package com.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RestaurantApp extends Application {

    private final List<Dish> menu = List.of(
            new Dish("Борщ", 350.00),
            new Dish("Пельмени", 420.50),
            new Dish("Салат Цезарь", 550.00),
            new Dish("Стейк Рибай", 1500.00),
            new Dish("Морс клюквенный", 120.00));

    // Вспомогательный класс для хранения связанных UI элементов для одного блюда
    private record MenuRow(Dish dish, CheckBox checkBox, Spinner<Integer> quantitySpinner) {
    }

    // Список, который будет хранить все строки нашего меню
    private final List<MenuRow> menuRows = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ресторанный чек");
        // Создание панели меню
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(15);
        menuGrid.setVgap(10);

        for (Dish dish : menu) {
            CheckBox dishCheckBox = new CheckBox(String.format("%s (%.2f руб.)", dish.name(), dish.price()));
            // Создаем спиннер для выбора количества порций от 1 до 99
            Spinner<Integer> quantitySpinner = new Spinner<>(1, 99, 1);
            quantitySpinner.setPrefWidth(70);
            quantitySpinner.setDisable(true);
            // Связывание чекбокса и спиннера
            dishCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    quantitySpinner.setDisable(false);
                    quantitySpinner.getValueFactory().setValue(1);
                } else {
                    quantitySpinner.setDisable(true);
                }
            });
            int currentRow = menuGrid.getRowCount();
            menuGrid.add(dishCheckBox, 0, currentRow);
            menuGrid.add(quantitySpinner, 1, currentRow);
            menuRows.add(new MenuRow(dish, dishCheckBox, quantitySpinner));
        }
        // Создание кнопки и области для вывода чека
        Button calculateButton = new Button("Сформировать чек");
        calculateButton.setFont(Font.font("System", FontWeight.BOLD, 14));
        TextArea billTextArea = new TextArea();
        billTextArea.setEditable(false);
        billTextArea.setFont(Font.font("Monospaced", 12));
        // Логика обработки нажатия на кнопку
        calculateButton.setOnAction(event -> {
            StringBuilder billText = new StringBuilder();
            billText.append("----------- ВАШ ЧЕК -----------\n");
            billText.append(String.format("%-20s %-10s %s\n", "Блюдо", "Кол-во", "Сумма"));
            billText.append("----------------------------------\n");
            double totalOrderCost = 0.0;
            for (MenuRow row : menuRows) {
                if (row.checkBox().isSelected()) {
                    Dish currentDish = row.dish();
                    int quantity = row.quantitySpinner().getValue();
                    double dishTotalCost = currentDish.price() * quantity;
                    billText.append(String.format("%-20s x%-9d %.2f\n",
                            currentDish.name(), quantity, dishTotalCost));
                    totalOrderCost += dishTotalCost;
                }
            }
            billText.append("----------------------------------\n");
            billText.append(String.format("ИТОГО К ОПЛАТЕ: %.2f руб.\n", totalOrderCost));
            billTextArea.setText(billText.toString());
        });
        // Главное окно приложения
        Label menuLabel = new Label("Меню:");
        menuLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        Label billLabel = new Label("Чек:");
        billLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        VBox root = new VBox(15, menuLabel, menuGrid, calculateButton, billLabel, billTextArea);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 450, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}