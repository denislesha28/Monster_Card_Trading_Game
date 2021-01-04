package com.Monster_Card_Game.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class UserManager {
    List<User> users=new ArrayList<>();
    Queue<User> battleQueue=new LinkedList();
    private ReentrantLock mutex=new ReentrantLock();

    public int addUser(User user)  {
        mutex.lock();
        users.add(user);
        if (users.contains(user)){
            mutex.unlock();
            return users.indexOf(user);
        }
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

    public void queueUp(User user){
        mutex.lock();
        if (battleQueue.contains(user)){
            mutex.unlock();
            return;
        }
        battleQueue.add(user);
        mutex.unlock();
    }

    public void startBattle(){
        mutex.lock();
        User user1=battleQueue.poll();
        User user2=battleQueue.poll();
        mutex.unlock();
        if(user1==null || user2==null){
            System.out.println("Waiting for other Users to queue up");
            return;
        }
        else {
            System.out.println("Starting Battle between: "+user1.getUsername()+" and "+user2.getUsername());
        }
    }


}
