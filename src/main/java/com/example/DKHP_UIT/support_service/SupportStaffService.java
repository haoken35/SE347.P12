package com.example.DKHP_UIT.support_service;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SupportStaffService {
    private String[] characters = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n", "o", "v", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };

    public String createPassword() {
        String s = "";
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int randomNumber = random.nextInt(characters.length - 1 - 0 + 1) + 0;
            s = s + characters[randomNumber];
        }
        return s;
    }
}
