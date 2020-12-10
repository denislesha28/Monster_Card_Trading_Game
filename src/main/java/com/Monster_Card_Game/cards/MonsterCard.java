package com.Monster_Card_Game.cards;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;

public class MonsterCard extends Card{
    public MonsterCard(String name,int damage) {
        super(name,damage);
        if(name.contains("Dragon")){
            type=monsters.Dragon;
        }
        else if(name.contains("Kraken")){
            type=monsters.Kraken;
        }
        else if(name.contains("Goblin")){
            type=monsters.Goblin;
        }
        else if(name.contains("Wizard")){
            type=monsters.Wizard;
        }
        else if(name.contains("Ork")){
            type=monsters.Ork;
        }
        else if(name.contains("Knight")){
            type=monsters.Knight;
        }
        else if(name.contains("FireElve")){
            type=monsters.FireElve;
        }
    }

    private int battleMonsterTypes(Card enemyCard){
        if(this.type==monsters.Goblin && enemyCard.getMonsterType()==monsters.Dragon){
            return 2;
        }
        else if(this.type==monsters.Dragon && enemyCard.getMonsterType()==monsters.Goblin){
            return 1;
        }
        else if(this.type==monsters.FireElve && enemyCard.getMonsterType()==monsters.Dragon){
            return 1;
        }
        else if(this.type==monsters.Dragon && enemyCard.getMonsterType()==monsters.FireElve){
            return 2;
        }
        else if(this.type==monsters.Wizard && enemyCard.getMonsterType()==monsters.Ork){
            return 1;
        }
        else if(this.type==monsters.Ork && enemyCard.getMonsterType()==monsters.Wizard){
            return 2;
        }
        return 0;
    }

    @Override
    public boolean battleCard(Card enemyCard) {
        int temp_dmg=this.damage;
        int enemy_dmg=enemyCard.getDamage();
        if(enemyCard.getMonsterType()==monsters.NONE) {
            if(this.type==monsters.Kraken){
                return true;
            }
            if(this.type==monsters.Knight && enemyCard.getAttribute()==elements.Water){
                return false;
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
            }
            else {
                return false;
            }
        }
        else {
            if(battleMonsterTypes(enemyCard)==1){
                return true;
            }
            else if(battleMonsterTypes(enemyCard)==2){
                return false;
            }
            else {
                if (temp_dmg > enemy_dmg) {
                    return true;
                }
            }
            return false;
        }
    }
}
