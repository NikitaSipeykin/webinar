package ru.geekBrains.java.sypeykinNV.webinar.lesson_6_Stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        System.out.println("Server started");

        try(ServerSocket serverSocket = new ServerSocket(8189);
            Socket currentClient = serverSocket.accept()){
            System.out.println("Client connected");

            DataInputStream dataInputStream = new DataInputStream(currentClient.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(currentClient.getOutputStream());

            String clientString = dataInputStream.readUTF();
            System.out.println("Client send us: " + clientString);
            dataOutputStream.writeUTF("Echo: " + clientString);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
