package com.Monster_Card_Game.tests;

import com.Monster_Card_Game.server.PasswordHasher;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHasherTest {
    @Test
    public void testHashGenerator() throws InvalidKeySpecException, NoSuchAlgorithmException {
        //Arrange
        PasswordHasher passwordHasher=new PasswordHasher();
        String password="istraitor";
        //Act
        String hash=passwordHasher.generateStrongPasswordHash(password);
        boolean check=passwordHasher.validatePassword("istraitor",hash);
        //Assert
        Assert.assertEquals(true,check);
    }
}
