package com.projekt.oop.yourvotematters.model;

import com.projekt.oop.yourvotematters.*;
import com.projekt.oop.yourvotematters.controller.LoginPageController;
import com.projekt.oop.yourvotematters.view.LoginPageView;
import com.projekt.oop.yourvotematters.view.MainPageView;
import javafx.stage.Stage;

/**
 * This class is responsible for the logic of the main page.
 * @author Marek Čederle
 */
public class MainPageModel {

    /**
     * This methiod logs out the current user and displays the login page.
     * @param primaryStage Primary stage of the application.
     */
    public void logOut(Stage primaryStage){
        App.setCurrentUser(null);
        LoginPageModel loginPageModel = new LoginPageModel();
        LoginPageController loginPageController = new LoginPageController(new LoginPageView(primaryStage, false), loginPageModel);
    }

    /**
     * Returns the {@link Voting} object that is selected in the {@link MainPageView} when clicked on the list view.
     * @param mainPageView The main page view.
     * @return local_voting The selected voting.
     */
    public Voting getSelectedVoting(MainPageView mainPageView) {
        String votingName = mainPageView.getVotingListView().getSelectionModel().getSelectedItem();
        if (votingName == null) {
            System.out.println("No voting selected");
            return null;
        }
        Voting local_voting = App.getVotingSerializer().getVotingByName(votingName);
        if (local_voting == null) {
            System.out.println("No voting found with the name: " + votingName);
        }
        return local_voting;
    }

    /**
     * This method displays the view based on the choice box pick.
     * It also checks the permissions of the user when trying to create a voting and show
     * the view for that.
     * @param mainPageView The main page view.
     * @param pickedOption The picked option from the choice box.
     */
    public void showViewBasedOnChoiceBoxPick(MainPageView mainPageView, String pickedOption) {
        switch (pickedOption) {
            case "Create voting":
                if(AccessLevel.ORGANIZER.equals(App.getCurrentUser().getAccessLevel()) || AccessLevel.ADMIN.equals(App.getCurrentUser().getAccessLevel())) {
                    // this just displays the create voting form, only if user have permission
                    mainPageView.displayCreateVoting(mainPageView.getBorderPane());
                }
                else {
                    System.out.println("---------------------------------------------");
                    System.out.println("You do not have permission to create a voting");
                    System.out.println("---------------------------------------------");
                }
                break;
            case "View voting":
                // do not need to check access level because everyone can view voting
                App.getCurrentUser().viewVoting(mainPageView);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    /**
     * This method checks the permissions of the user when trying to delete a voting.
     * If permission are sufficient, the voting is deleted.
     * @param mainPageView The main page view.
     * @param local_voting The selected voting.
     */
    public void checkDeleteVotingPermissions(MainPageView mainPageView, Voting local_voting){
        if (local_voting == null) {
            return;
        }
        System.out.println("Selected voting: " + local_voting.getVotingName());

        if(App.getCurrentUser() instanceof Admin) {
            ((Admin) App.getCurrentUser()).deleteVoting(local_voting);
        }
        else {
            System.out.println("You do not have permission to delete a voting");
        }
    }

    /**
     * This method checks if the voting is already finished, meaning that it is not in progress.
     * It also checks if the user already voted in selected voting.
     * @param mainPageView The main page view.
     * @param local_voting The selected voting.
     */
    public void displayVoteBasedOnPermissions(MainPageView mainPageView, Voting local_voting){
        if (local_voting.getStatus()!=null) {
            System.out.println("Voting is already finished");
            return;
        }

        // if the current user already voted, do not let him
        for (Vote vote : local_voting.getVotes()) {
            if (vote.getVoterName().equals(App.getCurrentUser().getName())) {
                System.out.println("You already voted");
                return;
            }
        }

        mainPageView.displayVoteInVoting(mainPageView.getBorderPane(), local_voting);
    }

    /**
     * This method creates a vote based on the vote button clicked.
     * After that it calls the vote method to add the vote to the voting.
     * @param mainPageView The main page view.
     * @param vote The vote button clicked.
     */
    public void voteButtonsAction(MainPageView mainPageView, Boolean vote){
        Vote createdVote = ((Voter) App.getCurrentUser()).vote(vote);

        String votingNameLocal = mainPageView.getVotingNameInVoting().getText();
        Voting votingLocal = App.getVotingSerializer().getVotingByName(votingNameLocal);
        if(vote == null)
        {
            System.out.println("Voted abstain");
        }
        else if(vote)
        {
            System.out.println("Voted yes");
        }
        else
        {
            System.out.println("Voted no");
        }

        // add vote to voting
        votingLocal.addVote(createdVote);

        mainPageView.displayViewVoting(mainPageView.getBorderPane(), App.getCurrentUser().getAccessLevel());
    }

    /**
     * This method is for creating a voting.
     * Firstly, it checks if the entered number of required votes is a number.
     * If yes, it parses it.
     * Next it checks if the voting with the same name is already created.
     * After that it checks if the fields are not empty and if the votes required is greater than 0.
     * If all conditions are met, it creates the voting with selected majority
     * and adds it to the voting list.
     * @param mainPageView The main page view.
     */
    public void createVotingBasedOnPermission(MainPageView mainPageView){
        // trim to remove spaces before and after the name of the voting so that the name is not similar to another name
        String votingName = mainPageView.getCreateVotingName().getText().trim();
        int votesRequired = -1;

        // check if votes required is a number, if yes parse it
        if (!mainPageView.getCreateVotesRequired().getText().isEmpty()) {
            try {
                votesRequired = Integer.parseInt(mainPageView.getCreateVotesRequired().getText());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a number");
                return;
            }
        }

        // check if voting with the same name is already created
        for (Voting forVoting : App.getVotingSerializer().getVotings())
        {
            if(forVoting.getVotingName().equals(votingName))
            {
                System.out.println("Voting with this name already exists!");
                mainPageView.clearCreateVotingFields();
                return;
            }
        }

        String votingType = (String) mainPageView.getCreateVotingType().getValue();

        // osetrenie vstupov
        if(votingName.isEmpty() || votesRequired <= 0 || votingType == null) {
            System.out.println("Please fill all fields, make sure votes required is greater than 0");
            return;
        }

        Voting voting;
        VotingMajority majority = null;

        switch(votingType) {
            case "Half majority":
                majority = VotingMajority.HALF;
                break;
            case "Two-thirds majority":
                majority = VotingMajority.TWO_THIRDS;
                break;
            case "Three-fifths majority":
                majority = VotingMajority.THREE_FIFTHS;
                break;
            case "Absolute majority":
                majority = VotingMajority.ABSOLUTE;
                break;
            default:
        }

        if(App.getCurrentUser() instanceof Admin) {
            voting = ((Admin) App.getCurrentUser()).createVoting(votingName, votesRequired, majority);
        }
        else {
            voting = ((Organizer) App.getCurrentUser()).createVoting(votingName, votesRequired, majority);
        }
        App.getVotingSerializer().addVoting(voting);

        System.out.println("Voting created: " + voting);

        mainPageView.displayViewVoting(mainPageView.getBorderPane(), App.getCurrentUser().getAccessLevel());
    }
}
