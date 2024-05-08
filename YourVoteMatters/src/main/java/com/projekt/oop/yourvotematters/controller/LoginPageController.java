package com.projekt.oop.yourvotematters.controller;

import com.projekt.oop.yourvotematters.view.LoginPageView;
import com.projekt.oop.yourvotematters.model.LoginPageModel;

/**
 * Controller for the login page.
 * This class is responsible for handling the user input and updating the view
 * based on the user input. It also handles button clicks event. Communicates
 * with the LoginPageModel to perform the necessary operations.
 * @author Marek Čederle
 */
public class LoginPageController {
    private LoginPageView loginPageView;
    private LoginPageModel loginPageModel;

    /**
     * Constructor for the LoginPageController.
     * It contains all the event handlers for the buttons in the LoginPageView.
     * The event handler names are self-explanatory.
     * @param loginPageViewIn LoginPageView object
     * @param loginPageModelIn LoginPageModel object
     */
    public LoginPageController(LoginPageView loginPageViewIn, LoginPageModel loginPageModelIn) {

        this.loginPageView = loginPageViewIn;
        this.loginPageModel = loginPageModelIn; // model

        loginPageView.getLoginButton().setOnAction(e -> {
            String username = loginPageView.getUsernameField();
            String password = loginPageView.getPasswordField();

            if (loginPageModel.findPassword(username, password)) {

                // pass the stage to the login method where the scene is set to the main scene
                loginPageModel.login(loginPageView.getStage());
            }
            else {
                // clear the fields when login not successful
                loginPageView.clearFields();
                loginPageView.showError("Login Failed, please try again!");
            }
        });

        loginPageView.getClearButton().setOnAction(e -> {
            loginPageView.clearFields();
        });

        loginPageView.getRegisterButton().setOnAction(e -> {
            loginPageModel.register(loginPageView.getStage());
        });
    }
}
