package com.projekt.oop.yourvotematters.controller;

import com.projekt.oop.yourvotematters.model.RegisterPageModel;
import com.projekt.oop.yourvotematters.view.RegisterPageView;

/**
 * Controller class for the RegisterPageView
 * This class is responsible for handling the user input and updating the view
 * based on the user input. It also handles button clicks event. Communicates
 * with the RegisterPageModel to perform the necessary operations.
 * @author Marek Čederle
 */
public class RegisterPageController {

    private RegisterPageView registerPageView;
    private RegisterPageModel registerPageModel;

    private String pickedOption;

    /**
     * Constructor for the RegisterPageController.
     * It contains all the event handlers for the buttons in the RegisterPageView.
     * The event handler names are self-explanatory.
     * @param registerPageViewIn RegisterPageView object
     * @param registerPageModelIn RegisterPageModel object
     */
    public RegisterPageController(RegisterPageView registerPageViewIn, RegisterPageModel registerPageModelIn) {
        this.registerPageView = registerPageViewIn;
        this.registerPageModel = registerPageModelIn;


        registerPageView.getRegisterButton().setOnAction(e -> {
            String username = registerPageView.getUsernameField();
            String password = registerPageView.getPasswdField();
            String confirmPassword = registerPageView.getConfirmPasswdField();

            if (registerPageModel.registerUser(username, password, confirmPassword, pickedOption)) {
                // after successful registration, go back to the login page
                registerPageModel.backToLogin(registerPageView.getStage());
            }
            else {
                // clear the fields when register not successful
                registerPageView.clearFields();
                registerPageView.showError("Error: Users with that name already exists or fields cannot be empty nor contain a white space !");
            }
        });

        registerPageView.getChoiceBox().setOnAction(e -> {
            pickedOption = (String) registerPageView.getChoiceBox().getValue();
            pickedOption = pickedOption.toUpperCase();
            System.out.println("Picked from choiceBox: " + pickedOption);
        });

        registerPageView.getClearButton().setOnAction(e -> {
            registerPageView.clearFields();
        });

        registerPageView.getBackButton().setOnAction(e -> {
            registerPageModel.backToLogin(registerPageView.getStage());
        });
    }
}
