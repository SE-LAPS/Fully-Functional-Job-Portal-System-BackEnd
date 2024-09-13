package com.example.Admin_Dashboard_backend.service;


import com.example.Admin_Dashboard_backend.model.ChatMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "chatbot-messages";

    public ChatServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public ChatMessage handleUserMessage(String userMessage) {
        // Predefined responses (same as React frontend)
        String aiResponse = switch (userMessage) {
            case "How do I create an account?" -> "To create an account, click on the 'Sign Up' button...";
            case "How can I search for jobs?" -> "Use the search bar on the homepage...";
            case "What is the status of my application?" -> "Go to 'My Applications'...";
            case "How can I update my resume?" -> "Log in to 'My Profile'...";
            case "How do I contact support?" -> "Email us at DesignITBazzinga@jobportal.com...";
            default -> "I’m not sure how to answer that.";
        };

        // Publish user message to Kafka topic
        kafkaTemplate.send(TOPIC, userMessage);

        // Return AI response as ChatMessage object
        return new ChatMessage("ai", aiResponse);
    }
}
