package ru.nikSipeikin.javaTwo.netChat.server.core;

import ru.nikSipeikin.javaTwo.netChat.common.Library;
import ru.nikSipeikin.javaTwo.network.ServerSocketThread;
import ru.nikSipeikin.javaTwo.network.ServerSocketThreadListener;
import ru.nikSipeikin.javaTwo.network.SocketThread;
import ru.nikSipeikin.javaTwo.network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {
    private ServerSocketThread thread;
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss: ");
    private final ChatServerListener listener;
    private final Vector<SocketThread> clients;

    public ChatServer(ChatServerListener listener){
        this.listener = listener;
        this.clients = new Vector<>();
    }

    public void start(int port){
        if (thread != null && thread.isAlive()){
            putLog("Server is already started");
        }else{
            thread = new ServerSocketThread(this, "Thread of server", port, 2000);
        }
    }

    public void stop(){
        if (thread == null || !thread.isAlive()){
            putLog("Server is not running");
        }else {
            thread.interrupt();
        }
    }

    private  void putLog(String message){
        message = DATE_FORMAT.format(System.currentTimeMillis()) +
                Thread.currentThread().getName() +": " + message;
        listener.onChatServerMessage(message);
    }

    /**
     *
     * Server methods
     * */

    @Override
    public void onServerStart(ServerSocketThread thread) {
        putLog("Server thread started");
        SQLClient.connect();
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server thread stopped");
        SQLClient.disconnect();
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
        new ClientThread(this, name, socket);
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
        clients.add(thread);
    }

    @Override
    public synchronized void onReceiveString(SocketThread thread, Socket socket, String message) {
        ClientThread client = (ClientThread) thread;
        if (client.isAuthorized())
            handleAuthMessage(client, message);
        else
            handleNonAuthMessage(client, message);
//        for (int i = 0; i < clients.size(); i++) {
//            SocketThread client = clients.get(i);
//            client.sendMessage(message);
//        }
    }

    private void handleNonAuthMessage(ClientThread client, String message){
        String[] arr = message.split(Library.DELIMITER);
        if (arr.length != 3 || !arr[0].equals(Library.AUTH_REQUEST)){
            client.messageFormatError(message);
            return;
        }
        String login = arr[1];
        String password = arr[2];
        String nickname = SQLClient.getNickname(login, password);
        if (nickname == null){
            putLog("Invalid login attempt: " + login);
            client.authFail();
            return;
        }
        client.authAccept(nickname);
        sendToAllAuthorizedClients(Library.getTypeBroadcast("Server ", nickname + " connected"));
    }

    private void handleAuthMessage(ClientThread client, String message){
        sendToAllAuthorizedClients(Library.getTypeBroadcast(client.getNickname(), message));
    }

    private void sendToAllAuthorizedClients(String message) {
        for (int i = 0; i < clients.size(); i++) {
            ClientThread recipient = (ClientThread) clients.get(i);
            if (!recipient.isAuthorized())continue;
            recipient.sendMessage(message);
        }
    }

    @Override
    public synchronized void onSocketException(SocketThread thread, Exception exception) {
        exception.printStackTrace();
    }
}
