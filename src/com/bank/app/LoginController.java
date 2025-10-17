package com.bank.app;

import com.bank.core.Customer;

/**
 * Lightweight LoginController for the demo GUI.
 * For simplicity this controller accepts a demo credential:
 *   id = "demo", password = "pwd"
 *
 * In a later step we can switch this to use CustomerDAO for real DB-backed auth.
 */
public class LoginController {

    /**
     * Authenticate a user. Returns a Customer object when successful, otherwise null.
     * Demo credentials: id="demo", pwd="pwd"
     */
    public Customer authenticate(String id, String password) {
        if (id == null || password == null) return null;

        // Demo quick login
        if ("demo".equals(id) && "pwd".equals(password)) {
            // return a demo Customer; passwords are not hashed here because it's a demo
            return new Customer("Demo", "User", "Demo Address", "demo@example.com", "pwd");
        }

        // Could add DB-backed lookup here (CustomerDAO.findById) later.
        return null;
    }
}
