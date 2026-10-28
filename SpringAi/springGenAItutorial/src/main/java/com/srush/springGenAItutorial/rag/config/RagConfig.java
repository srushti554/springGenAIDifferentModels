package com.srush.springGenAItutorial.rag.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="app.rag")
@Data
public class RagConfig {

    private boolean forceRebuild;
    private int topK;
    private double similarityThreshold;
    private ChunkProperties chunk;

    @Data
    public static class ChunkProperties {
        private int size;
        private int minChunkSize;
        private int minChunksToEmbed;
        private int maxNumChunks;
        private boolean keepSeparted;
    }
}
