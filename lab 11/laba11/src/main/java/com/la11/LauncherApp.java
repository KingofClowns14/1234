package com.la11;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LauncherApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Выбор приложения для запуска");
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Выберите, какое приложение запустить:");
        // Кнопка для запуска бесконечного потока
        Button btnInfinit = new Button("1. Бесконечного потока (App)");
        btnInfinit.setMaxWidth(Double.MAX_VALUE);
        btnInfinit.setOnAction(event -> {
            launchApplication(new App());
            primaryStage.close();
        });
        // Кнопка для запуска "зависающего" приложения
        Button btnFreeze = new Button("2. Демонстрация зависания (FreezingApp)");
        btnFreeze.setMaxWidth(Double.MAX_VALUE);
        btnFreeze.setOnAction(event -> {
            launchApplication(new FreezingApp());
            primaryStage.close();
        });
        // Кнопка для запуска исправления через Runnable
        Button btnRunnable = new Button("3. Исправление через Runnable");
        btnRunnable.setMaxWidth(Double.MAX_VALUE);
        btnRunnable.setOnAction(event -> {
            launchApplication(new FixedWithRunnable());
            primaryStage.close();
        });
        // Кнопка для запуска исправления через наследование Thread
        Button btnThread = new Button("4. Исправление через наследование Thread");
        btnThread.setMaxWidth(Double.MAX_VALUE);
        btnThread.setOnAction(event -> {
            launchApplication(new FixedWithThread());
            primaryStage.close();
        });
        // Кнопка для запуска приложения с прогресс-баром
        Button btnProgress = new Button("5. Прогресс-бар");
        btnProgress.setMaxWidth(Double.MAX_VALUE);
        btnProgress.setOnAction(event -> {
            launchApplication(new ProgressBarApp());
            primaryStage.close();
        });
        root.getChildren().addAll(titleLabel, btnInfinit, btnFreeze, btnRunnable, btnThread, btnProgress);
        Scene scene = new Scene(root, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void launchApplication(Application app) {
        try {
            Stage newStage = new Stage();
            app.start(newStage);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Не удалось запустить приложение: " + app.getClass().getSimpleName());
        }
    }

}