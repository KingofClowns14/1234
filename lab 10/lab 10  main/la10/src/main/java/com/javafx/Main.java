package com.javafx;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============================================");
        System.out.println("Выберите приложение для запуска:");
        System.out.println("1)Задание №1-2");
        System.out.println("2)The program for ordering in a restaurant");
        System.out.println("3)Calculator");
        System.out.println("4)Text flag");
        System.out.println("=============================================");
        System.out.print("Введите номер (1-4): ");
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Запускается WordShifter...");
                    WordShifter.launch(WordShifter.class, args);
                    break;
                case 2:
                    System.out.println("Запускается RestaurantApp...");
                    RestaurantApp.launch(RestaurantApp.class, args);
                    break;
                case 3:
                    System.out.println("Запускается CalculatorApp...");
                    CalculatorApp.launch(CalculatorApp.class, args);
                    break;
                case 4:
                    System.out.println("Запускается FlagApp...");
                    FlagApp.launch(FlagApp.class, args);
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, введите число от 1 до 4.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println("Ошибка: Необходимо ввести целое число.");
        } finally {
            scanner.close();
        }
    }
}