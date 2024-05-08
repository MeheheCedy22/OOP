package com.projekt.oop.yourvotematters.view;

import com.projekt.oop.yourvotematters.AccessLevel;
import com.projekt.oop.yourvotematters.App;
import com.projekt.oop.yourvotematters.Voting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class is responsible for displaying the main page of the application.
 * It contains the main menu, create voting form, view voting list, voting information and voting form.
 * @author Marek Čederle
 */
public class MainPageView {

    /* MAIN PAGE DEFAULT VIEW */
    private Stage stage;
    private Scene scene;
    private BorderPane borderPane = new BorderPane();

    private Button exitButton = new Button("Exit");
    private Button logOutButton = new Button("Log Out");

    private Label optionLabel = new Label("Select an option:");
    private ChoiceBox choiceBox = new ChoiceBox();


    /* DISPLAY CREATE VOTING */
    private Text createVotingNameLabel = new Text("Voting name");
    private Text createVotesRequiredLabel = new Text("Votes required");
    private Button createButton = new Button("Create");
    private Button clearButton = new Button("Clear");
    private TextField createVotingName = new TextField();
    private TextField createVotesRequired = new TextField();
    private Text createVotingTypeLabel = new Text("Voting type");
    private ChoiceBox createVotingType = new ChoiceBox();
    private GridPane gridPane;

    /*
        DISPLAY VIEW VOTING,
        delete, list votes, vote - will be part of this
    */

    private ListView<String> votingListView = new ListView<>();
    private Button selectVotingButton = new Button("View voting information");

    private Button voteButton = new Button("Vote");
    private Button deleteVotingButton = new Button("Delete voting");

    private Button backToVotingListButton = new Button("Back");

    private Button yesButton = new Button("Yes");
    private Button noButton = new Button("No");
    private Button abstainButton = new Button("Abstain");
    private Text votingNameInVoting;

    /**
     * Constructor for the main page view. Creates the main menu view with all the necessary elements.
     * @param primaryStage Primary stage of the application.
     */
    public MainPageView(Stage primaryStage) {

        this.stage = primaryStage;
        stage.setTitle("YourVoteMatters");

        borderPane.setMinSize(800, 600);

        VBox vbox = new VBox();
        vbox.setMinWidth(200);
        borderPane.setLeft(vbox);

        vbox.getChildren().addAll(exitButton, logOutButton, optionLabel, choiceBox);

        choiceBox.getItems().addAll("Create voting", "View voting");

        scene = new Scene(borderPane, 800, 600);
        stage.setScene(scene);

        // display the view voting as default
        displayViewVoting(borderPane, App.getCurrentUser().getAccessLevel());
    }

    /**
     * Displays the create voting form.
     * @param borderPane BorderPane to display the form in.
     */
    public void displayCreateVoting(BorderPane borderPane){
        gridPane = new GridPane();

        // fix for string duplication in choicebox when switching between screens in one app instance
        createVotingType = null;
        createVotingType = new ChoiceBox();

        // Setting size for the pane
        gridPane.setMinSize(800, 600);

        //S etting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //S etting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);


        createVotingType.getItems().addAll("Half majority", "Two-thirds majority", "Three-fifths majority", "Absolute majority");

        // Arranging all the nodes in the grid
        gridPane.add(createVotingNameLabel, 0, 0);
        gridPane.add(createVotesRequiredLabel, 1, 0);
        gridPane.add(createVotingName, 0, 1);
        gridPane.add(createVotesRequired, 1, 1);
        gridPane.add(createButton, 0, 2);
        gridPane.add(clearButton, 1, 2);
        gridPane.add(createVotingTypeLabel, 2, 0);
        gridPane.add(createVotingType, 2, 1);

        // Setting the scene
        borderPane.setCenter(gridPane);
    }

    /**
     * Displays the view for list of votings and buttons for voting, deleting voting and accessing voting information.
     * @param borderPane BorderPane to display the view in.
     * @param accessLevel Access level of the user.
     */
    public void displayViewVoting(BorderPane borderPane, AccessLevel accessLevel)
    {
        gridPane = new GridPane();

        // fix for string duplication in listview when switching between screens in one app instance
        votingListView = null;
        votingListView = new ListView<>();

        // Setting size for the pane
        gridPane.setMinSize(800, 600);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        // setting the list view with voting names
        ObservableList<String> listViewVotings = FXCollections.observableArrayList();

        for (Voting voting : App.getVotingSerializer().getVotings()) {
            listViewVotings.add(voting.getVotingName());
        }

        votingListView.setItems(listViewVotings);


        gridPane.add(votingListView, 0, 0);
        gridPane.add(selectVotingButton, 0, 1);

        if (accessLevel.equals(AccessLevel.VOTER) || accessLevel.equals(AccessLevel.ORGANIZER) || accessLevel.equals(AccessLevel.ADMIN)) {
            gridPane.add(voteButton, 1, 1);
        }

        if (accessLevel.equals(AccessLevel.ADMIN)) {
            gridPane.add(deleteVotingButton, 2, 1);
        }

        // setting the scene
        borderPane.setCenter(gridPane);
    }

