package com.projekt.oop.yourvotematters;

import com.projekt.oop.yourvotematters.view.MainPageView;

/**
 * Class representing a visitor user. Implements User interface.
 * @author Marek Čederle
 */
public class Visitor implements User{

    private String name;

    private AccessLevel accessLevel;

    /**
     * Constructor for Visitor class.
     * @param name Name of the visitor.
     * @param accessLevel Access level of the visitor.
     */
    public Visitor(String name, AccessLevel accessLevel) {
        this.name = name;
        this.accessLevel = accessLevel;
    }

    /**
     * Method for viewing the votings.
     * @param mainPageView Main page view.
     */
    public void viewVoting(MainPageView mainPageView){
        mainPageView.displayViewVoting(mainPageView.getBorderPane(), AccessLevel.VISITOR);
    }

    /**
     * @return name Name of the visitor.
     */
    public String getName() {
        return name;
    }

    /**
     * @return accessLevel Access level of the visitor.
     */
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
}
