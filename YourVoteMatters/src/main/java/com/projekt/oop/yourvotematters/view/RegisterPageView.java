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
 * RegisterPageView class is responsible for creating the view for the registration page.
 * @author Marek Čederle
 */
public class RegisterPageView {

    private Stage stage;
    private Scene scene;
    private GridPane gridPane = new GridPane();

    private Text usernameLabel = new Text("Username");
    private Text passwdLabel = new Text("Password");
    private Text confirmPasswdLabel = new Text("Confirm Password");

    private TextField usernameField = new TextField();
    private PasswordField passwdField = new PasswordField();
    private PasswordField confirmPasswdField = new PasswordField();

    private Button registerButton = new Button("Register");
    private Button clearButton = new Button("Clear");
    private Button backButton = new Button("Back");

    private Label choiceLabel = new Label("Select Access Level");
    private ChoiceBox choiceBox = new ChoiceBox();

    /**
     * Constructor for the RegisterPageView class. Creates the view for the registration page with all text fields, buttons and labels.
     * @param primaryStage Primary stage of the application.
     */
    public RegisterPageView(Stage primaryStage) {

        this.stage = primaryStage;
        stage.setTitle("Register");

        // Setting size for the pane
        gridPane.setMinSize(800, 600);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        // Adding items to the choice box
        choiceBox.getItems().addAll("Admin", "Organizer", "Voter", "Visitor");

        // Arranging all the nodes in the grid
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);
        gridPane.add(passwdLabel, 0, 1);
        gridPane.add(passwdField, 1, 1);
        gridPane.add(confirmPasswdLabel, 0, 2);
        gridPane.add(confirmPasswdField, 1, 2);
        gridPane.add(registerButton, 0, 3);
        gridPane.add(clearButton, 1, 3);
        gridPane.add(backButton, 2, 3);
        gridPane.add(choiceLabel, 2, 0);
        gridPane.add(choiceBox, 2, 1);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.33);

        gridPane.getColumnConstraints().addAll(column1, column2, column3);

        // Setting the scene
        scene = new Scene(gridPane, 800, 600);
        stage.setScene(scene);
    }

    /**
     * @return usernameField Username as a string from the text field.
     */
    public String getUsernameField() {
        return usernameField.getText();
    }

    /**
     * @return passwdField Password as a string from the text field.
     */
    public String getPasswdField() {
        return passwdField.getText();
    }

    /**
     * @return confirmPasswdField Confirm password as a string from the text field.
     */
    public String getConfirmPasswdField() {
        return confirmPasswdField.getText();
    }

    /**
     * @return registerButton Register button.
     */
    public Button getRegisterButton() {
        return registerButton;
    }

    /**
     * @return clearButton Clear button.
     */
    public Button getClearButton() {
        return clearButton;
    }

    /**
     * @return backButton Back button.
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * @return stage Primary stage of the application.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Clears all text fields in the registration page.
     */
    public void clearFields() {
        usernameField.setText("");
        passwdField.setText("");
        confirmPasswdField.setText("");
    }

    /**
     * @return gridPane Grid pane of the registration page.
     */
    public GridPane getGridPane() {
        return gridPane;
    }

    /**
     * @return choiceBox Choice box for selecting access level.
     */
    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    /**
     * Shows an error message on the registration page.
     * @param message Error message to be shown.
     */
    public void showError(String message) {
        Label errorLabel = new Label(message);
        getGridPane().add(errorLabel, 1, 5);

        // Set the max width of the error label to the width of the column
        errorLabel.setMaxWidth(getGridPane().getColumnConstraints().get(1).getPrefWidth());
        errorLabel.setWrapText(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(7));
        pause.setOnFinished(event -> getGridPane().getChildren().remove(errorLabel));
        pause.play();
    }
}
