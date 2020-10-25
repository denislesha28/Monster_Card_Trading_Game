package com.company.cards;
import com.company.enums.elements;
import com.company.enums.monsters;

public class MonsterCard extends Card{
    public MonsterCard(int damage, elements attribute,monsters type) {
        super(damage, attribute);
        this.type=type;
    }

    @Override
    public boolean battleCard(Card enemyCard) {
        if (enemyCard.getMonsterType()==monsters.NONE){
            if(this.getAttribute()==elements.Fire && enemyCard.getAttribute()==elements.Normal){
                this.damage*=2;
            }
        }
        return true;

    }
}
