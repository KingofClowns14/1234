package com.la12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProgressServer {

    public static void main(String[] args) {
        int port = 8080;
        System.out.println("Сервер запущен на порту " + port + ". Ожидание клиента...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подключился: " + clientSocket.getInetAddress());
                // Для каждого клиента запускаем свой обработчик в отдельном потоке
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Класс для обработки конкретного клиента
    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private final Object monitor = new Object();
        private volatile boolean isRunning = false;
        private volatile boolean isPaused = false;
        private Thread workerThread;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                String inputLine;
                // Читаем команды от клиента в бесконечном цикле
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Получена команда: " + inputLine);
                    switch (inputLine) {
                        case "START":
                            handleStart(out);
                            break;
                        case "PAUSE":
                            handlePause();
                            break;
                        case "RESUME":
                            handleResume();
                            break;
                        case "STOP":
                            handleStop();
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Соединение с клиентом разорвано.");
            } finally {
                handleStop(); // Убеждаемся, что рабочий поток остановлен
            }
        }

        private void handleStart(PrintWriter out) {
            handleStop(); // Остановить предыдущий, если был
            isRunning = true;
            isPaused = false;
            // Запускаем рабочий поток
            workerThread = new Thread(() -> {
                try {
                    int totalIterations = 1000;
                    for (int i = 0; i < totalIterations; i++) {
                        if (!isRunning)
                            break;
                        // Логика паузы
                        synchronized (monitor) {
                            while (isPaused) {
                                monitor.wait();
                            }
                        }
                        Thread.sleep(20);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Рабочий поток прерван");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            workerThread.start();
        }

        private void handlePause() {
            isPaused = true;
        }

        private void handleResume() {
            isPaused = false;
            synchronized (monitor) {
                monitor.notify();
            }
        }

        private void handleStop() {
            isRunning = false;
            if (workerThread != null) {
                workerThread.interrupt();
            }
            // Если поток висел на паузе, его нужно разбудить, чтобы он завершился
            synchronized (monitor) {
                monitor.notifyAll();
            }
        }
    }
}