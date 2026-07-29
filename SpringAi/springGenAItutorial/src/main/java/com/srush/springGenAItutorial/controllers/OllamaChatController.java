package com.srush.springGenAItutorial.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ollama/chat")
public class OllamaChatController {

    private static final String SYSTEM_PROMPT = "You are a email drafting assistant which can only reply to email drafting request." +
            "If you get any requests which are not related to Email drafting then just return that I am only an Email drafting assistant" +
            "The email that you draft should be concise, should have the necessary information and should have a good crisp subject line." +
            "The email should be very formal and professional";

    private final ChatClient chatClient;

    public OllamaChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/draftEmail")
    public String draftEmail(@RequestBody String message) {
        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(message)
                .call()
                .content();
    }
}
