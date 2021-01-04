package com.Monster_Card_Game.stack;
import com.Monster_Card_Game.cards.Card;
import com.Monster_Card_Game.cards.MonsterCard;
import com.Monster_Card_Game.cards.SpellCard;
import com.Monster_Card_Game.server.DatabaseHandler;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> deck=new ArrayList(4);
    public void createCards(String payload,DatabaseHandler dbHandler) throws SQLException {
        if (deck.size()==4){
            System.out.println("Deck already full!");
            return;
        }
        String regex="(?<=\"), (?=\")";
        String cards[]=payload.split(regex);
        if (cards.length<4){
            System.out.println("Not enough cards");
            return;
        }
        cards[0]=cards[0].replace("[","");
        cards[cards.length-1]=cards[cards.length-1].replace("]","");
        String selectSql="SELECT cardserialid, cardmonster, carddamage, cardname" +
                " FROM \"MonsterCardGame\".\"card\"" +
                " where cardserialid=?";
        System.out.println(selectSql);
        for (int i=0;i<cards.length;i++){
            cards[i]=cards[i].replace("\"","");
            PreparedStatement preparedStatement=dbHandler.connection.prepareStatement(selectSql);
            preparedStatement.setString(1,cards[i]);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int cardDmg=resultSet.getInt("carddamage");
                String cardName=resultSet.getString("cardname");
                String monsterType=resultSet.getString("cardmonster");
                String cardSerialID=resultSet.getString("cardserialid");
                if(monsterType.compareTo("NONE")==0){
                    deck.add(new SpellCard(cardName,cardDmg,cardSerialID));
                }
                else {
                    deck.add(new MonsterCard(cardName,cardDmg,cardSerialID));
                }

            }
            System.out.println(cards[i]);
        }
    }

    public void printDeck(){
        for (int i=0;i<deck.size();i++){
            System.out.println("Card number "+i+" Name: "+deck.get(i).getName()+" Damage: "+deck.get(i).getDamage()
            +" Attribute: "+deck.get(i).getAttribute()+" MonsterType: "+deck.get(i).getMonsterType());
        }
    }

    public Card getCard(int index){
        Card card;
        if (deck.get(index)==null){
            System.out.println("Logical error");
        }
        card = deck.get(index);
        return card;
    }

    public void addCard(Card card){
        deck.add(card);
    }

}
