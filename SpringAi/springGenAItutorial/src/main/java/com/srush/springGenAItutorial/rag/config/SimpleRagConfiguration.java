package com.srush.springGenAItutorial.rag.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class SimpleRagConfiguration {

    private static final String FILES_PATH="classpath:rag/mini-emp-handbook/*.md";
    private static final String VECTOR_STORE = "src/main/resources/rag/mini-emp-handbook/vectorStore";

    @Bean
    public TokenTextSplitter tokenTextSplitter(RagConfig ragConfig) {
        return TokenTextSplitter.builder()
                .withChunkSize(ragConfig.getChunkProperties().getSize())
                .withMinChunkSizeChars(ragConfig.getChunkProperties().getMinChunkSize())
                .withMinChunkLengthToEmbed(ragConfig.getChunkProperties().getMinChunksToEmbed())
                .withKeepSeparator(ragConfig.getChunkProperties().isKeepSeparted())
                .withMaxNumChunks(ragConfig.getChunkProperties().getMaxNumChunks())
                .build();
    }
    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel, TokenTextSplitter tokenTextSplitter, RagConfig ragConfig) throws IOException {
        var simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        var vectorStoreFile = new File(VECTOR_STORE);
        if(vectorStoreFile.exists() && !ragConfig.isForceRebuild()) {
            log.info("Vector Store Exists, loding it from {}", VECTOR_STORE);
            simpleVectorStore.load(vectorStoreFile);
            return simpleVectorStore;
        }
        log.info("Creating new vector Store file at : {}",VECTOR_STORE);
        var documents = loadDocuments();
        var chunks = getChunks(documents,tokenTextSplitter);
        simpleVectorStore.add(chunks);
        simpleVectorStore.load(vectorStoreFile);
        return  simpleVectorStore;

    }

    private List<Document> loadDocuments() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(FILES_PATH);

        List<Document> documents = new ArrayList<>();
        for(Resource resource:resources) {
            TextReader reader = new TextReader(resource);
            reader.getCustomMetadata().put("category",resource.getFilename().replace(".md",""));
            reader.getCustomMetadata().put("access_level","public");
            reader.getCustomMetadata().put("version","2026.01");
            documents.addAll(reader.read());
        }
        return documents;
    }

    private List<Document> getChunks(List<Document> documents, TokenTextSplitter tokenTextSplitter) {
        return tokenTextSplitter.apply(documents);
    }

}
