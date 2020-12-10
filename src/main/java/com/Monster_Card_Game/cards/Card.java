package com.Monster_Card_Game.cards;

import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;

public abstract class Card {
    protected monsters type;
    protected int damage;
    protected elements attribute;
    protected String name;
    protected String cardID;
    public Card(String name,int damage){
        this.name=name;
        this.damage=damage;
        if(name.contains("Water")){
            attribute=elements.Water;
        }
        else if(name.contains("Fire")){
            attribute=elements.Fire;
        }
        else{
            attribute=elements.Normal;
        }
        cardID="empty";
        type=monsters.NONE;
    }
    public int getDamage() {
        return damage;
    }

    public elements getAttribute(){
        return attribute;
    }

    public monsters getMonsterType(){
        return type;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    protected int battleElements(Card enemyCard){
        //General Declaration - > 1 - Effective , 2 - > not effective , 0 - > no effect
        if(this.getAttribute()==elements.Fire && enemyCard.getAttribute()==elements.Normal){
            return 1;
        }
        else if(this.getAttribute()==elements.Fire && enemyCard.getAttribute()==elements.Water){
            return 2;
        }
        else if(this.getAttribute()==elements.Water && enemyCard.getAttribute()==elements.Fire){
            return 1;
        }
        else if(this.getAttribute()==elements.Water && enemyCard.getAttribute()==elements.Normal){
            return 2;
        }
        else if(this.getAttribute()==elements.Normal && enemyCard.getAttribute()==elements.Water){
            return 1;
        }
        else if(this.getAttribute()==elements.Normal && enemyCard.getAttribute()==elements.Fire){
            return 2;
        }
        else {
            return 0;
        }
    }

    public abstract boolean battleCard(Card enemyCard);
}
