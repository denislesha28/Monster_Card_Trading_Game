package com.Monster_Card_Game.cards;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MonsterCard extends Card{
    @JsonCreator
    public MonsterCard(@JsonProperty("Name")String name,@JsonProperty("Damage")int damage,@JsonProperty("Id")String cardID) {
        super(name,damage,cardID);
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
            battleLog+=enemyCard.getName()+" Card won via Monster Type Battle Dragon > Goblin\n";
            return 2;
        }
        else if(this.type==monsters.Dragon && enemyCard.getMonsterType()==monsters.Goblin){
            battleLog+=name+" Card won via Monster Type Battle Dragon > Goblin\n";
            return 1;
        }
        else if(this.type==monsters.FireElve && enemyCard.getMonsterType()==monsters.Dragon){
            battleLog+=name+" Card won via Monster Type Battle FireElve > Dragon\n";
            return 1;
        }
        else if(this.type==monsters.Dragon && enemyCard.getMonsterType()==monsters.FireElve){
            battleLog+=enemyCard.getName()+" Card won via Monster Type Battle FireElve > Dragon\n";
            return 2;
        }
        else if(this.type==monsters.Wizard && enemyCard.getMonsterType()==monsters.Ork){
            battleLog+=name+" Card won via Monster Type Battle Wizard > Ork\n";
            return 1;
        }
        else if(this.type==monsters.Ork && enemyCard.getMonsterType()==monsters.Wizard){
            battleLog+=enemyCard.getName()+" Card won via Monster Type Battle Wizard > Ork\n";
            return 2;
        }
        battleLog+="No Monster Type Advantages were found\n";
        return 0;
    }

    @Override
    public boolean battleCard(Card enemyCard) {
        int temp_dmg=this.damage;
        int enemy_dmg=enemyCard.getDamage();
        if(enemyCard.getMonsterType()==monsters.NONE) {
            if(this.type==monsters.Kraken){
                battleLog+="Kraken automatically won against any Spell Card!";
                return true;
            }
            if(this.type==monsters.Knight && enemyCard.getAttribute()==elements.Water){
                battleLog+="Knights automatically lose against any Water Spell!";
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
                battleLog+=name+" card won against "+enemyCard.getName()+" card via damage calculation: "+name
                        +" dmg "+temp_dmg+" > "+enemyCard.getName()+" dmg "+enemy_dmg;
                return true;
            }
            else if(temp_dmg==enemy_dmg){
                battleLog+=name+" card draw against "+enemyCard.getName()+" card via damage calculation: "+name
                        +" dmg "+temp_dmg+" =  "+enemyCard.getName()+" dmg "+enemy_dmg;
                return false;
            }
            else {
                battleLog+=enemyCard.getName()+" card won against "+name+" card via damage calculation: "+enemyCard.getName()
                        +" dmg "+enemy_dmg+" > "+name+" dmg "+temp_dmg;
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
                    battleLog+=name+" card won against "+enemyCard.getName()+" card via damage calculation: "+name
                            +" dmg "+temp_dmg+" > "+enemyCard.getName()+" dmg "+enemy_dmg;
                    return true;
                }
                else if(temp_dmg == enemy_dmg){
                    battleLog+=name+" card draw against "+enemyCard.getName()+" card via damage calculation: "+name
                            +" dmg "+temp_dmg+" =  "+enemyCard.getName()+" dmg "+enemy_dmg;
                    return false;
                }
            }
            battleLog+=enemyCard.getName()+" card won against "+name+" card via damage calculation: "+enemyCard.getName()
                    +" dmg "+enemy_dmg+" > "+name+" dmg "+temp_dmg;
            return false;
        }
    }
}
