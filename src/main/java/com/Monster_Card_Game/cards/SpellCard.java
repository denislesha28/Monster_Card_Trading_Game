package com.Monster_Card_Game.cards;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpellCard extends Card {
    @JsonCreator
    public SpellCard(@JsonProperty("Name")String name,@JsonProperty("Damage")int damage,@JsonProperty("Id")String cardID) {
        super(name,damage,cardID);
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
