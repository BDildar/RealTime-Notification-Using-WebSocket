package com.example.miko.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.miko.Handler.NotificationHandler;
import com.example.miko.Service.NotificationService;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private final NotificationService notificationService;
    private final NotificationHandler notificationHandler;

    public LanguageController(NotificationService notificationService) {
        this.notificationService = notificationService;
		this.notificationHandler = new NotificationHandler();
    }

    @PostMapping("/change")
    public void changeLanguage(@RequestParam String userId, @RequestParam String language) throws Exception {
        notificationService.changeLanguage(userId, language);
    }
    
    @PostMapping("/send-notification")
    public String sendNotification(@RequestParam String userId, @RequestParam String message) {
        try {
            notificationHandler.notifyUser(userId, message);
            return "Notification sent!";
        } catch (Exception e) {
            return "Error sending notification: " + e.getMessage();
        }
    }
}


