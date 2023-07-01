package ru.nikSipeikin.javaTwo.netChat.common;

public class Library {
    public static final String DELIMITER = "±";
    public static final String AUTH_REQUEST = "/auth_request";
    public static final String AUTH_ACCEPT = "/auth_accept";
    public static final String AUTH_DENIED = "/aut_denied";
    public static final String MSG_FORMAT_ERROR = "/msg_format_error";
    public static final String TYPE_BROADCAST = "/bcast";
    public static final String USER_LIST = "/user_list";
    public static final String TYPE_BROADCAST_CLIENT = "/clien_bcast";

    public static String getAuthRequest(String login, String password){
        return AUTH_REQUEST + DELIMITER + login + DELIMITER + password;
    }

    public static String getAuthAccept (String nickname){
        return AUTH_ACCEPT + DELIMITER + nickname;
    }

    public static String getAuthDenied(){
        return AUTH_DENIED;
    }

    public static String getMsgFormatError(String message){
        return MSG_FORMAT_ERROR + DELIMITER +message;
    }

    public static String getTypeBroadcast(String src, String message){
        return TYPE_BROADCAST + DELIMITER + System.currentTimeMillis() + DELIMITER + src + DELIMITER + message;
    }

    public static String getUserList(String users){
        return USER_LIST + DELIMITER + users;
    }

    public static String getTypeBroadcastClient(String message){
        return TYPE_BROADCAST_CLIENT + DELIMITER + message;
    }
}
