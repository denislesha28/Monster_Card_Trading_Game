package com.company.cards;

public class SpellCard implements Card {
    private int damage;
    private Type attribute;
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Type getAttribute() {
        return attribute;
    }

    public void setAttribute(Type attribute) {
        this.attribute = attribute;
    }

}
