package com.example.Admin_Dashboard_backend.controller;


import com.example.Admin_Dashboard_backend.model.ChatMessage;
import com.example.Admin_Dashboard_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "*")  // Allow requests from frontend (React)
public class ChatBotController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/message")
    public ChatMessage handleMessage(@RequestBody String userMessage) {
        return chatService.handleUserMessage(userMessage);
    }
}
