package com.Monster_Card_Game;

import com.Monster_Card_Game.cards.Card;
import com.Monster_Card_Game.cards.MonsterCard;
import com.Monster_Card_Game.cards.SpellCard;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
import com.Monster_Card_Game.server.DatabaseHandler;
import com.Monster_Card_Game.server.JsonSerializer;
import com.Monster_Card_Game.server.RequestContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        int portNumber=10001;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            RequestContext handler = new RequestContext();
            DatabaseHandler dbHandler=new DatabaseHandler();
            JsonSerializer jsonSerializer=new JsonSerializer();
            Socket clientSocket = serverSocket.accept();
            if (clientSocket != null) {
                //System.out.println("Connected");
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String header=handler.readHeader(in);
            String payload=handler.readPayload(in);
            String request=handler.readRequest();
            System.out.println(header+"  "+payload+"  "+"  "+request);
            Card testCard=jsonSerializer.convertStringToObject(payload);
            System.out.println(testCard);

        }catch (IOException | SQLException e){
            System.out.println(e);
        }
    }
}
