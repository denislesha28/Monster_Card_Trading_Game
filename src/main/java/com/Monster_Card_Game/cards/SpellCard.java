package com.Monster_Card_Game.cards;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
public class SpellCard extends Card {
    public SpellCard(String name,int damage) {
        super(name,damage);
    }

    @Override
    public boolean battleCard(Card enemyCard) {
        int temp_dmg=damage;
        int enemy_dmg=enemyCard.getDamage();
        if(enemyCard.getMonsterType()==monsters.Kraken){
            return false;
        }
        if(this.attribute==elements.Water && enemyCard.getMonsterType()==monsters.Knight){
            return true;
        }
        if (battleElements(enemyCard) == 1) {
            temp_dmg *= 2;
            enemy_dmg /= 2;
        } else if (battleElements(enemyCard) == 2) {
            temp_dmg /= 2;
            enemy_dmg *= 2;
        }
        if (temp_dmg > enemy_dmg) {
            return true;
        } else {
            return false;
        }
    }



}
