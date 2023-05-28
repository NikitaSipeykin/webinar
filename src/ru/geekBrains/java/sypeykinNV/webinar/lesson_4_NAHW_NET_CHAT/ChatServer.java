package ru.geekBrains.java.sypeykinNV.webinar.lesson_4_NAHW_NET_CHAT;

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
