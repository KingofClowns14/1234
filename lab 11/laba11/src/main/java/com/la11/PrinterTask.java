package com.la11;

import javafx.application.Platform;
import java.util.function.Consumer;

class PrinterTask implements Runnable {
    private final Object monitor;
    private final Consumer<String> output;
    private final String myName;
    private final String nextThreadName;

    public PrinterTask(Object monitor, String myName, String nextThreadName, Consumer<String> output) {
        this.monitor = monitor;
        this.myName = myName;
        this.nextThreadName = nextThreadName;
        this.output = output;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (monitor) {
                try {
                    while (!App.currentTurn.equals(myName)) {
                        monitor.wait();
                    }
                    Platform.runLater(() -> output.accept(myName + "\n"));
                    Thread.sleep(500);
                    App.currentTurn = nextThreadName;
                    monitor.notifyAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(myName + " close.");
                }
            }
        }
    }
}