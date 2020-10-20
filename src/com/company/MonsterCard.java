package com.company;
enum Type{
    FIRE,
    WATER,
    NORMAL
}
public class MonsterCard {
    private int damage;
    private Type attribute;
    MonsterCard(int damage, Type attribute){
        this.damage=damage;
        this.attribute=attribute;
    }
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
