package com.laserviewtechnologies.socialapp.controller;

import com.laserviewtechnologies.socialapp.model.Message;
import com.laserviewtechnologies.socialapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Endpoint to get chat messages between two users
    @GetMapping("/{userId}/{friendId}")
    public List<Message> getMessages(@PathVariable String userId, @PathVariable String friendId) {
        return chatService.getUserMessages(userId, friendId);
    }

    // WebSocket endpoint to send a message (real-time chat)
    @MessageMapping("/send")  // Maps to /app/send endpoint (used by client to send messages)
    public void sendMessage(@Payload Message message) {
        // Save the message in the database
        Message savedMessage = chatService.saveMessage(message);

        // Send the message to both sender and receiver using WebSocket
        messagingTemplate.convertAndSendToUser(message.getReceiverId(), "/queue/messages", savedMessage);
        messagingTemplate.convertAndSendToUser(message.getSenderId(), "/queue/messages", savedMessage);
    }

    // This endpoint is just for testing or direct API-based messaging (optional)
    @PostMapping("/sendMessage")
    public void sendMessageViaAPI(@RequestBody Message message) {
        // Save message to the database
        Message savedMessage = chatService.saveMessage(message);

        // Send message to receiver using WebSocket
        messagingTemplate.convertAndSendToUser(message.getReceiverId(), "/queue/messages", savedMessage);
    }
}

