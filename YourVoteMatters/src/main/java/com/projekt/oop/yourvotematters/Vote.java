package com.projekt.oop.yourvotematters;
import java.io.Serializable;
import java.util.UUID;

/**
 * Class representing a single vote.
 * @author Marek Čederle
 */
public class Vote implements Serializable {
    private UUID voteID;
    /**
     * Boolean representing the vote status.
     * true - yes, false - no, null - abstain
     */
    private Boolean votedStatus;
    private String voterName;

    /**
     * Constructor for the Vote class. Generates a random UUID for the vote.
     * @param votedStatus Status of the vote.
     * @param voterName Name of the voter.
     */
    public Vote(Boolean votedStatus, String voterName) {
        this.voteID = UUID.randomUUID();
        this.votedStatus = votedStatus;
        this.voterName = voterName;
    }

    /**
     * @return voteID UUID of the vote
     */
    public UUID getVoteID() {
        return voteID;
    }

    /**
     * @return votedStatus Boolean representing the vote status.
     */
    public Boolean getVotedStatus() {
        return votedStatus;
    }

    /**
     * @return voterName Name of the voter.
     */
    public String getVoterName() {
        return voterName;
    }
}
