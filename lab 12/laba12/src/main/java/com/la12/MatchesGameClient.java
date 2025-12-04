package com.la12;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MatchesGameClient extends Application {
    private Label infoLabel;
    private Label countLabel;
    private HBox buttonBox;
    private PrintWriter out;
    private BufferedReader in;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Игра: Спички");
        countLabel = new Label("Спичек на столе: ?");
        countLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        infoLabel = new Label("Подключение к серверу...");
        infoLabel.setStyle("-fx-text-fill: blue;");
        buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        for (int i = 1; i <= 5; i++) {
            int val = i;
            Button btn = new Button(String.valueOf(val));
            btn.setOnAction(e -> sendMove(val));
            btn.setDisable(true); // Кнопки выключены по умолчанию
            buttonBox.getChildren().add(btn);
        }
        VBox root = new VBox(20, countLabel, buttonBox, infoLabel);
        root.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(root, 300, 200));
        stage.show();
        new Thread(this::connectToServer).start();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 8070);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                String cmd = line;
                Platform.runLater(() -> processCommand(cmd));
            }
        } catch (IOException e) {
            Platform.runLater(() -> infoLabel.setText("Ошибка подключения"));
        }
    }

    private void processCommand(String cmd) {
        if (cmd.startsWith("INFO")) {
            infoLabel.setText(cmd.substring(5));
        } else if (cmd.startsWith("START") || cmd.startsWith("UPDATE")) {
            String count = cmd.split(" ")[1];
            countLabel.setText("Спичек на столе: " + count);
        } else if (cmd.equals("YOUR_TURN")) {
            infoLabel.setText("Ваш ход! Выберите от 1 до 5 спичек.");
            buttonBox.getChildren().forEach(node -> node.setDisable(false));
        } else if (cmd.equals("OPPONENT_TURN")) {
            infoLabel.setText("Ход противника...");
            buttonBox.getChildren().forEach(node -> node.setDisable(true));
        } else if (cmd.equals("WIN")) {
            infoLabel.setText("ПОБЕДА! Вы взяли последнюю спичку.");
            infoLabel.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            buttonBox.getChildren().forEach(node -> node.setDisable(true));
        } else if (cmd.equals("LOSE")) {
            infoLabel.setText("ПОРАЖЕНИЕ. Противник взял последнюю спичку.");
            infoLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            buttonBox.getChildren().forEach(node -> node.setDisable(true));
        }
    }

    private void sendMove(int amount) {
        out.println("TAKE " + amount);
        // Сразу блокируем кнопки, чтобы не нажать дважды
        buttonBox.getChildren().forEach(node -> node.setDisable(true));
    }
}