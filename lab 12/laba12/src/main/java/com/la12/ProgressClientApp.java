package com.la12;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProgressClientApp extends Application {

    private ProgressBar progressBar;
    private Button startButton;
    private Button pauseButton;
    private Button stopButton;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    // Поток для чтения ответов от сервера
    private Thread listenerThread;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Клиент: Управление прогрессом");
        stage.setOnCloseRequest(event -> disconnect());
        Label titleLabel = new Label("Прогресс выполнения (на сервере):");
        progressBar = new ProgressBar(0);
        progressBar.setMaxWidth(Double.MAX_VALUE);
        startButton = new Button("Старт");
        pauseButton = new Button("Пауза");
        stopButton = new Button("Стоп");
        pauseButton.setDisable(true);
        stopButton.setDisable(true);
        startButton.setOnAction(event -> sendCommand("START"));
        pauseButton.setOnAction(event -> handlePauseResume());
        stopButton.setOnAction(event -> sendCommand("STOP"));
        HBox buttonBox = new HBox(10, startButton, pauseButton, stopButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(15, titleLabel, progressBar, buttonBox);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(root, 400, 180));
        stage.show();
        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 8080);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Запускаем поток, который слушает обновления прогресса от сервера
            listenerThread = new Thread(this::listenFromServer);
            listenerThread.setDaemon(true);
            listenerThread.start();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось подключиться к серверу!");
            alert.showAndWait();
            startButton.setDisable(true);
        }
    }

    // Логика чтения данных от сервера
    private void listenFromServer() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                String message = line;
                Platform.runLater(() -> processServerMessage(message));
            }
        } catch (IOException e) {
            System.out.println("Связь с сервером потеряна.");
        }
    }

    // Обработка входящих сообщений в потоке JavaFX
    private void processServerMessage(String message) {
        if ("FINISHED".equals(message)) {
            resetUI();
        } else {
            try {
                // Сервер присылает число
                double progress = Double.parseDouble(message);
                progressBar.setProgress(progress);
                // Если мы получили прогресс, значит задача идет, активируем кнопки
                if (stopButton.isDisable()) {
                    stopButton.setDisable(false);
                    pauseButton.setDisable(false);
                    pauseButton.setText("Пауза");
                }
            } catch (NumberFormatException e) {
                // Игнорируем некорректные данные
            }
        }
    }

    private void sendCommand(String cmd) {
        if (out != null) {
            out.println(cmd);
            if ("STOP".equals(cmd)) {
                resetUI();
            }
        }
    }

    private void handlePauseResume() {
        if (pauseButton.getText().equals("Пауза")) {
            sendCommand("PAUSE");
            pauseButton.setText("Продолжить");
        } else {
            sendCommand("RESUME");
            pauseButton.setText("Пауза");
        }
    }

    private void resetUI() {
        progressBar.setProgress(0);
        pauseButton.setDisable(true);
        stopButton.setDisable(true);
        pauseButton.setText("Пауза");
    }

    private void disconnect() {
        try {
            if (out != null)
                out.close();
            if (in != null)
                in.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}