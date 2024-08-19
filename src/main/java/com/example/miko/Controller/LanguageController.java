package com.example.miko.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.miko.Service.LanguageService;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @PostMapping("/change")
    public ResponseEntity<String> changeLanguage(@RequestParam String userId, @RequestParam String language) {
        boolean success = languageService.changeLanguage(userId, language);
        if (success) {
            return ResponseEntity.ok("Language updated successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to update language");
        }
    }
}

