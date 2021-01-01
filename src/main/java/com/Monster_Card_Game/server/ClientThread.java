package com.Monster_Card_Game.server;

import com.Monster_Card_Game.stack.PackageHandler;
import com.Monster_Card_Game.user.User;
import com.Monster_Card_Game.user.UserManager;
import sun.misc.Cleaner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientThread extends Thread {
    DatabaseHandler dbHandler;
    RequestContext handler;
    JsonSerializer jsonSerializer;
    TokenGenerator tokenGenerator;
    PrintWriter out;
    BufferedReader in;
    UserManager userManager;

    public ClientThread(Socket clientSocket,UserManager userManager) throws SQLException, IOException {
        dbHandler = new DatabaseHandler();
        handler = new RequestContext();
        jsonSerializer = new JsonSerializer();
        tokenGenerator = new TokenGenerator();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.userManager = userManager;
        handler = new RequestContext();
    }

    @Override
    public void run() {
            try {
                String header = handler.readHeader(in);
                String payload = handler.readPayload(in);
                String request = handler.readRequest();
                System.out.println(payload + "\n" + request);
                if (request.compareTo("users") == 0) {
                    User user = jsonSerializer.convertUserToObject(payload);
                    dbHandler.createUser(user.getUsername(), user.getPassword());
                    out.println(handler.ServerResponse);
                    out.flush();
                } else if (request.compareTo("sessions") == 0) {
                    int position = userManager.addUser(jsonSerializer.convertUserToObject(payload));
                    if (dbHandler.validateUser(userManager.at(position).getUsername(), userManager.at(position).getPassword())) {
                        tokenGenerator.generateToken(userManager.at(position).getUsername());
                    } else {
                        System.out.println("Wrong username or password");
                    }
                    out.println(handler.ServerResponse);
                    out.flush();
                } else if (request.compareTo("packages") == 0) {
                    boolean check = tokenGenerator.authenticate("admin");
                    if (!check) {
                        System.out.println("Admin privilege is required!");
                    }else {
                        PackageHandler packageHandler = new PackageHandler();
                        packageHandler.generatePackage(payload, dbHandler);
                    }
                    out.println(handler.ServerResponse);
                    out.flush();
                } else if (request.compareTo("transactions/packages") == 0) {
                    String username = tokenGenerator.returnUserFromToken(header);
                    if (userManager.at(username) == null) {
                        System.out.println("Not logged in");
                    }
                    if (userManager.at(username).acquirePackage(dbHandler)) {
                        System.out.println(username + " acquired a package");
                    } else {
                        System.out.println("There are no more packages left");
                    }
                    out.println(handler.ServerResponse);
                    out.flush();
                } else if (request.compareTo("cards") == 0) {
                    String username = tokenGenerator.returnUserFromToken(header);
                    if (userManager.at(username) == null) {
                        System.out.println("Not logged in");
                    }
                    else {
                        System.out.println("User: " + username + " cards:");
                        userManager.at(username).showAcquiredCards(dbHandler);
                    }
                    out.println(handler.ServerResponse);
                    out.flush();
                } else if (request.compareTo("deck") == 0) {
                    if (handler.readHTTPVerb().compareTo("GET") == 0) {
                        String username = tokenGenerator.returnUserFromToken(header);
                        if (userManager.at(username) == null) {
                            System.out.println("Not logged in");
                        }
                        else {
                            userManager.at(username).printDeck();
                        }
                        out.println(handler.ServerResponse);
                        out.flush();
                    } else {
                        String username = tokenGenerator.returnUserFromToken(header);
                        if (userManager.at(username) == null) {
                            System.out.println("Not logged in");
                        }
                        else {
                            userManager.at(username).createDeck(payload, dbHandler);
                        }
                        out.println(handler.ServerResponse);
                        out.flush();
                    }
                }
            } catch (IOException | SQLException | InvalidKeySpecException | NoSuchAlgorithmException e) {
                System.out.println(e);
            }
    }
}

