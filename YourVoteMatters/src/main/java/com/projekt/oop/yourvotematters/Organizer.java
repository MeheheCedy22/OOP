package com.projekt.oop.yourvotematters;

import com.projekt.oop.yourvotematters.view.MainPageView;

/**
 * Class representing an organizer.
 * @author Marek Čederle
 */
public class Organizer extends Voter{

    /**
     * Constructor of the class. Calls the constructor of the superclass.
     * @param name Name of the organizer
     * @param accessLevel Access level of the organizer
     */
    public Organizer(String name, AccessLevel accessLevel) {
        super(name, accessLevel);
    }

    /**
     * Method for creating a voting.
     * @param name Name of the voting
     * @param requiredVotesCount Number of required votes
     * @param votingMajority Majority required to pass the voting
     * @return {@link Voting} Object representing the voting
     */
    public Voting createVoting(String name, int requiredVotesCount, VotingMajority votingMajority){
        return new Voting(name, requiredVotesCount, votingMajority);
    }

    /**
     * Method for viewing the votings. Overrides the method from the super class to use different access level.
     * @param mainPageView View of the main page
     */
    @Override
    public void viewVoting(MainPageView mainPageView){
        mainPageView.displayViewVoting(mainPageView.getBorderPane(), AccessLevel.ORGANIZER);
    }
}
