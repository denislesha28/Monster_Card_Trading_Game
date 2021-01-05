package com.Monster_Card_Game.cards;

import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.deploy.panel.ExceptionListDialog;

public abstract class Card {
    protected monsters type;
    protected int damage;
    protected elements attribute;
    protected String name;
    protected String cardID;
    protected String battleLog="";
    @JsonCreator
    public Card(@JsonProperty("Name")String name,@JsonProperty("Damage")int damage,@JsonProperty("Id")String cardID){
        this.name=name;
        this.damage=damage;
        if(name.contains("Water")){
            attribute=elements.Water;
        }
        else if(name.contains("Fire")){
            attribute=elements.Fire;
        }
        else{
            attribute=elements.Normal;
        }
        this.cardID=cardID;
        type=monsters.NONE;
    }
    public int getDamage() {
        return damage;
    }

    public elements getAttribute(){
        return attribute;
    }

    public monsters getMonsterType(){
        return type;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCardID() { return cardID; }

    public String getBattleLog() { return battleLog; }

    public void resetBattleLog() { battleLog=""; }

    protected int battleElements(Card enemyCard){
        //General Declaration - > 1 - Effective , 2 - > not effective , 0 - > no effect
        if(this.getAttribute()==elements.Fire && enemyCard.getAttribute()==elements.Normal){
            battleLog+=name+" Card advantage via Element Battle Fire is effective against Normal Type\n";
            return 1;
        }
        else if(this.getAttribute()==elements.Fire && enemyCard.getAttribute()==elements.Water){
            battleLog+=enemyCard.getName()+" Card advantage via Element Battle Water is effective against Fire Type\n";
            return 2;
        }
        else if(this.getAttribute()==elements.Water && enemyCard.getAttribute()==elements.Fire){
            battleLog+=name+" Card advantage via Element Battle Water is effective against Fire Type\n";
            return 1;
        }
        else if(this.getAttribute()==elements.Water && enemyCard.getAttribute()==elements.Normal){
            battleLog+=enemyCard.getName()+" Card advantage via Element Battle Normal is effective against Water Type\n";
            return 2;
        }
        else if(this.getAttribute()==elements.Normal && enemyCard.getAttribute()==elements.Water){
            battleLog+=name+" Card advantage via Element Battle Normal is effective against Water Type\n";
            return 1;
        }
        else if(this.getAttribute()==elements.Normal && enemyCard.getAttribute()==elements.Fire){
            battleLog+=enemyCard.getName()+" Card advantage via Element Battle Fire is effective against Normal Type\n";
            return 2;
        }
        else {
            if (battleLog.isEmpty()) {
                battleLog += "No Element Type Advantages were found\n";
            }
            return 0;
        }
    }

    public abstract boolean battleCard(Card enemyCard);
}
