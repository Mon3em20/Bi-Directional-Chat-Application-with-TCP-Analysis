package com.chattingapplication.client;

import java.io.*;
import java.net.*;

public class MyClient {
    public static void main(String[] args) {
        final String serverAddress = "1192.168.8.191"; // server's actual IP address
        final int serverPort = 5000; // Changed the port to 4460

        try (Socket clientSocket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connected to the server.");

            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Thread sendThread = new Thread(() -> {
                try {
                    String clientMessage;
                    while (true) {
                        clientMessage = br.readLine();
                        dout.writeUTF(clientMessage);
                        dout.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread receiveThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while (true) {
                        serverMessage = din.readUTF();
                        System.out.println("Server says: " + serverMessage);
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
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}