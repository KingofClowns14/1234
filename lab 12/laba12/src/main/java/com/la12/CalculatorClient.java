package com.la12;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8081;
        try (Socket socket = new Socket(host, port);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                Scanner scanner = new Scanner(System.in)) {
            System.out.println("Подключено к серверу калькулятора.");
            System.out.println("Введите 'exit' вместо числа для выхода.");
            while (true) {
                try {
                    System.out.print("Введите первое число: ");
                    if (scanner.hasNext("exit"))
                        break;
                    double num1 = Double.parseDouble(scanner.next());
                    System.out.print("Введите второе число: ");
                    double num2 = Double.parseDouble(scanner.next());
                    System.out.print("Введите операцию (+, -, *, /): ");
                    String op = scanner.next();
                    // Отправка данных на сервер
                    out.writeDouble(num1);
                    out.writeDouble(num2);
                    out.writeUTF(op);
                    out.flush();
                    // Получение результата
                    boolean isSuccess = in.readBoolean();
                    if (isSuccess) {
                        double result = in.readDouble();
                        System.out.println("Результат: " + result);
                    } else {
                        String error = in.readUTF();
                        System.out.println("Сервер: " + error);
                    }
                    System.out.println("-------------------------");
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод числа. Попробуйте снова.");
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка подключения: " + e.getMessage());
        }
    }
}