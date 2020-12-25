package com.Monster_Card_Game.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private int vcoins;
    private String username;
    private String password;

    @JsonCreator
    User(@JsonProperty("Username")String username,@JsonProperty("Password")String password){
        this.username=username;
        this.password=password;
        vcoins=20;
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

}
