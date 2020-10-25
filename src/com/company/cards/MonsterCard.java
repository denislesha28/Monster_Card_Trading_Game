package com.company.cards;

import com.company.enums.elements;
import com.company.enums.monsters;
public class MonsterCard extends Card{
    private monsters type;
    public MonsterCard(int damage, elements attribute,monsters type) {
        super(damage, attribute);
        this.type=type;
    }
    public monsters getMonsterType(){
        return type;
    }
}
