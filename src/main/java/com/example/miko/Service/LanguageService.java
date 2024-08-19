package com.example.miko.Service;
import org.springframework.stereotype.Service;

import com.example.miko.Handler.NotificationHandler;

import java.util.List;

@Service
public class LanguageService {

    private final List<String> supportedLanguages = List.of("en", "fr", "es", "de", "it", "zh", "jp", "kr");

    public boolean isLanguageSupported(String language) {
        return supportedLanguages.contains(language);
    }

    public void changeLanguage(String userId, String language, NotificationHandler notificationHandler) throws Exception {
        if (isLanguageSupported(language)) {
            notificationHandler.notifyUser(userId, "Language changed to " + language);
        } else {
            throw new IllegalArgumentException("Unsupported language: " + language);
        }
    }
}



