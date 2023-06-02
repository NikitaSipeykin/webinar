package ru.nikSipeikin.javaTwo.netChat.server.core;

import ru.nikSipeikin.javaTwo.network.ServerSocketThread;
import ru.nikSipeikin.javaTwo.network.ServerSocketThreadListener;
import ru.nikSipeikin.javaTwo.network.SocketThread;
import ru.nikSipeikin.javaTwo.network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {
    ServerSocketThread thread;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss: ");
    public void start(int port){
        if (thread != null && thread.isAlive()){
            System.out.println("Server is already started");
        }else{
            thread = new ServerSocketThread(this, "Thread of server", 8189, 2000);
        }
    }

    public void stop(){
        if (thread == null || !thread.isAlive()){
            System.out.println("Server is not running");
        }else {
            thread.interrupt();
        }
    }

    private  void putLog(String message){
        message = DATE_FORMAT.format(System.currentTimeMillis()) +
                Thread.currentThread().getName() +": " + message;
        System.out.println(message);
    }

    /**
     *
     * Server methods
     * */

    @Override
    public void onServerStart(ServerSocketThread thread) {
        putLog("Server thread started");
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server thread stopped");
    }

    @Override
    public void onServerSocketCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket created");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
        //putLog("Server timeout");
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable exception) {
       exception.printStackTrace();
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Client connected");
        String name = "SocketThread " + socket.getInetAddress() + ":" + socket.getPort();
        new SocketThread(this, name, socket);
    }


    @Override
    public synchronized void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Socket created");
    }

    @Override
    public synchronized void onSocketStop(SocketThread thread) {
        putLog("Socket stopped");
    }

    @Override
    public synchronized void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Socket ready");
    }

    @Override
    public synchronized void onReceiveString(SocketThread thread, Socket socket, String message) {
        thread.sendMessage("echo: " + message);
    }

    @Override
    public synchronized void onSocketException(SocketThread thread, Exception exception) {
        exception.printStackTrace();
    }
}
