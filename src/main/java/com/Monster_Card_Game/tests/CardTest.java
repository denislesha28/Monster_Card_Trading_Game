package com.Monster_Card_Game.tests;

import com.Monster_Card_Game.cards.Card;
import com.Monster_Card_Game.cards.MonsterCard;
import com.Monster_Card_Game.cards.SpellCard;
import com.Monster_Card_Game.enums.elements;
import com.Monster_Card_Game.enums.monsters;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CardTest {
    @Test
    public void testCardType(){
        //Arrange
        Card testCard=new SpellCard("FireSpell", 100,"empty");
        //Act
        elements actual=testCard.getAttribute();
        //Assert
        Assertions.assertEquals(elements.Fire,actual);
    }

    @Test
    public void testCardDamage(){
        //Arrange
        Card testCard=new SpellCard("RandomSpell",100,"empty");
        //Act
        int actual=testCard.getDamage();
        //Assert
        Assertions.assertEquals(100,actual);
    }

    @Test
    public void testMonsterType(){
        //Arrange
        MonsterCard testCard=new MonsterCard("FireElve",100,"empty");
        //Act
        monsters actual=testCard.getMonsterType();
        //Assert
        Assertions.assertEquals(monsters.FireElve,actual);
    }
}
