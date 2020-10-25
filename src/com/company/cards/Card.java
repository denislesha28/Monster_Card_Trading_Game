package com.company.cards;

import com.company.enums.elements;
import com.company.enums.monsters;

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
