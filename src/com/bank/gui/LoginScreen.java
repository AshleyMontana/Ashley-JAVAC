package com.bank.gui;

import com.bank.controller.LoginController;
import com.bank.controller.BankController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScreen {

    public void show(Stage stage) {
        stage.setTitle("Banking System - Login");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #2C3E50;"); // Added color

        // Title
        Text title = new Text("Banking System Login");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        title.setFill(Color.WHITE); // Added color

        // Username
        Label userLabel = new Label("Username:");
        userLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        userLabel.setTextFill(Color.LIGHTBLUE); // Added color
        grid.add(userLabel, 0, 1);

        TextField userField = new TextField();
        userField.setPromptText("Enter username");
        userField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-background-color: #ECF0F1;"); // Added color
        grid.add(userField, 1, 1);

        // Password
        Label passLabel = new Label("Password:");
        passLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        passLabel.setTextFill(Color.LIGHTBLUE); // Added color
        grid.add(passLabel, 0, 2);

        PasswordField passField = new PasswordField();
        passField.setPromptText("Enter password");
        passField.setStyle("-fx-font-size: 14px; -fx-padding: 8px; -fx-background-color: #ECF0F1;"); // Added color
        grid.add(passField, 1, 2);

        // Login Button
        Button loginBtn = new Button("Login");
        loginBtn.setDefaultButton(true);
        loginBtn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10px 20px; " +
                "-fx-background-color: #27AE60; -fx-text-fill: white;"); // Added color

        // Status message
        Text status = new Text();
        status.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(status, 1, 4);

        // Button action
        loginBtn.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            LoginController loginController = new LoginController();
            boolean authenticated = loginController.authenticate(username, password);

            if (authenticated) {
                status.setText("✅ Login successful!");
                status.setFill(Color.LIGHTGREEN); // Added color

                // Create BankController
                BankController bankController = new BankController();

                // Open dashboard
                DashboardScreen dashboard = new DashboardScreen(loginController, bankController);
                dashboard.show(stage);
            } else {
                status.setText("❌ Invalid username or password");
                status.setFill(Color.RED); // Added color
            }
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginBtn);
        grid.add(hbBtn, 1, 3);

        grid.add(title, 0, 0, 2, 1);

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}