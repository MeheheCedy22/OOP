package com.projekt.oop.yourvotematters.model;

import com.projekt.oop.yourvotematters.controller.LoginPageController;
import com.projekt.oop.yourvotematters.view.LoginPageView;
import javafx.stage.Stage;

import java.io.*;

/**
 * RegisterPageModel class is responsible for the logic of the register page.
 * @author Marek Čederle
 */
public class RegisterPageModel {

    /**
     * Method for registering a new user.
     * It checks if the username is not already taken, if the password and confirm password are the same,
     * if the fields are not empty and if there are no white characters in the string.
     * @param username Username of the new user
     * @param password Password of the new user
     * @param confirmPassword Confirm password of the new user
     * @param pickedOption Access level of the new user
     * @return true if registration was successful, false otherwise
     */
    public Boolean registerUser(String username, String password, String confirmPassword, String pickedOption){
        try {
            // check if password and confirm password are the same
            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match!");
                return false;
            }
            // removed confirm password isEmpty check, it was redundant
            else if(username.isEmpty() || password.isEmpty() || pickedOption == null){
                System.out.println("Fields cannot be empty!");
                return false;
            }
            // check if there are white chars in the middle of the string and if there are, return false
            else if (username.contains(" ") || password.contains(" ") || confirmPassword.contains(" ")){
                System.out.println("Fields cannot contain white characters!");
                return false;
            }

            // check if user already exists
            BufferedReader reader = new BufferedReader(new FileReader("resources/users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String fileUsername = parts[0];
                    if (username.equals(fileUsername)) {
                        System.out.println("User already exists!");
                        System.out.println("Username: " + username);
                        reader.close();
                        return false;
                    }
                }
            }
            reader.close();

            // if user does not exist, write it to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter("resources/users.txt", true));
            writer.write(username + " " + password + " " + pickedOption + "\n");
            writer.close();

            System.out.println("Register Successful");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Confirm Password: " + confirmPassword);
            System.out.println("Access Level: " + pickedOption);
            return true;

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
        }

        System.out.println("Register Failed");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confirmPassword);
        System.out.println("Access Level: " + pickedOption);

        return false;
    }

    /**
     * Method for going back to the login page.
     * @param mainStage Main stage of the application
     */
    public void backToLogin(Stage mainStage){
        LoginPageModel loginPageModel = new LoginPageModel();
        LoginPageController loginPageController = new LoginPageController(new LoginPageView(mainStage, false), loginPageModel);
    }
}
