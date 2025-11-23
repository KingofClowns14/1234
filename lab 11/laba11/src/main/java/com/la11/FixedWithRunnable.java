package com.la11;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FixedWithRunnable extends Application {
    private Thread backgroundThread;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(10));
        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPromptText("Здесь будут сообщения от фонового потока...");
        Button runButton = new Button("Запустить в фоне (через Runnable)");
        runButton.setOnAction(event -> {
            logArea.setText("Фоновый поток запущен. GUI работает!\n");
            Runnable task = () -> {
                try {
                    int counter = 0;
                    while (!Thread.currentThread().isInterrupted()) {
                        counter++;
                        final String message = "Сообщение из фона #" + counter + "\n";
                        Platform.runLater(() -> logArea.appendText(message));
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("The background thread was interrupted.");
                }
            };
            stopPreviousThread();
            backgroundThread = new Thread(task);
            backgroundThread.setDaemon(true);
            backgroundThread.start();
        });
        root.getChildren().addAll(runButton, logArea);
        stage.setTitle("GUI не зависает (Runnable)");
        stage.setScene(new Scene(root, 350, 250));
        stage.show();
    }

    @Override
    public void stop() {
        stopPreviousThread();
    }

    private void stopPreviousThread() {
        if (backgroundThread != null && backgroundThread.isAlive()) {
            backgroundThread.interrupt();
        }
    }
}