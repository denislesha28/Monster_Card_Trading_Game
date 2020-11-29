package com.Monster_Card_Game.cards;

import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;

public abstract class Card {
    protected monsters type;
    protected int damage;
    protected elements attribute;
    public  Card(int damage, elements attribute){
        this.damage=damage;
        this.attribute=attribute;
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

    public abstract boolean battleCard(Card enemyCard);
}
