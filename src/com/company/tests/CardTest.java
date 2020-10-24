package com.company.tests;

import com.company.cards.MonsterCard;
import com.company.enums.elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CardTest {
    @Test
    public void testCardType(){
        //Arrange
        MonsterCard testCard=new MonsterCard(100, elements.FIRE);
        //Act
        elements actual=testCard.getAttribute();
        //Assert
        Assertions.assertEquals(elements.FIRE,actual);
    }

    @Test
    public void testCardDamage(){
        //Arrange
        MonsterCard testCard=new MonsterCard(100,elements.UKNOWN);
        //Act
        int actual=testCard.getDamage();
        //Assert
        Assertions.assertEquals(100,actual);
    }
}
