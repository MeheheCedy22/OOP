package com.projekt.oop.yourvotematters.controller;

import com.projekt.oop.yourvotematters.*;
import com.projekt.oop.yourvotematters.model.MainPageModel;
import com.projekt.oop.yourvotematters.view.MainPageView;

/**
 * This class is responsible for handling the user input and updating the view
 * based on the user input. Communicates with the {@link MainPageModel} to perform the necessary operations.
 * @author Marek Čederle
 */
public class MainPageController {
    private MainPageView mainPageView;

    private MainPageModel mainPageModel;

    /**
     * Constructor for the MainPageController.
     * It contains all the event handlers for the buttons in the MainPageView.
     * The event handler names are self-explanatory.
     * @param mainPageViewIn MainPageView object
     * @param mainPageModelIn MainPageModel object
     */
    public MainPageController(MainPageView mainPageViewIn, MainPageModel mainPageModelIn) {

        this.mainPageView = mainPageViewIn;
        this.mainPageModel = mainPageModelIn; // model

        mainPageView.getExitButton().setOnAction(e -> {
            System.exit(0);
        });

        mainPageView.getLogOutButton().setOnAction(e -> {
            System.out.println("Logged out");
            mainPageModel.logOut(mainPageView.getStage());

        });

        mainPageView.getChoiceBox().setOnAction(e -> {
            String pickedOption = (String) mainPageView.getChoiceBox().getValue();
            System.out.println("Picked from choiceBox: "+ pickedOption);

            mainPageModel.showViewBasedOnChoiceBoxPick(mainPageView, pickedOption);
        });

        /* DELETE VOTING HANDLERS */
        mainPageView.getDeleteVotingButton().setOnAction(e -> {
            Voting local_voting = mainPageModel.getSelectedVoting(mainPageView);

            mainPageModel.checkDeleteVotingPermissions(mainPageView, local_voting);

            // update to list view
            mainPageView.getGridPane().getChildren().clear();
            mainPageView.displayViewVoting(mainPageView.getBorderPane(), App.getCurrentUser().getAccessLevel());
        });



        /* CREATE VOTING HANDLERS */
        mainPageView.getCreateButton().setOnAction(e -> {
            mainPageModel.createVotingBasedOnPermission(mainPageView);
        });

        /*
            DISPLAY VIEW VOTING and VOTE HANDLERS
            voting info, vote button, buttons for voting
        */

        mainPageView.getSelectVotingButton().setOnAction(e -> {
            Voting local_voting = mainPageModel.getSelectedVoting(mainPageView);
            if (local_voting == null) {
                return;
            }
            System.out.println("Selected voting: " + local_voting.getVotingName());
            mainPageView.displayVotingInfo(mainPageView.getBorderPane(), local_voting);
        });

        mainPageView.getVoteButton().setOnAction( e-> {
            Voting local_voting = mainPageModel.getSelectedVoting(mainPageView);
            if (local_voting == null) {
                return;
            }
            System.out.println("Selected voting: " + local_voting.getVotingName());

            mainPageModel.displayVoteBasedOnPermissions(mainPageView, local_voting);
        });

        mainPageView.getBackToVotingListButton().setOnAction(e -> {
            mainPageView.displayViewVoting(mainPageView.getBorderPane(), App.getCurrentUser().getAccessLevel());
        });

        mainPageView.getClearButton().setOnAction(e -> {
            mainPageView.clearCreateVotingFields();
        });

        mainPageView.getYesButton().setOnAction(e -> {
            mainPageModel.voteButtonsAction(mainPageView, true);
        });

        mainPageView.getNoButton().setOnAction(e -> {
            mainPageModel.voteButtonsAction(mainPageView, false);
        });

        mainPageView.getAbstainButton().setOnAction(e -> {
            mainPageModel.voteButtonsAction(mainPageView, null);
        });
    }
}
