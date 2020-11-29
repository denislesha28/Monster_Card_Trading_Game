package com.Monster_Card_Game.cards;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;

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
