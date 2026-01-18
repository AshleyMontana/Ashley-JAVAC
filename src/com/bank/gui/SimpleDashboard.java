package com.bank.gui;

import com.bank.controller.LoginController;
import com.bank.controller.BankController;
import com.bank.model.Customer;
import com.bank.model.Account;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleDashboard {
    private LoginController loginController;
    private BankController bankController;

    public SimpleDashboard(LoginController loginController, BankController bankController) {
        this.loginController = loginController;
        this.bankController = bankController;
    }

    public void show(Stage stage) {
        stage.setTitle("Banking Dashboard - Welcome " + loginController.getCurrentUser());

        BorderPane root = new BorderPane();

        // Menu
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem logoutItem = new MenuItem("Logout");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(logoutItem, new SeparatorMenuItem(), exitItem);
        menuBar.getMenus().add(fileMenu);

        // Side buttons
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(15));

        Button btn1 = new Button("Create Customer");
        Button btn2 = new Button("View Summary");
        Button btn3 = new Button("Logout");

        btn1.setPrefWidth(150);
        btn2.setPrefWidth(150);
        btn3.setPrefWidth(150);

        sideMenu.getChildren().addAll(btn1, btn2, btn3);

        // Content area
        TextArea content = new TextArea();
        content.setEditable(false);
        content.setText("Welcome to Banking System!\n\n" +
                "Week 3: Controller Implementation\n" +
                "✅ LoginController working\n" +
                "✅ BankController working\n" +
                "✅ GUI connected to Controllers");

        // Button actions
        btn1.setOnAction(e -> {
            Customer customer = bankController.createCustomer("Test", "User", "123 Street");
            content.setText("✅ Customer Created:\n" +
                    "ID: " + customer.getCustomerId() + "\n" +
                    "Name: " + customer.getFirstName() + " " + customer.getSurname() + "\n" +
                    "Address: " + customer.getAddress());
        });

        btn2.setOnAction(e -> {
            content.setText(bankController.getBankSummary());
        });

        btn3.setOnAction(e -> {
            loginController.logout();
            LoginScreen login = new LoginScreen();
            login.show(stage);
        });

        logoutItem.setOnAction(e -> {
            loginController.logout();
            LoginScreen login = new LoginScreen();
            login.show(stage);
        });

        exitItem.setOnAction(e -> stage.close());

        // Layout
        root.setTop(menuBar);
        root.setLeft(sideMenu);
        root.setCenter(content);

        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
    }
}