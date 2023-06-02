package ru.nikSipeikin.javaTwo.network;

import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketThreadListener {
    void onServerStart(ServerSocketThread thread);
    void onServerStop(ServerSocketThread thread);
    void onServerSocketCreated(ServerSocketThread thread, ServerSocket server);
    void onServerTimeout(ServerSocketThread thread, ServerSocket server);
    void onServerException(ServerSocketThread thread, Throwable exception);
    void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket);
}
