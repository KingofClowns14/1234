package com.la12;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MatchesGameServer {
    private int matches = 37;
    private List<PlayerHandler> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public static void main(String[] args) {
        new MatchesGameServer().start(8070);
    }

    public void start(int port) {
        System.out.println("Сервер игры запущен. Ожидание игроков...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (players.size() < 2) {
                Socket socket = serverSocket.accept();
                PlayerHandler player = new PlayerHandler(socket, players.size());
                players.add(player);
                new Thread(player).start();
                System.out.println("Игрок " + (players.size()) + " подключился.");
            }
            System.out.println("Оба игрока подключены. Игра начинается!");
            broadcast("START " + matches);
            notifyTurn();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void processMove(int playerIndex, int amount) {
        if (playerIndex != currentPlayerIndex)
            return;
        matches -= amount;
        broadcast("UPDATE " + matches);

        if (matches <= 0) {
            players.get(currentPlayerIndex).sendMessage("WIN");
            players.get((currentPlayerIndex + 1) % 2).sendMessage("LOSE");
            return;
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        notifyTurn();
    }

    private void notifyTurn() {
        players.get(currentPlayerIndex).sendMessage("YOUR_TURN");
        players.get((currentPlayerIndex + 1) % 2).sendMessage("OPPONENT_TURN");
    }

    private void broadcast(String msg) {
        for (PlayerHandler p : players) {
            p.sendMessage(msg);
        }
    }

    private class PlayerHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private int id;

        public PlayerHandler(Socket socket, int id) {
            this.socket = socket;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("INFO Ожидание второго игрока...");
                String line;
                while ((line = in.readLine()) != null) {
                    if (line.startsWith("TAKE")) {
                        int amount = Integer.parseInt(line.split(" ")[1]);
                        processMove(id, amount);
                    }
                }
            } catch (IOException e) {
                System.out.println("Игрок отключился");
            }
        }

        public void sendMessage(String msg) {
            if (out != null)
                out.println(msg);
        }
    }
}
