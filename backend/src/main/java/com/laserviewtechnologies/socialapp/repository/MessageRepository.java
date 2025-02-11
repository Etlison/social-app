package com.laserviewtechnologies.socialapp.repository;

import com.laserviewtechnologies.socialapp.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    // Retrieve messages between two users based on their sender and receiver IDs
    List<Message> findBySenderIdAndReceiverIdOrReceiverIdAndSenderId(String senderId, String receiverId, String receiverId2, String senderId2);

    // Optionally, find all messages for a specific user (either sent or received)
    List<Message> findBySenderId(String senderId);

    List<Message> findByReceiverId(String receiverId);
}


