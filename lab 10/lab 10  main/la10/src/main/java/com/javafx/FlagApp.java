package com.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.List;

public class FlagApp extends Application {
    private final List<String> availableColors = List.of("Красный", "Синий", "Зелёный", "Белый", "Чёрный");
    private final ToggleGroup topStripeGroup = new ToggleGroup();
    private final ToggleGroup middleStripeGroup = new ToggleGroup();
    private final ToggleGroup bottomStripeGroup = new ToggleGroup();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Текстовый флаг");
        // Создаем три панели выбора, по одной для каждой полосы
        VBox topSelector = createStripeSelector("Верхняя полоса:", availableColors, topStripeGroup);
        VBox middleSelector = createStripeSelector("Средняя полоса:", availableColors, middleStripeGroup);
        VBox bottomSelector = createStripeSelector("Нижняя полоса:", availableColors, bottomStripeGroup);
        Button drawButton = new Button("Нарисовать");
        drawButton.setFont(Font.font("System", FontWeight.BOLD, 14));
        Label resultLabel = new Label("Выберите цвета и нажмите 'Нарисовать'");
        resultLabel.setFont(Font.font("System", 14));
        resultLabel.setPadding(new Insets(10, 0, 0, 0));
        drawButton.setOnAction(event -> {
            Toggle selectedTop = topStripeGroup.getSelectedToggle();
            Toggle selectedMiddle = middleStripeGroup.getSelectedToggle();
            Toggle selectedBottom = bottomStripeGroup.getSelectedToggle();
            if (selectedTop == null || selectedMiddle == null || selectedBottom == null) {
                resultLabel.setText("Ошибка: выберите цвет для каждой полосы!");
                return;
            }
            String topColor = ((RadioButton) selectedTop).getText();
            String middleColor = ((RadioButton) selectedMiddle).getText();
            String bottomColor = ((RadioButton) selectedBottom).getText();
            String resultText = String.format("%s, %s, %s", topColor, middleColor, bottomColor);
            resultLabel.setText(resultText);
        });
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(topSelector, middleSelector, bottomSelector, drawButton, resultLabel);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Вспомогательный метод для создания панели выбора цвета для одной полосы.
     * 
     * @param title        Текст заголовка (например, "Верхняя полоса:")
     * @param colorOptions Список доступных цветов
     * @param toggleGroup  Группа, к которой будут принадлежать RadioButton
     * @return VBox, содержащий заголовок и набор RadioButton
     */
    private VBox createStripeSelector(String title, List<String> colorOptions, ToggleGroup toggleGroup) {
        Label label = new Label(title);
        label.setFont(Font.font("System", FontWeight.BOLD, 12));
        // HBox для горизонтального расположения кнопок
        HBox radioButtonsBox = new HBox(10);
        for (String color : colorOptions) {
            RadioButton rb = new RadioButton(color);
            rb.setToggleGroup(toggleGroup); // Добавляем кнопку в группу
            radioButtonsBox.getChildren().add(rb);
        }
        VBox selectorBox = new VBox(5, label, radioButtonsBox);
        return selectorBox;
    }
}