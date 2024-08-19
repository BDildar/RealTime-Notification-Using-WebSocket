package com.example.miko.Handler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NotificationHandler extends TextWebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        if (userId != null) {
            sessions.put(userId, session);
            System.out.println("WebSocket connection established for user: " + userId);
        } else {
            System.out.println("No userId found in the WebSocket connection");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        // Process incoming message
    }

    public void notifyUser(String userId, String message) throws Exception {
        WebSocketSession session = sessions.get(userId);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(message));
            System.out.println("Notified user: " + userId + " with message: " + message);
        } else {
            System.out.println("No open WebSocket session for user: " + userId);
        }
    }

    private String getUserId(WebSocketSession session) {
        try {
            URI uri = session.getUri();
            if (uri != null) {
                Map<String, String> queryParams = getQueryParams(uri);
                return queryParams.get("userId");
            }
        } catch (URISyntaxException e) {
            System.err.println("Error parsing URI: " + e.getMessage());
        }
        return null;
    }

    private Map<String, String> getQueryParams(URI uri) throws URISyntaxException {
        Map<String, String> queryParams = new HashMap<>();
        String[] pairs = uri.getQuery().split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            queryParams.put(pair.substring(0, idx), pair.substring(idx + 1));
        }
        return queryParams;
    }
}

