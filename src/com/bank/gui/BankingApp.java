package com.bank.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class BankingApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println("=== Banking System - YOUR Personal Project ===");

        // Show YOUR login screen
        LoginScreen login = new LoginScreen();
        login.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}