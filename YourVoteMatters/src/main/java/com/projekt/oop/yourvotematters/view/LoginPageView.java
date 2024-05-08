package com.projekt.oop.yourvotematters.view;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * LoginPageView class is responsible for creating the login page view.
 * @author Marek Čederle
 */
public class LoginPageView {

    private Stage stage;
    private Scene scene;
    private GridPane gridPane = new GridPane();

    private Text usernameLabel = new Text("Username");
    private Text passwdLabel = new Text("Password");

    private TextField usernameField = new TextField();
    private PasswordField passwdField = new PasswordField();

    private Button loginButton = new Button("Login");
    private Button clearButton = new Button("Clear");
    private Button registerButton = new Button("Register new account!");

    /**
     * Constructor for the LoginPageView class. Creates the login page view with all the necessary elements.
     * @param primaryStage Primary stage of the application.
     * @param firstTime Boolean value to determine if the stage should be shown.
     */
    public LoginPageView(Stage primaryStage, Boolean firstTime) {
        this.stage = primaryStage;
        stage.setTitle("Login");

        // Setting size for the pane
        gridPane.setMinSize(800, 600);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        // Arranging all the nodes in the grid and adding them to the grid
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwdLabel, 0, 1);
        gridPane.add(passwdField, 1, 1);
        gridPane.add(loginButton, 0, 2);
        gridPane.add(clearButton, 1, 2);
        gridPane.add(registerButton, 2, 2);

        // Setting the width of the columns
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.33);

        // Adding the columns to the grid
        gridPane.getColumnConstraints().addAll(column1, column2, column3);

        //Setting the scene
        scene = new Scene(gridPane, 800, 600);
        stage.setScene(scene);

        // To show the stage only once
        if(firstTime) {
            stage.setResizable(false);
            stage.show();
        }
    }

    /**
     * @return stage Stage of the login page.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @return gridPane GridPane of the login page.
     */
    public GridPane getGridPane() {
        return gridPane;
    }
    /**
     * @return loginButton Button for logging in.
     */
    public Button getLoginButton() {
        return loginButton;
    }
    /**
     * @return clearButton Button for clearing the fields.
     */
    public Button getClearButton() {
        return clearButton;
    }
    /**
     * @return registerButton Button for registering a new account.
     */
    public Button getRegisterButton() {
        return registerButton;
    }

    /**
     * Clears the fields in the login page.
     */
    public void clearFields() {
        usernameField.setText("");
        passwdField.setText("");
    }

    /**
     * @return usernameField TextField for the username.
     */
    public String getUsernameField() {
        return usernameField.getText();
    }
    /**
     * @return passwdField PasswordField for the password.
     */
    public String getPasswordField() {
        return passwdField.getText();
    }

    /**
     * Shows an error message on the login page.
     * @param message Message to be shown.
     */
    public void showError(String message) {
        Label errorLabel = new Label(message);
        getGridPane().add(errorLabel, 1, 4);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> getGridPane().getChildren().remove(errorLabel));
        pause.play();
    }
}