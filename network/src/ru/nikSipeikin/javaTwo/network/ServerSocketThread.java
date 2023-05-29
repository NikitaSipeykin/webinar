package ru.nikSipeikin.javaTwo.network;

public class ServerSocketThread extends Thread{
    private final int port;
    private final int timeout;

    public ServerSocketThread(String name, int port, int timeout) {
        super(name);
        this.port = port;
        this.timeout = timeout;
        start();
    }

    @Override
    public void run() {
        System.out.println("Server started");

        while (!isInterrupted()){
            try {
                sleep(timeout);
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
            System.out.println("Server is working");
        }
        System.out.println("server stopped");
    }
}
