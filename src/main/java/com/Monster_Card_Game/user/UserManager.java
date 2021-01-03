package com.Monster_Card_Game.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class UserManager {
    List<User> users=new ArrayList<>();
    private ReentrantLock mutex=new ReentrantLock();

    public int addUser(User user)  {
        mutex.lock();
        users.add(user);
        int temp=users.indexOf(user);
        mutex.unlock();
        return temp;
    }

    public User at(int index){
        return users.get(index);
    }

    public User at(String username){
        for (int i=0;i < users.size(); i++){
            mutex.lock();
            if(users.get(i).getUsername().compareTo(username)==0){
                User temp=users.get(i);
                mutex.unlock();
                return temp;
            }
            mutex.unlock();
        }
        System.out.println("User not logged in");
        return null;
    }


}
