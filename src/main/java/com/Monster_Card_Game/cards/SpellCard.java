package com.Monster_Card_Game.cards;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
public class SpellCard extends Card {
    public SpellCard(int damage, elements attribute) {
        super(damage, attribute);
        type=monsters.NONE;
    }

    @Override
    public boolean battleCard(Card enemyCard) {
        return true;
    }

}
