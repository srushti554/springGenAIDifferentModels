package com.srush.springGenAItutorial.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vertexai/chat")
public class GoogleVertexAIController {

    private static final String SYSTEM_PROMPT = "You're a helpful assistant that generates professional JavaDocs comments for java code." +
            "Always explain the purpose of the class or method, its parameters, return values, and any exceptions it may throw." +
            "Use the standard JavaDocs style with /**....*/." +
            "Keep explanations concise, clear and technical.";
    private final ChatClient chatClient;

    public GoogleVertexAIController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/generate-java-docs")
    public ChatClientResponse generateJavaDocs(@RequestBody String message) {
        return chatClient
                .prompt()
                .system(SYSTEM_PROMPT)
                .user(message)
                .call()
                .chatClientResponse();
    }
}
