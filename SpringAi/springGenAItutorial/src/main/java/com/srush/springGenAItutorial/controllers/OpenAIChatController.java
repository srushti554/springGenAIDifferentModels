package com.srush.springGenAItutorial.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openai/chat")
public class OpenAIChatController {

    private static final String SYSTEM_PROMPT = "Only answer to summarizing tasks, if the question or prompt is not about" +
            " summarizing any text then simply return that i can only summarize text";
    private final ChatClient chatClient;

    public OpenAIChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/summarize")
    public String summarizeText(@RequestBody String message) {
        // system method, if you only want this endpoint answer for sumarize questions and no other question.
        // You can specify what the system should answer and what not
        return chatClient.prompt().system(SYSTEM_PROMPT).user(message).call().content();
    }

    @PostMapping("/summarizeText")
    public ChatClientResponse summarizeChatResponse(@RequestBody String message) {
        // system method, if you only want this endpoint answer for sumarize questions and no other question.
        // You can specify what the system should answer and what not
        return chatClient.prompt().system(SYSTEM_PROMPT).user(message).call().chatClientResponse();
    }

    //This method is prviding a tempate to the LLM on how to answer any question related to only summarizing meeting notes.
    // this is a dedicated endpoint for meeting notes
    @PostMapping("/summarizeMeetingNotes")
    public String summarizeMeetingNotes(@RequestBody String meetingNotes) {
        return chatClient.prompt().system(SYSTEM_PROMPT)
                .user(u -> u.text("Can you summarize the following meeting notes: {meetingNotes}"
                +"use the format described in the following example while doing summarization:" +
                " Input: In today's meeting we reviewed Q3 targets and performance gaps." +
                " the team will be given extra points for last quarter's great performances." +
                " We need to focus more on interacting with our clients and getting their concerns addressed " +
                "Output: " +
                "Action Items: We need to focus more on interacting with our clients and getting their concerns addressed " +
                "Decisions:the team will be given extra points for last quarter's great performances")
                        .param("meetingNotes",meetingNotes))
                .call()
                .content();
    }
}
