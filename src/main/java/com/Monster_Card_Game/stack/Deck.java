package com.Monster_Card_Game.stack;
import com.Monster_Card_Game.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> deck=new ArrayList(4);
    public void createCards(String payload){
        String regex="(?<=\"), (?=\")";
        String cards[]=payload.split(regex);
        /*if (cards.length<5){
            System.out.println("Not enough cards");
            return;
        }*/
        cards[0]=cards[0].replace("[","");
        cards[cards.length-1]=cards[cards.length-1].replace("]","");
        for (int i=0;i<cards.length;i++){
            cards[i]=cards[i].replace("\"","");
            System.out.println(cards[i]);
        }
    }

}
