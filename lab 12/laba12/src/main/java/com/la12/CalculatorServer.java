package com.la12;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
    public static void main(String[] args) {
        int port = 8081;
        System.out.println("Сервер калькулятора запущен на порту " + port);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                // Ожидаем клиента
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новый клиент подключился: " + clientSocket.getInetAddress());
                // Запускаем обработку клиента в отдельном потоке
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                try {
                    // Читаем данные от клиента
                    double num1 = in.readDouble();
                    double num2 = in.readDouble();
                    String operation = in.readUTF();
                    double result = 0;
                    String errorMessage = "";
                    switch (operation) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                errorMessage = "Ошибка: Деление на ноль!";
                            } else {
                                result = num1 / num2;
                            }
                            break;
                        default:
                            errorMessage = "Ошибка: Неизвестная операция";
                    }
                    // Отправляем ответ
                    if (errorMessage.isEmpty()) {
                        out.writeBoolean(true);
                        out.writeDouble(result);
                    } else {
                        out.writeBoolean(false);
                        out.writeUTF(errorMessage);
                    }
                    out.flush();
                } catch (EOFException e) {
                    System.out.println("Клиент отключился");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка связи с клиентом: " + e.getMessage());
        }
    }
}