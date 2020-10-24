package com.company.cards;
import com.company.enums.elements;
enum Type{
    FIRE,
    WATER,
    NORMAL
}
public abstract class Card {
    private int damage;
    private elements attribute;
    public  Card(int damage, elements attribute){
        this.damage=damage;
        this.attribute=attribute;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public elements getAttribute(){
        return attribute;
    }

    public void setAttribute(elements attribute) {
        this.attribute = attribute;
    }
}
