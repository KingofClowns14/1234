package com.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CalculatorApp extends Application {
    private TextField display; // Поле для вывода чисел
    private String operator = ""; // Текущий оператор (+, -, *, /)
    private double number1 = 0; // Первый операнд
    private boolean start = true; // Флаг: начинать ли вводить новое число

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Калькулятор");
        display = new TextField();
        display.setFont(Font.font("Monospaced", 28));
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setText("0");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);
        // Определяем надписи на кнопках
        String[][] buttons = {
                { "7", "8", "9", "/" },
                { "4", "5", "6", "*" },
                { "1", "2", "3", "-" },
                { "0", ".", "=", "+" }
        };
        // Создаем кнопки с цифрами и операциями в цикле
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                String label = buttons[y][x];
                Button button = createButton(label);
                grid.add(button, x, y);
            }
        }
        // Создание дополнительных кнопок
        Button clearButton = createButton("C");
        grid.add(clearButton, 0, 4, 2, 1);
        GridPane.setFillWidth(clearButton, true);
        Button signButton = createButton("+/-");
        grid.add(signButton, 2, 4, 2, 1);
        GridPane.setFillWidth(signButton, true);
        // Главное окно
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(display);
        root.setCenter(grid);
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Вспомогательный метод для создания и настройки кнопок
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setFont(Font.font("System", 18));
        GridPane.setHgrow(button, Priority.ALWAYS);
        GridPane.setVgrow(button, Priority.ALWAYS);
        // Назначаем обработчик в зависимости от типа кнопки
        switch (text) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                button.setOnAction(e -> processNumber(text));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                button.setOnAction(e -> processOperator(text));
                break;
            case "=":
                button.setOnAction(e -> processEquals());
                break;
            case "C":
                button.setOnAction(e -> processClear());
                break;
            case "+/-":
                button.setOnAction(e -> processSignChange());
                break;
        }
        return button;
    }

    // Обработка нажатия на цифры и точку
    private void processNumber(String value) {
        if (start) {
            display.setText("");
            start = false;
        }
        // Защита от нескольких точек в одном числе
        if (value.equals(".") && display.getText().contains(".")) {
            return;
        }
        display.setText(display.getText() + value);
    }

    // Обработка нажатия на операторы (+, -, *, /)
    private void processOperator(String value) {
        if (!operator.isEmpty() && !start) {
            processEquals();
        }
        operator = value;
        number1 = Double.parseDouble(display.getText());
        start = true;
    }

    // Обработка нажатия на "="
    private void processEquals() {
        if (operator.isEmpty() || start) {
            return;
        }
        double number2 = Double.parseDouble(display.getText());
        try {
            double result = calculate(number1, number2, operator);
            display.setText(String.valueOf(result));
            if (result == Math.floor(result)) {
                display.setText(String.valueOf((int) result));
            }
        } catch (IllegalArgumentException e) {
            display.setText("Ошибка");
        }
        operator = "";
        start = true;
    }

    // Обработка нажатия на "C"
    private void processClear() {
        display.setText("0");
        operator = "";
        number1 = 0;
        start = true;
    }

    // Обработка нажатия на "+/-"
    private void processSignChange() {
        String currentText = display.getText();
        if (currentText.equals("0") || currentText.equals("Ошибка")) {
            return;
        }
        if (currentText.startsWith("-")) {
            display.setText(currentText.substring(1));
        } else {
            display.setText("-" + currentText);
        }
    }

    // Метод для выполнения вычислений
    private double calculate(double num1, double num2, String op) throws IllegalArgumentException {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль");
                }
                return num1 / num2;
            default:
                throw new IllegalStateException("Неизвестный оператор: " + op);
        }
    }
}