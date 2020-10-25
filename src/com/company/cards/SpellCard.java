package com.company.cards;
import com.company.enums.elements;
import com.company.enums.monsters;
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
