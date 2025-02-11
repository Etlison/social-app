package com.laserviewtechnologies.socialapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Configures WebSocket endpoint and message brokers
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the "/ws" endpoint for WebSocket connections
        // Clients will connect to this endpoint to establish a WebSocket connection
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*") // Allow connections from any origin
                .withSockJS();          // Enables SockJS fallback option if WebSockets are not supported
    }

    // Configures message broker for handling messages
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple message broker to broadcast messages to subscribers
        registry.enableSimpleBroker("/queue", "/topic");  // "/queue" for point-to-point and "/topic" for publish-subscribe
        registry.setApplicationDestinationPrefixes("/app"); // Prefix for routes to route the messages to the controllers
    }
}


