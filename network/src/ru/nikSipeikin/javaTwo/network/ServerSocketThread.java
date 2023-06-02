package ru.nikSipeikin.javaTwo.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketThread extends Thread{
    private final int port;
    private final int timeout;
    private final ServerSocketThreadListener listener;

    public ServerSocketThread(ServerSocketThreadListener listener, String name, int port, int timeout) {
        super(name);
        this.port = port;
        this.timeout = timeout;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        listener.onServerStart(this);

        try (ServerSocket serverSocket = new ServerSocket(port)){
            serverSocket.setSoTimeout(timeout);// задает время выполнения метода .accept()
            listener.onServerSocketCreated(this, serverSocket);
            while (!isInterrupted()){
                Socket socket;
                try {
                    socket = serverSocket.accept();// после timeout запускается по новой, тем самым проверяя isInterrupted()

                }catch (SocketTimeoutException e){
                    listener.onServerTimeout(this, serverSocket);
                    continue;
                }
                listener.onSocketAccepted(this, serverSocket, socket);
            }
        }catch (IOException e){
            listener.onServerException(this, e);
        }finally {
            listener.onServerStop(this);
        }
    }
}
