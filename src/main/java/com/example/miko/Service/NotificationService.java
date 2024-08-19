package com.example.miko.Service;
import org.springframework.stereotype.Service;

import com.example.miko.Handler.NotificationHandler;

@Service
public class NotificationService {

    private final LanguageService languageService;
    private final NotificationHandler notificationHandler;

    public NotificationService(LanguageService languageService, NotificationHandler notificationHandler) {
        this.languageService = languageService;
        this.notificationHandler = notificationHandler;
    }

    public void changeLanguage(String userId, String language) throws Exception {
        languageService.changeLanguage(userId, language, notificationHandler);
    }
}



