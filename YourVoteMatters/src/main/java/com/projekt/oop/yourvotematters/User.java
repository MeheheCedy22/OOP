package com.projekt.oop.yourvotematters;

import com.projekt.oop.yourvotematters.view.MainPageView;

/**
 * Interface for User. Represents the user of the application and his basic action - viewing the votings.
 * @author Marek Čederle
 */
public interface User {

    /**
     * Method for viewing the votings.
     * @param mainPageView The view of the main page
     */
    void viewVoting(MainPageView mainPageView);
}
