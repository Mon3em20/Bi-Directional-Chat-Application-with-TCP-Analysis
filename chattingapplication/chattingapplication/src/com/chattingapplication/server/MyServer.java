package com.chattingapplication.server;

import java.io.*;
import java.net.*;

public class MyServer {
    public static void main(String[] args) {
        final int PORT = 5000; // Changed the port to 4470
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Waiting for clients...");

            Socket clientSocket = serverSocket.accept(); // client's IP address is 192.168.1.18
            System.out.println("Client connected.");

            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Thread sendThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while (true) {
                        serverMessage = br.readLine();
                        dout.writeUTF(serverMessage);
                        dout.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread receiveThread = new Thread(() -> {
                try {
                    String clientMessage;
                    while (true) {
                        clientMessage = din.readUTF();
                        System.out.println("Client says: " + clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            sendThread.start();
            receiveThread.start();

            sendThread.join();
            receiveThread.join();

            din.close();
            dout.close();
            clientSocket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}