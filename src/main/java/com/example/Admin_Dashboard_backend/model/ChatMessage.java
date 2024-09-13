package com.example.Admin_Dashboard_backend.model;

public class ChatMessage {
    private String type;  // 'user' or 'ai'
    private String text;

    public ChatMessage() {}

    public ChatMessage(String type, String text) {
        this.type = type;
        this.text = text;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
