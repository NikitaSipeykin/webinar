package ru.nikSipeikin.javaTwo.netChat.server.core;

import java.sql.*;

public class SQLClient {
    private static Connection connection;
    private static Statement statement;
    synchronized static void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:chat-server/chat-server.db");
            statement = connection.createStatement();
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    synchronized static String getNickname(String login, String password){
        String query = String.format("select nickname from clients where login = '%s' and password = '%s' ",
                login, password);

        try (ResultSet set = statement.executeQuery(query)){
            if (set.next())
                return set.getString(1);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    synchronized static void disconnect(){
        try {
            connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
