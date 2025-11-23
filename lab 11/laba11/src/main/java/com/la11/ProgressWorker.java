package com.la11;

import java.util.function.Consumer;

public class ProgressWorker implements Runnable {
    private final int totalIterations = 1000;
    private final Consumer<Double> onProgressUpdate; // Функция для обновления ProgressBar
    private final Runnable onFinish; // Функция, которая вызовется по завершении
    // Ключевые поля для управления состоянием потока
    private final Object monitor; // Объект для синхронизации (wait/notify)
    private volatile boolean running = true;
    private volatile boolean paused = false;

    public ProgressWorker(Object monitor, Consumer<Double> onProgressUpdate, Runnable onFinish) {
        this.monitor = monitor;
        this.onProgressUpdate = onProgressUpdate;
        this.onFinish = onFinish;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < totalIterations; i++) {
                // Проверка, не была ли дана команда на полную остановку
                if (!running) {
                    break;
                }
                // Логика паузы
                synchronized (monitor) {
                    while (paused) {
                        // Поток "засыпает" и ОСВОБОЖДАЕТ монитор,
                        // позволяя другим потокам его захватить.
                        // Он ждет, пока кто-то вызовет notify() на этом же мониторе.
                        monitor.wait();
                    }
                }
                // Выполнение работы
                Thread.sleep(20); // Имитация работы (20 мс)
                // Обновление прогресса
                double progress = (i + 1.0) / totalIterations;
                onProgressUpdate.accept(progress);
            }
        } catch (InterruptedException e) {
            // Это исключение возникнет, если поток прервут во время sleep() или wait().
            // Считаем это сигналом к завершению.
            System.out.println("The Thread was interrupted.");
        } finally {
            // Код, который выполнится в любом случае по завершении
            onFinish.run();
        }
    }

    // Дает команду потоку полностью завершить свою работу.
    public void stop() {
        running = false;
    }

    /*
     * Приостанавливает или возобновляет выполнение цикла.
     * paused true для паузы, false для возобновления
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}