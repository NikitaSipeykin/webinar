package ru.nikSipeikin.javaTwo.netChat.server.core;

import ru.nikSipeikin.javaTwo.netChat.common.Library;
import ru.nikSipeikin.javaTwo.network.SocketThread;
import ru.nikSipeikin.javaTwo.network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread {

    private String nickname;
    private boolean isAuthorized;

    public String getNickname() {
        return nickname;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public ClientThread(SocketThreadListener listener, String name, Socket socket) {
        super(listener, name, socket);
    }

    void authAccept(String nickname){
        isAuthorized = true;
        this.nickname = nickname;
        sendMessage(Library.getAuthAccept(nickname));
    }

    void authFail(){
        sendMessage(Library.getAuthDenied());
        close();
    }

    void messageFormatError(String message){
        sendMessage(Library.getMsgFormatError(message));
        close();
    }
}