    /**
     * Displays the information about selected voting.
     * @param borderPane BorderPane to display the information in.
     * @param inVoting Voting to display information about.
     */
    public void displayVotingInfo(BorderPane borderPane, Voting inVoting) {
        gridPane = new GridPane();

        // Setting size for the pane
        gridPane.setMinSize(800, 600);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        Text votingName = new Text("Voting name: " + inVoting.getVotingName());
        Text votingID = new Text("Voting ID: " + inVoting.getVotingID());
        Text voteYesCount = new Text("Yes votes: " + inVoting.getVoteYesCount());
        Text voteNoCount = new Text("No votes: " + inVoting.getVoteNoCount());
        Text voteAbstainCount = new Text("Abstain votes: " + inVoting.getVoteAbstainCount());
        Text numberOfVotes = new Text("Number of votes: " + inVoting.getNumberOfVotes());
        Text requiredVotesCount = new Text("Required votes: " + inVoting.getRequiredVotesCount());
        Text votingMajority = new Text("Voting majority: " + inVoting.getVotingMajority());

        // voting status
        Boolean inVotingStatus = inVoting.getStatus();
        String votingStatusString;
        if (inVotingStatus == null) {
            votingStatusString = "Voting is still in progress";
        } else if (inVotingStatus)
        {
            votingStatusString = "Voting was successful";
        } else {
            votingStatusString = "Voting was not successful";
        }

        Text votingStatus = new Text("Voting status: " + votingStatusString);


        gridPane.add(votingName, 0, 0);
        gridPane.add(votingID, 0, 1);
        gridPane.add(voteYesCount, 0, 2);
        gridPane.add(voteNoCount, 0, 3);
        gridPane.add(voteAbstainCount, 0, 4);
        gridPane.add(numberOfVotes, 0, 5);
        gridPane.add(requiredVotesCount, 0, 6);
        gridPane.add(votingMajority, 0, 7);
        gridPane.add(votingStatus, 0, 8);
        gridPane.add(backToVotingListButton, 0, 9);

        // Setting the scene
        borderPane.setCenter(gridPane);
    }

    /**
     * Displays the form for vote operation in selected voting.
     * @param borderPane BorderPane to display the form in.
     * @param inVoting Voting to vote in.
     */
    public void displayVoteInVoting(BorderPane borderPane, Voting inVoting)
    {
        gridPane = new GridPane();

        // Setting size for the pane
        gridPane.setMinSize(800, 600);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);


        votingNameInVoting = new Text(inVoting.getVotingName());

        gridPane.add(votingNameInVoting, 0, 0, 4, 1);
        gridPane.add(yesButton, 0, 1);
        gridPane.add(noButton, 1, 1);
        gridPane.add(abstainButton, 2,1);
        gridPane.add(backToVotingListButton, 3, 1);


        // Setting the scene
        borderPane.setCenter(gridPane);
    }

    /**
     * @return stage Stage of the application.
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @return borderPane BorderPane of the application.
     */
    public BorderPane getBorderPane() {
        return borderPane;
    }

    /**
     * @return exitButton Exit button.
     */
    public Button getExitButton() {
        return exitButton;
    }
    /**
     * @return logOutButton Log out button.
     */
    public Button getLogOutButton() {
        return logOutButton;
    }
    /**
     * @return choiceBox ChoiceBox for selecting the main menu option.
     */
    public ChoiceBox getChoiceBox() {
        return choiceBox;
    }

    /* DISPLAY CREATE VOTING */
    /**
     * @return createButton Create button.
     */
    public Button getCreateButton() {
        return createButton;
    }
    /**
     * @return clearButton Clear button.
     */
    public Button getClearButton() {
        return clearButton;
    }
    /**
     * @return gridPane GridPane.
     */
    public GridPane getGridPane() {
        return gridPane;
    }
    /**
     * @return createVotingName Name of the voting to create.
     */
    public TextField getCreateVotingName() {
        return createVotingName;
    }
    /**
     * @return createVotesRequired Votes required for the voting.
     */
    public TextField getCreateVotesRequired() {
        return createVotesRequired;
    }
    /**
     * Clears the fields in the create voting form.
     */
    public void clearCreateVotingFields() {
        createVotingName.setText("");
        createVotesRequired.setText("");

    }
    /**
     * @return createVotingType ChoiceBox for selecting the voting type.
     */
    public ChoiceBox getCreateVotingType() {
        return createVotingType;
    }

    /* DISPLAY VIEW VOTING */
    /**
     * @return votingListView ListView of votings.
     */
    public ListView<String> getVotingListView() {
        return votingListView;
    }

    /**
     * @return selectVotingButton Button for selecting the voting.
     */
    public Button getSelectVotingButton() {
        return selectVotingButton;
    }

    /**
     * @return backToVotingListButton Button for going back to the voting list.
     */
    public Button getBackToVotingListButton() {
        return backToVotingListButton;
    }

    /**
     * @return voteButton Vote button.
     */
    public Button getVoteButton() {
        return voteButton;
    }

    /**
     * @return yesButton Yes button.
     */
    public Button getYesButton() {
        return yesButton;
    }

    /**
     * @return noButton No button.
     */
    public Button getNoButton() {
        return noButton;
    }

    /**
     * @return abstainButton Abstain button.
     */
    public Button getAbstainButton() {
        return abstainButton;
    }

    /**
     * @return deleteVotingButton Delete voting button.
     */
    public Button getDeleteVotingButton() {
        return deleteVotingButton;
    }

    /**
     * @return votingNameInVoting Name of the voting in the voting view.
     */
    public Text getVotingNameInVoting() {
        return votingNameInVoting;
    }
}
