package com.Monster_Card_Game.user;

import com.Monster_Card_Game.server.DatabaseHandler;
import com.Monster_Card_Game.stack.Deck;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    private int vcoins;
    private String username;
    private String password;
    private int userID;
    DatabaseHandler dbHandler;
    private Deck deck;

    @JsonCreator
    User(@JsonProperty("Username")String username,@JsonProperty("Password")String password) throws SQLException {
        this.username=username;
        this.password=password;
        vcoins=20;
        dbHandler=new DatabaseHandler();
        userID=-1; // userID is non existent
    }

    User(String username) throws SQLException {
        this.username=username;
        vcoins=20;
        dbHandler=new DatabaseHandler();
        userID=-1;
    }
    public int getVcoins() {
        return vcoins;
    }

    public void setVcoins(int vcoins) {
        this.vcoins = vcoins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void resetPassword(){ password=""; }

    public void acquirePackage() throws SQLException {
        if(userID==-1) {
            String getUserID = "Select \"userid\" from \"MonsterCardGame\".\"user\"\n" +
                    "WHERE \"username\" = ?";
            PreparedStatement preparedStatement = dbHandler.connection.prepareStatement(getUserID);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userID=resultSet.getInt("userid");
            }
        }
        String sqlAcquire="UPDATE \"MonsterCardGame\".\"package\" SET \"userid\" = "+userID+" WHERE \"packageid\" = " +
                "(SELECT \"packageid\" from \"MonsterCardGame\".\"package\"" +
                "WHERE \"userid\" IS NULL LIMIT 1);";
        Statement stmt=dbHandler.connection.createStatement();
        stmt.executeUpdate(sqlAcquire);
    }

    public void showAcquiredCards() throws SQLException {
        if(userID==-1) {
            String getUserID = "Select \"userid\" from \"MonsterCardGame\".\"user\"\n" +
                    "WHERE \"username\" = ?";
            PreparedStatement preparedStatement = dbHandler.connection.prepareStatement(getUserID);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userID=resultSet.getInt("userid");
            }
        }
        String selectCards="select * " +
                "from \"MonsterCardGame\".\"card\" as c" +
                " join \"MonsterCardGame\".\"package\" as p" +
                " on c.\"packageid\"=p.\"packageid\"" +
                " where p.\"userid\"="+userID;
        Statement stmt=dbHandler.connection.createStatement();
        System.out.println(selectCards);
        ResultSet resultSet=stmt.executeQuery(selectCards);
        int i=0;
        while (resultSet.next()){
            i++;
            System.out.println("Card "+resultSet.getString("cardserialid")+": "
                    +resultSet.getString("cardname")+" "+resultSet.getString("carddamage")+
                    " "+resultSet.getString("cardattribute")+" "+resultSet.getString("cardmonster"));
        }
    }

    public void createDeck(String payload) throws SQLException {
        deck=new Deck();
        deck.createCards(payload);
    }

    public void printDeck(){
        if (deck==null){
            System.out.println("Empty Deck!!!");
        }
        deck.printDeck();
    }

}
