package com.Monster_Card_Game.server;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {
    String jdbcURL;
    String username;
    String password;
    Connection connection;
    public DatabaseHandler() throws SQLException {
        jdbcURL="jdbc:postgresql://localhost:5432/MonsterCardGame";
        username="postgres";
        password="root";
        connection = DriverManager.getConnection(jdbcURL,username,password);
        System.out.println("Database Connected");
    }

}
