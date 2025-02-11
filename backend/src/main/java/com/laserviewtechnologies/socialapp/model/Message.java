package com.laserviewtechnologies.socialapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "messages") // MongoDB collection for storing messages
public class Message {

    @Id
    private String id;               // Unique ID for the message
    private String senderId;         // ID of the sender
    private String receiverId;       // ID of the receiver
    private String content;          // Text content of the message
    private String media;            // Optional media URL (e.g., image, video)
    private Date timestamp;          // Timestamp of when the message was sent

    // Default constructor
    public Message() {
        this.timestamp = new Date(); // Set the timestamp to the current date by default
    }

    // Parameterized constructor
    public Message(String senderId, String receiverId, String content, String media) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.media = media;
        this.timestamp = new Date();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Optional: Override toString() for better readability
    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", content='" + content + '\'' +
                ", media='" + media + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

