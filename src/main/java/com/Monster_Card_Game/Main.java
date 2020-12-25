package com.Monster_Card_Game;

import com.Monster_Card_Game.cards.Card;
import com.Monster_Card_Game.cards.MonsterCard;
import com.Monster_Card_Game.cards.SpellCard;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
import com.Monster_Card_Game.server.DatabaseHandler;
import com.Monster_Card_Game.server.JsonSerializer;
import com.Monster_Card_Game.server.RequestContext;
import com.Monster_Card_Game.server.TokenGenerator;
import com.Monster_Card_Game.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        int portNumber=10001;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            RequestContext handler = new RequestContext();
            DatabaseHandler dbHandler=new DatabaseHandler();
            JsonSerializer jsonSerializer=new JsonSerializer();
            TokenGenerator tokenGenerator=new TokenGenerator();
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (clientSocket != null) {
                    //System.out.println("Connected");
                }
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String header = handler.readHeader(in);
                String payload = handler.readPayload(in);
                String request = handler.readRequest();
                System.out.println(header + "  " + payload + "  " + "  " + request);
                /*
                Card testCard=jsonSerializer.convertStringToObject(payload);
                System.out.println(testCard);
                System.out.println(testCard.getName()+"  "+testCard.getMonsterType()+"  "+testCard.getAttribute());
                 */
                if (request.compareTo("users") == 0) {
                    User user = jsonSerializer.convertUserToObject(payload);
                    dbHandler.createUser(user.getUsername(), user.getPassword());
                    user = null;
                } else if (request.compareTo("sessions") == 0) {
                    User user = jsonSerializer.convertUserToObject(payload);
                    if (dbHandler.validateUser(user.getUsername(), user.getPassword())) {
                        tokenGenerator.generateToken(user.getUsername());
                    }
                    System.out.println(dbHandler.validateUser(user.getUsername(), user.getPassword()));
                    user = null;
                }
            }

        }catch (IOException | SQLException | InvalidKeySpecException | NoSuchAlgorithmException e){
            System.out.println(e);
        }
    }
}
