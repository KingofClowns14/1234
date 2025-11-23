package com.la11;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProgressBarApp extends Application {
    // Объект-монитор для синхронизации. Должен быть один на все потоки.
    private final Object monitor = new Object();
    // Ссылки на фоновую задачу и поток, чтобы ими можно было управлять
    private ProgressWorker currentWorker;
    private Thread workerThread;
    private ProgressBar progressBar;
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Взаимодействие потоков с GUI");
        stage.setOnCloseRequest(event -> stopCurrentTask());
        Label titleLabel = new Label("Прогресс выполнения задачи:");
        progressBar = new ProgressBar(0);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        startButton = new Button("Старт");
        pauseButton = new Button("Пауза");
        stopButton = new Button("Стоп");
        pauseButton.setDisable(true);
        stopButton.setDisable(true);
        startButton.setOnAction(event -> handleStart());
        pauseButton.setOnAction(event -> handlePauseResume());
        stopButton.setOnAction(event -> handleStop());
        HBox buttonBox = new HBox(10, startButton, pauseButton, stopButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(15, titleLabel, progressBar, buttonBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(root, 400, 180));
        stage.show();
    }

    private void handleStart() {
        // Останавливаем предыдущую задачу, если она была.
        stopCurrentTask();
        progressBar.setProgress(0);

        // Создаем задачу (Runnable).
        currentWorker = new ProgressWorker(
                monitor,
                progress -> Platform.runLater(() -> progressBar.setProgress(progress)),
                () -> Platform.runLater(() -> {
                    // Сбрасываем UI только если поток, который завершился (Thread.currentThread()),
                    // это тот же самый поток, который мы храним как "актуальный" (workerThread).
                    if (workerThread == Thread.currentThread()) {
                        resetUI();
                    }
                }));
        // Создаем новый поток, ЗАПОМИНАЕМ на него ссылку в поле workerThread и
        // запускаем.
        workerThread = new Thread(currentWorker);
        workerThread.setDaemon(true);
        workerThread.start();
        // Включаем кнопки "Пауза" и "Стоп" для нового потока.
        pauseButton.setDisable(false);
        stopButton.setDisable(false);
        pauseButton.setText("Пауза");
    }

    private void handlePauseResume() {
        if (currentWorker == null) {
            return;
        }
        if (pauseButton.getText().equals("Пауза")) {
            currentWorker.setPaused(true);
            pauseButton.setText("Продолжить");
        } else {
            currentWorker.setPaused(false);
            pauseButton.setText("Пауза");
            // "Будим" рабочий поток, который ждет на мониторе
            synchronized (monitor) {
                monitor.notify();
            }
            pauseButton.setText("Пауза");
        }
    }

    private void handleStop() {
        stopCurrentTask();
        resetUI(); // Сразу сбрасываем интерфейс
    }

    // Корректно останавливает текущую фоновую задачу.
    private void stopCurrentTask() {
        if (currentWorker != null) {
            currentWorker.stop();
        }
        if (workerThread != null) {
            // Прерывание потока разбудит его, если он в sleep() или wait()
            workerThread.interrupt();
        }
    }

    // Сбрасывает элементы интерфейса в исходное состояние.
    private void resetUI() {
        progressBar.setProgress(0);
        pauseButton.setDisable(true);
        stopButton.setDisable(true);
        pauseButton.setText("Пауза");
    }

    @Override
    public void stop() {
        // Гарантируем остановку потока при закрытии окна
        stopCurrentTask();
    }
}