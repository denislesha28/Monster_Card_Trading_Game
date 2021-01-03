package com.Monster_Card_Game.user;

import com.Monster_Card_Game.server.DatabaseHandler;
import com.Monster_Card_Game.stack.Deck;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.ReentrantLock;

public class User {
    private int vcoins;
    private String username;
    private String password;
    private int userID;
    private Deck deck;
    private String bio;
    private String image;
    private ReentrantLock mutex=new ReentrantLock();

    @JsonCreator
    User(@JsonProperty("Username")String username,@JsonProperty("Password")String password)  {
        this.username=username;
        this.password=password;
        userID=-1; // userID is non existent
    }

    @JsonCreator
    User(@JsonProperty("Name")String username,@JsonProperty("Bio") String bio,@JsonProperty("Image")String image){
        this.username=username;
        this.bio=bio;
        this.image=image;
    }


    User(String username) throws SQLException {
        this.username=username;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean acquirePackage(DatabaseHandler dbHandler) throws SQLException {
        boolean confirmation=true;
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
        mutex.lock();
        if (checkCoins(dbHandler)) {
            String sqlAcquire = "UPDATE \"MonsterCardGame\".\"package\" SET \"userid\" = " + userID + " WHERE \"packageid\" = " +
                    "(SELECT \"packageid\" from \"MonsterCardGame\".\"package\"" +
                    "WHERE \"userid\" IS NULL LIMIT 1) RETURNING (SELECT \"packageid\" from \"MonsterCardGame\".\"package\" " +
                    "                WHERE \"userid\" IS NULL LIMIT 1);";
            String sqlReduceCoins = "UPDATE \"MonsterCardGame\".user set \"vcoins\"=\"vcoins\"-5 " +
                    "where \"userid\"=" + userID + " AND \"vcoins\" > 0 RETURNING \"vcoins\"";
            Statement stmt2 = dbHandler.connection.createStatement();
            Statement stmt = dbHandler.connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sqlAcquire);
            if (!resultSet.next()){
                confirmation=false;
                System.out.println("No more packages left to buy!!");
            }
            else {
                ResultSet resultSet2 = stmt2.executeQuery(sqlReduceCoins);
                while (resultSet2.next()){
                    vcoins=resultSet2.getInt("vcoins");
                }
            }
        }
        else {
            System.out.println("Not enough coins to buy package");
            confirmation=false;
        }
        mutex.unlock();
        return confirmation;
    }

    public void showAcquiredCards(DatabaseHandler dbHandler) throws SQLException {
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

    public void createDeck(String payload,DatabaseHandler dbHandler) throws SQLException {
        mutex.lock();
        deck=new Deck();
        deck.createCards(payload,dbHandler);
        mutex.unlock();
    }

    private boolean checkCoins(DatabaseHandler dbHandler) throws SQLException {
        String sql="select \"vcoins\" from \"MonsterCardGame\".\"user\""+
                " where \"userid\"="+userID;
        Statement stmt=dbHandler.connection.createStatement();
        ResultSet resultSet=stmt.executeQuery(sql);
        while (resultSet.next()){
            vcoins=resultSet.getInt("vcoins");
        }
        if (vcoins > 0){
            return true;
        }
        return false;
    }

    public void printDeck(){
        if (deck==null){
            System.out.println("Empty Deck!!!");
            return;
        }
        mutex.lock();
        deck.printDeck();
        mutex.unlock();
    }

    public void updateUserData(DatabaseHandler dbHandler,User tempUser) throws IOException, SQLException {
        String sqlUpdate="UPDATE \"MonsterCardGame\".\"user\" " +
                "SET  \"firstname\"="+tempUser.getUsername()+" \"bio\"="+tempUser.getBio()+", \"image\"="
                +tempUser.getImage()+" WHERE \"username\"="+username;
        PreparedStatement preparedStatement=dbHandler.connection.prepareStatement(sqlUpdate);
        bio=tempUser.getBio();
        image=tempUser.getImage();
        preparedStatement.executeUpdate();
    }

}
