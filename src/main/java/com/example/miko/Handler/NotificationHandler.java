package com.example.miko.Handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationHandler extends TextWebSocketHandler {


    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = extractUserId(session);
        sessions.put(userId, session);
        System.out.println("WebSocket connection established for user: " + userId);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String userId = extractUserId(session);
        sessions.remove(userId);
        System.out.println("WebSocket connection closed for user: " + userId);
    }

    public void sendLanguageChangeNotification(String userId, String language) throws Exception {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            String message = createNotificationMessage(language);
            session.sendMessage(new TextMessage(message));
            System.out.println("Language change notification sent to user: " + userId);
        } else {
            System.out.println("No open WebSocket session for user: " + userId);
        }
    }

    private String extractUserId(WebSocketSession session) {
        // Implement your logic to extract the user ID from the session
        return "user123"; // Placeholder
    }

    private String createNotificationMessage(String language) {
        // Create a message payload for the language change
        return "Language changed to " + language;
    }
}
