package com.projekt.oop.yourvotematters;

/**
 * Enum class representing access levels of users.
 * @author Marek Čederle
 */
public enum AccessLevel {
    /**
     * Represents a user with no special permissions.
     */
    VISITOR,

    /**
     * Represents a user who can vote on issues.
     */
    VOTER,

    /**
     * Represents a user who can organize votings and votes in the votings.
     */
    ORGANIZER,

    /**
     * Represents a user with full permissions.
     */
    ADMIN
}