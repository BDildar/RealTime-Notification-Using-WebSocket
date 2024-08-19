package com.example.miko.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.miko.Handler.NotificationHandler;

import java.util.List;

@Service
public class LanguageService {

    private final List<String> supportedLanguages;
    private final NotificationHandler notificationHandler;

    public LanguageService(
            @Value("${miko.supported-languages}") List<String> supportedLanguages,
            NotificationHandler notificationHandler) {
        this.supportedLanguages = supportedLanguages;
        this.notificationHandler = notificationHandler;
    }

    public boolean changeLanguage(String userId, String language) {
        if (isSupportedLanguage(language)) {
            try {
                notificationHandler.sendLanguageChangeNotification(userId, language);
                System.out.println("Language changed to " + language + " for user: " + userId);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Unsupported language: " + language);
            return false;
        }
    }

    private boolean isSupportedLanguage(String language) {
        return supportedLanguages.contains(language.toLowerCase());
    }

    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }
}


