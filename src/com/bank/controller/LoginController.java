package com.bank.controller;

public class LoginController {
    private String currentUser;

    public boolean authenticate(String username, String password) {
        // Simple authentication
        if ("admin".equals(username) && "admin123".equals(password)) {
            currentUser = username;
            return true;
        }
        if ("test".equals(username) && "test".equals(password)) {
            currentUser = username;
            return true;
        }
        return false;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }
}