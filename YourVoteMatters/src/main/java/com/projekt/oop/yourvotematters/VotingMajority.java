package com.projekt.oop.yourvotematters;

/**
 * Enum class representing the majority needed to pass a vote.
 * @author Marek Čederle
 */
public enum VotingMajority {
    /**
     * Represents a majority where more than half of the votes are needed to pass.
     */
    HALF,

    /**
     * Represents a majority where more than two thirds of the votes are needed to pass.
     */
    TWO_THIRDS,

    /**
     * Represents a majority where more than three fifths of the votes are needed to pass.
     */
    THREE_FIFTHS,

    /**
     * Represents a majority where all votes are needed to pass.
     */
    ABSOLUTE
}
