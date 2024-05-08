package com.projekt.oop.yourvotematters;

import com.projekt.oop.yourvotematters.view.MainPageView;

/**
 * Voter class represents a voter in the system.
 * @author Marek Čederle
 */
public class Voter extends Visitor{

    /**
     * Constructor for Voter class. Calls constructor of {@link Visitor} super class.
     * @param name Name of the voter
     * @param accessLevel Access level of the voter
     */
    public Voter(String name, AccessLevel accessLevel) {
        super(name, accessLevel);
    }

    /**
     * Method for voting. Creates a new Vote object with the given votedStatus and the name of the voter.
     * @param votedStatus Boolean value representing the vote
     * @return Vote Object representing the vote
     */
    public Vote vote(Boolean votedStatus){
        return new Vote(votedStatus, this.getName());
    }

    /**
     * Method for viewing the votings. Overrides the method from the super class to use different access level.
     * @param mainPageView Instance of the MainPageView class
     */
    @Override
    public void viewVoting(MainPageView mainPageView){
        mainPageView.displayViewVoting(mainPageView.getBorderPane(), AccessLevel.VOTER);
    }
}
