package com.projekt.oop.yourvotematters.model;

import com.projekt.oop.yourvotematters.controller.*;
import com.projekt.oop.yourvotematters.view.*;
import com.projekt.oop.yourvotematters.*;
import com.projekt.oop.yourvotematters.model.MainPageModel;

import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that handles the logic of the login page.
 * @author Marek Čederle
 */
public class LoginPageModel {

    /**
     * This method reads the users.txt file and checks if the username and password are correct.
     * @param username Username of the user.
     * @param password Password of the user.
     * @return true if the username and password are correct, false otherwise
     */
    public Boolean findPassword(String username, String password){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String fileUsername = parts[0];
                    String filePassword = parts[1];
                    if (username.equals(fileUsername) && password.equals(filePassword)) {

                        Visitor user = createUser(username, parts[2]);

                        if(user != null){
                            App.setCurrentUser(user);
                        }
                        else {
                            System.out.println("Unexpected problem with user type! Login failed!");
                            System.out.println("Username: " + username);
                            System.out.println("Password: " + password);
                            return false;
                        }
                        System.out.println("Login Successful");
                        System.out.println("Username: " + username);
                        System.out.println("Password: " + password);
                        reader.close();

                        return true;
                    }
                }
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
        }

        System.out.println("Login Failed");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        return false;
    }

    /**
     * This method creates a {@link Visitor} object based on the type of access
     * @param name Name of the user
     * @param typeOfAccess Type of access of the user
     * @return {@link Visitor} object based on the type of access
     */
    public Visitor createUser(String name, String typeOfAccess){
        return switch (typeOfAccess) {
            case "ADMIN" -> new Admin(name, AccessLevel.ADMIN);
            case "ORGANIZER" -> new Organizer(name, AccessLevel.ORGANIZER);
            case "VOTER" -> new Voter(name, AccessLevel.VOTER);
            case "VISITOR" -> new Visitor(name, AccessLevel.VISITOR);
            default -> null;
        };
    }

    /**
     * This method sets the main scene after successful login.
     * @param mainStage Main stage of the application
     */
    public void login(Stage mainStage){
        MainPageModel mainPageModel = new MainPageModel();

        // Save a reference tro object to prevent garbage collector from deleting it
        MainPageController mainPageController = new MainPageController(new MainPageView(mainStage), mainPageModel);
    }

    /**
     * This method sets the register scene after clicking on the register button.
     * @param mainStage Main stage of the application
     */
    public void register(Stage mainStage){
        RegisterPageModel registerPageModel = new RegisterPageModel();

        // Save a reference tro object to prevent garbage collector from deleting it
        RegisterPageController registerPageController = new RegisterPageController(new RegisterPageView(mainStage), registerPageModel);
    }
}
