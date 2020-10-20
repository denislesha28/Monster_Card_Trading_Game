package com.company.cards;
//import com.company.enums.elements;
enum Type{
    FIRE,
    WATER,
    NORMAL
}
public interface Card {
    int getDamage();

    void setDamage(int damage);

    Type getAttribute();

    void setAttribute(Type attribute);
}
