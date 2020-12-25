package com.Monster_Card_Game.server;

import java.util.ArrayList;
import java.util.List;

public class TokenGenerator {
    List<String> tokens = new ArrayList<>();
    public String generateToken(String input){
        String token="Basic "+input+"-mtcgToken";
        tokens.add(token);
        return token;
    }

    public boolean authenticate(String input){
        String token="Basic "+input+"-mtcgToken";
        if(tokens.contains(token)){
            return true;
        }
        return false;
    }

    public void deleteToken(String input){
        String token="Basic "+input+"-mtcgToken";
        tokens.remove(token);
    }
}