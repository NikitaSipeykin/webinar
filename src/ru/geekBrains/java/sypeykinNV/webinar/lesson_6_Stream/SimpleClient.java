package ru.geekBrains.java.sypeykinNV.webinar.lesson_6_Stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        try(Socket serverSocket = new Socket("127.0.0.1", 8189)){
            System.out.println("Connected to server");

            DataInputStream dataInputStream = new DataInputStream(serverSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(serverSocket.getOutputStream());
            dataOutputStream.writeUTF("Hello, server");

            String clientString = dataInputStream.readUTF();
            System.out.println(clientString);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
