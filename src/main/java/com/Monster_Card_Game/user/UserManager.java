package com.Monster_Card_Game.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    List<User> users=new ArrayList<>();

    public int addUser(User user)  {
        users.add(user);
        return users.indexOf(user);
    }

    public User at(int index){
        return users.get(index);
    }

    public User at(String username){
        for (int i=0;i < users.size(); i++){
            if(users.get(i).getUsername().compareTo(username)==0){
                return users.get(i);
            }
        }
        System.out.println("User not logged in");
        return null;
    }
}
