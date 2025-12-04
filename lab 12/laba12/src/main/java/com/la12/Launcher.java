package com.la12;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите модуль для запуска:");
        System.out.println("Калькулятор");
        System.out.println("1. Запустить Сервер Калькулятора");
        System.out.println("2. Запустить Клиент Калькулятора");
        System.out.println("Игра 'Спички'");
        System.out.println("3. Запустить Сервер Игры");
        System.out.println("4. Запустить Клиент Игры");
        System.out.println("Прогресс-бар");
        System.out.println("5. Запустить Сервер Прогресса");
        System.out.println("6. Запустить Клиент Прогресса");
        System.out.println("-----------------------------------------");
        System.out.println("0. Выход");
        System.out.print("\nВведите номер: ");
        String choice = scanner.next();

        switch (choice) {
            case "1":
                System.out.println("Запуск сервера калькулятора...");
                CalculatorServer.main(new String[] {});
                break;
            case "2":
                System.out.println("Запуск клиента калькулятора...");
                CalculatorClient.main(new String[] {});
                break;
            case "3":
                System.out.println("Запуск сервера игры 'Спички'...");
                MatchesGameServer.main(new String[] {});
                break;
            case "4":
                System.out.println("Запуск графического клиента игры...");
                MatchesGameClient.main(new String[] {});
                break;
            case "5":
                System.out.println("Запуск сервера прогресс-бара...");
                ProgressServer.main(new String[] {});
                break;
            case "6":
                System.out.println("Запуск графического клиента прогресс-бара...");
                ProgressClientApp.main(new String[] {});
                break;
            case "0":
                System.out.println("Выход из программы.");
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор. Перезапустите программу.");
        }
    }
}