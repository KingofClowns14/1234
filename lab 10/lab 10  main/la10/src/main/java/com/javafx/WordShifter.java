package com.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WordShifter extends Application {

    private boolean isArrowRight = true;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Комбинированное приложение");
        // Задание №1
        TextField leftTextField = new TextField();
        TextField rightTextField = new TextField();
        Button shiftButton = new Button();
        Polygon arrowRight = createArrow(true);
        Polygon arrowLeft = createArrow(false);
        shiftButton.setGraphic(arrowRight);
        shiftButton.setOnAction(event -> {
            if (isArrowRight) {
                rightTextField.setText(leftTextField.getText());
                leftTextField.clear();
                shiftButton.setGraphic(arrowLeft);
            } else {
                leftTextField.setText(rightTextField.getText());
                rightTextField.clear();
                shiftButton.setGraphic(arrowRight);
            }
            isArrowRight = !isArrowRight;
        });
        HBox wordShifterBox = new HBox(10, leftTextField, shiftButton, rightTextField);
        wordShifterBox.setAlignment(Pos.CENTER);
        // Задание №2
        Label labelWidget = new Label("Это метка (Label)");
        labelWidget.setFont(new Font("Arial", 16));
        Button buttonWidget = new Button("Нажми меня!");
        Slider sliderWidget = new Slider(0, 100, 50);
        CheckBox labelCheckbox = new CheckBox("Показать/скрыть метку");
        CheckBox buttonCheckbox = new CheckBox("Показать/скрыть кнопку");
        CheckBox sliderCheckbox = new CheckBox("Показать/скрыть слайдер");
        labelWidget.visibleProperty().bind(labelCheckbox.selectedProperty());
        labelWidget.managedProperty().bind(labelCheckbox.selectedProperty());
        buttonWidget.visibleProperty().bind(buttonCheckbox.selectedProperty());
        buttonWidget.managedProperty().bind(buttonCheckbox.selectedProperty());
        sliderWidget.visibleProperty().bind(sliderCheckbox.selectedProperty());
        sliderWidget.managedProperty().bind(sliderCheckbox.selectedProperty());
        labelCheckbox.setSelected(true);
        buttonCheckbox.setSelected(true);
        sliderCheckbox.setSelected(true);
        HBox labelRow = new HBox(20, labelCheckbox, labelWidget);
        labelRow.setAlignment(Pos.CENTER_LEFT);
        HBox buttonRow = new HBox(20, buttonCheckbox, buttonWidget);
        buttonRow.setAlignment(Pos.CENTER_LEFT);
        HBox sliderRow = new HBox(20, sliderCheckbox, sliderWidget);
        sliderRow.setAlignment(Pos.CENTER_LEFT);
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().add(wordShifterBox);
        root.getChildren().add(new Separator());
        root.getChildren().addAll(labelRow, buttonRow, sliderRow);
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Polygon createArrow(boolean right) {
        Polygon arrow = new Polygon();
        if (right) {
            arrow.getPoints().addAll(0.0, 0.0, 10.0, 5.0, 0.0, 10.0);
        } else {
            arrow.getPoints().addAll(10.0, 0.0, 0.0, 5.0, 10.0, 10.0);
        }
        return arrow;
    }
}