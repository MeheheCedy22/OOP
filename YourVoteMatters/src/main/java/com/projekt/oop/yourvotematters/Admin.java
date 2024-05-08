package com.projekt.oop.yourvotematters;

import com.projekt.oop.yourvotematters.view.MainPageView;

/**
 * Class representing an admin.
 * @author Marek Čederle
 */
public class Admin extends Organizer {
    /**
     * Constructor for the Admin class. Calls the constructor of the super class.
     * @param name Name of the admin.
     * @param accessLevel Access level of the admin.
     */
    public Admin(String name, AccessLevel accessLevel) {
        super(name, accessLevel);
    }

    /**
     * Method for deleting a voting.
     * @param voting Voting to be deleted.
     */
    public void deleteVoting(Voting voting){
        App.getVotingSerializer().removeVoting(voting);
    }

    /**
     * Method for viewing the votings. Overrides the method from the super class to use different access level.
     * @param mainPageView Main page view to be displayed.
     */
    @Override
    public void viewVoting(MainPageView mainPageView){
        mainPageView.displayViewVoting(mainPageView.getBorderPane(), AccessLevel.ADMIN);
    }
}
