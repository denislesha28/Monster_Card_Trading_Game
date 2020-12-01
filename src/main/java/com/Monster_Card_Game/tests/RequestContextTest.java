package com.Monster_Card_Game.tests;

import com.Monster_Card_Game.server.RequestContext;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class RequestContextTest {
    @Test
    public void testHTTPVerb() throws IOException {
        //Arrange
        RequestContext handler=new RequestContext();
        //Act
        handler.setHTTPHeader("GET /messages HTTP/1.1\n" +
                "Host: localhost:1111\n" +
                "User-Agent: curl/7.71.1\n" +
                "Accept: */*\n" +
                "Content-Type: application/json\n" +
                "Content-Length: 59\n");
        String actual=handler.readHTTPVerb();
        //Assert
        Assert.assertEquals("GET", actual);
    }
    @Test
    public void testRequest() throws IOException {
        //Arrange
        RequestContext handler=new RequestContext();
        //Act
        handler.setHTTPHeader("GET /messages HTTP/1.1\n" +
                "Host: localhost:1111\n" +
                "User-Agent: curl/7.71.1\n" +
                "Accept: */*\n" +
                "Content-Type: application/json\n" +
                "Content-Length: 59\n");
        String actual=handler.readRequest();
        //Assert
        Assert.assertEquals("messages ", actual);
    }
    /* Mocking ware ideal gewesen um das Payload lesen zu testen*/
}
