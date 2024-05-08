package com.projekt.oop.yourvotematters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import static java.lang.Math.floor;

/**
 * Voting class.
 * Represents a voting object.
 * Implements Serializable interface to allow serialization.
 * @author Marek Čederle
 */
public class Voting implements Serializable {

    private String votingName;
    private UUID votingID;
    private int voteYesCount;
    private int voteNoCount;
    private int voteAbstainCount;
    private int numberOfVotes;
    private int requiredVotesCount;
    /**
     * Status of the voting.
     * True - accepted, false - rejected, null - in progress.
     */
    private Boolean status;

    private VotingMajority votingMajority;

    private ArrayList<Vote> votes;

    /**
     * Constructor for Voting class. Sets default values, generate random UUID and sets input parameters.
     * @param name Voting name.
     * @param requiredVotesCount Required number of votes.
     * @param votingMajority Type of majority required to accept the voting.
     */
    public Voting(String name, int requiredVotesCount, VotingMajority votingMajority) {
        this.votingID = UUID.randomUUID();
        this.votingName = name;
        this.voteYesCount = 0;
        this.voteNoCount = 0;
        this.voteAbstainCount = 0;
        this.numberOfVotes = 0;
        this.requiredVotesCount = requiredVotesCount;
        this.votes = new ArrayList<>();
        this.votingMajority = votingMajority;
        this.status = null;
    }

    /**
     * Add vote to the voting. Increments vote counters and calls handleVotingFinish method.
     * Calls function to serialize votings after adding and updating.
     * @param vote Vote object to be added.
     */
    public void addVote(Vote vote){
        this.votes.add(vote);
        this.numberOfVotes++;
        if(vote.getVotedStatus() == null){
            this.voteAbstainCount++;
        } else if(vote.getVotedStatus()){
            this.voteYesCount++;
        } else {
            this.voteNoCount++;
        }

        this.handleVotingFinish();

        App.getVotingSerializer().updateVoting();
    }

    /**
     * Method to handle voting finish. Checks if the voting has reached required number of votes.
     */
    public void handleVotingFinish(){

        if(this.numberOfVotes == this.requiredVotesCount){
            if(this.votingMajority == VotingMajority.HALF){
                if(this.voteYesCount > floor((double)this.requiredVotesCount / 2)){
                    System.out.println("Voting: " + this.votingName + " has been accepted");
                    this.status = true;
                } else {
                    System.out.println("Voting: " + this.votingName + " has been rejected");
                    this.status = false;
                }
            }
            else if(this.votingMajority == VotingMajority.TWO_THIRDS){
                if(this.voteYesCount > floor((double)this.requiredVotesCount * 2 / 3)){
                    System.out.println("Voting: " + this.votingName + " has been accepted");
                    this.status = true;
                } else {
                    System.out.println("Voting: " + this.votingName + " has been rejected");
                    this.status = false;
                }
            }
            else if(this.votingMajority == VotingMajority.THREE_FIFTHS){
                if(this.voteYesCount > floor((double)this.requiredVotesCount * 3 / 5)){
                    System.out.println("Voting: " + this.votingName + " has been accepted");
                    this.status = true;
                } else {
                    System.out.println("Voting: " + this.votingName + " has been rejected");
                    this.status = false;
                }
            }
            else if(this.votingMajority == VotingMajority.ABSOLUTE){
                if(this.voteYesCount == this.numberOfVotes-this.voteAbstainCount){
                    System.out.println("Voting: " + this.votingName + " has been accepted");
                    this.status = true;
                } else {
                    System.out.println("Voting: " + this.votingName + " has been rejected");
                    this.status = false;
                }
            }
        }
    }

    /**
     * @return votingName Name of the voting.
     */
    public String getVotingName() {
        return votingName;
    }

    /**
     * @return votingID UUID of the voting.
     */
    public UUID getVotingID() {
        return votingID;
    }

    /**
     * @return voteYesCount Number of votes with positive status.
     */
    public int getVoteYesCount() {
        return voteYesCount;
    }

    /**
     * @return voteNoCount Number of votes with negative status.
     */
    public int getVoteNoCount() {
        return voteNoCount;
    }

    /**
     * @return voteAbstainCount Number of votes with abstain status.
     */
    public int getVoteAbstainCount() {
        return voteAbstainCount;
    }

    /**
     * @return numberOfVotes Number of votes.
     */
    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    /**
     * @return requiredVotesCount Required number of votes.
     */
    public int getRequiredVotesCount() {
        return requiredVotesCount;
    }

    /**
     * @return votingMajority Type of majority required to accept the voting.
     */
    public VotingMajority getVotingMajority() {
        return votingMajority;
    }

    /**
     * @return votes List of votes.
     */
    public ArrayList<Vote> getVotes() {
        return votes;
    }

    /**
     * @return status Status of the voting.
     */
    public Boolean getStatus() {
        return status;
    }
}
