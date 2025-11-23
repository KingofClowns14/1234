package com.la11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    // Общий объект-монитор для синхронизации потоков
    private static final Object monitor = new Object();
    // Переменная для отслеживания очереди.
    public static volatile String currentTurn = "Thread1";
    private Thread thread1;
    private Thread thread2;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(300);
        Button startButton = new Button("Старт");
        startButton.setOnAction(event -> {
            outputArea.clear();
            Runnable task1 = new PrinterTask(monitor, "Thread1", "Thread2", outputArea::appendText);
            Runnable task2 = new PrinterTask(monitor, "Thread2", "Thread1", outputArea::appendText);
            thread1 = new Thread(task1);
            thread2 = new Thread(task2);
            thread1.setDaemon(true);
            thread2.setDaemon(true);
            thread1.start();
            thread2.start();
            startButton.setDisable(true);
        });
        root.getChildren().addAll(startButton, outputArea);
        Scene scene = new Scene(root, 350, 350);
        stage.setTitle("Синхронизация потоков");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        if (thread1 != null) {
            thread1.interrupt();
        }
        if (thread2 != null) {
            thread2.interrupt();
        }
        super.stop();
    }
}