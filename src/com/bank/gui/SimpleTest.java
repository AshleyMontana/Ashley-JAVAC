package com.bank.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleTest extends Application {

    public static void main(String[] args) {
        System.out.println("✅ JavaFX confirmed working!");
        System.out.println("Now building Banking System GUI...");
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("🎉 JavaFX READY!\n\nNext: Building Banking GUI...");
        label.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");

        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 400, 200);

        stage.setTitle("SUCCESS - JavaFX Working");
        stage.setScene(scene);
        stage.show();
    }
}