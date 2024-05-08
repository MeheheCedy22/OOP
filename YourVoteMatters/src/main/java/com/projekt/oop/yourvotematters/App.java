package com.projekt.oop.yourvotematters;

import com.projekt.oop.yourvotematters.controller.LoginPageController;
import com.projekt.oop.yourvotematters.view.LoginPageView;
import com.projekt.oop.yourvotematters.model.LoginPageModel;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class of the application. It is responsible for starting the GUI and creating the first window.
 * It also contains the current user and the voting serializer as static members.
 * @author Marek Čederle
 */
public class App extends Application {

    public LoginPageModel loginPageModel = new LoginPageModel();

    private static Visitor currentUser = null;
    private static VotingSerializer votingSerializer = new VotingSerializer();

    /**
     * @return currentUser The current user
     */
    public static Visitor getCurrentUser(){
        return currentUser;
    }
    /**
     * @param user The user to be set as the current user
     */
    public static void setCurrentUser(Visitor user){
        currentUser = user;
    }

    /**
     * @return votingSerializer The voting serializer
     */
    public static VotingSerializer getVotingSerializer(){
        return votingSerializer;
    }

    /**
     * start method for the GUI
     * @param primaryStage The primary stage for this application, onto which
     * the application scenes can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        LoginPageController loginPageController = new LoginPageController(new LoginPageView(primaryStage, true), loginPageModel);
    }

    /**
     * Main method of the application that launches the GUI and starts the application.
     * It also prints a message about logging to the console.
     * @param args The command line arguments
     */
    public static void main(String[] args){
        System.out.println("---------------------------------------------");
        System.out.println("Verbose logging of application");
        System.out.println("---------------------------------------------");
        launch(args);
    }
}
