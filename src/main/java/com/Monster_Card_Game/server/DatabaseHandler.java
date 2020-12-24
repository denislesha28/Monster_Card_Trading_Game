package com.Monster_Card_Game.server;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void createUser(String username,String password) throws SQLException {
        String insertStatement="INSERT INTO \"MonsterCardGame\".\"user\" (\"username\",\"password\") " +
                "VALUES (\'"+username+"\',\'"+password+"\')";
        System.out.println(insertStatement);
        Statement stmt=connection.createStatement();
        stmt.executeUpdate(insertStatement);
    }
}
