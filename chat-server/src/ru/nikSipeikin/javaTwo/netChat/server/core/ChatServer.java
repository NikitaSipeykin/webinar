package ru.nikSipeikin.javaTwo.netChat.server.core;

import ru.nikSipeikin.javaTwo.network.ServerSocketThread;

public class ChatServer {
    ServerSocketThread thread;
    public void start(int port){
        if (thread != null && thread.isAlive()){
            System.out.println("Server is already started");
        }else{
            thread = new ServerSocketThread("Thread of server", 8189, 2000);
        }
    }

    public void stop(){
        if (thread == null || !thread.isAlive()){
            System.out.println("Server is not running");
        }else {
            thread.interrupt();
        }
    }
}
