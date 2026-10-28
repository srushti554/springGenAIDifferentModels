package com.srush.springGenAItutorial.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class TokenGenerationAdvisor implements CallAdvisor, StreamAdvisor {
    private final Logger LOGGER = LoggerFactory.getLogger(TokenGenerationAdvisor.class);
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        LOGGER.info("Token Generation details Advisor entry");
        LOGGER.info("Request going to LLM: {}",chatClientRequest.prompt().getContents());
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        LOGGER.info("Response coming from LLM: {}",chatClientResponse.chatResponse().getResult().getOutput().getText());
        LOGGER.info("Printing Token Details:");
        LOGGER.info("Prompt Input Tokens: {}",chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
        LOGGER.info("Completion Tokens: {}",chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        LOGGER.info("Token Tokens: {}",chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return null;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
