package com.example.Admin_Dashboard_backend.service;


import com.example.Admin_Dashboard_backend.model.ChatMessage;

public interface ChatService {
    ChatMessage handleUserMessage(String userMessage);
}
