package com.laserviewtechnologies.socialapp.service;

import com.laserviewtechnologies.socialapp.model.Message;
import com.laserviewtechnologies.socialapp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MessageRepository messageRepository;

    // Save a new message to the database
    public Message saveMessage(Message message) {
        return messageRepository.save(message); // Save the message document in MongoDB
    }

    // Get all messages between two users (in either direction)
    public List<Message> getUserMessages(String userId, String friendId) {
        // This query finds messages where either the user is the sender or the receiver
        return messageRepository.findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(userId, friendId, userId, friendId);
    }

    // Optionally, retrieve all messages sent by a specific user (for display or history)
    public List<Message> getMessagesSentByUser(String senderId) {
        return messageRepository.findBySenderId(senderId);
    }

    // Optionally, retrieve all messages received by a specific user (for display or history)
    public List<Message> getMessagesReceivedByUser(String receiverId) {
        return messageRepository.findByReceiverId(receiverId);
    }
}

