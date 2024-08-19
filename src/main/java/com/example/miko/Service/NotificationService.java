package com.example.miko.Service;

import org.springframework.stereotype.Service;

import com.example.miko.Handler.NotificationHandler;

@Service
public class NotificationService {

    private final NotificationHandler notificationHandler;

    public NotificationService(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    public void sendLanguageChangeNotification(String userId, String language) throws Exception {
        notificationHandler.sendLanguageChangeNotification(userId, language);
    }
}


