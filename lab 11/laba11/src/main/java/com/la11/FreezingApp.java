package com.la11;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FreezingApp extends Application {
    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(10));
        Label label = new Label("Приложение готово к работе.");
        Button freezeButton = new Button("Запустить цикл и зависнуть");
        freezeButton.setOnAction(event -> {
            System.out.println("A blocking cycle begins in the main thread... \nThe app is frozen! Try to move it");
            while (true) {
            }
        });
        root.getChildren().addAll(label, freezeButton);
        stage.setTitle("Демонстрация зависания GUI");
        stage.setScene(new Scene(root, 350, 150));
        stage.show();
    }
}