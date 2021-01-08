package com.Monster_Card_Game.user;

import com.Monster_Card_Game.server.DatabaseHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StatsManager {
    public void insertScores(int battleResult,String username1,String username2, DatabaseHandler dbHandler){
        //1 - player 1 win, 2 - player 2 win, 3 - draw


    }

    public void showUserStats(String username,DatabaseHandler dbHandler) throws SQLException {
        String sql="SELECT \"username\",\"wins\",\"losses\",\"elo\",\"draws\" FROM " +
                "\"MonsterCardGame\".\"stats\" as s " +
                "join \"MonsterCardGame\".\"user\" as u " +
                "on s.\"userid\"=u.\"userid\" " +
                "where u.\"username\"=?";
        PreparedStatement preparedStatement=dbHandler.getConnection().prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("User: "+username);
            System.out.println("ELO: "+resultSet.getString("elo"));
            System.out.println("Wins: "+resultSet.getString("wins"));
            System.out.println("Losses: "+resultSet.getString("losses"));
            System.out.println("Draws: "+resultSet.getString("draws"));
        }

    }


}
