package com.Monster_Card_Game;

import com.Monster_Card_Game.server.RequestContext;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        int portNumber=1111;
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            RequestContext handler = new RequestContext();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
