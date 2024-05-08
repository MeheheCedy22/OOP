package com.projekt.oop.yourvotematters;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * This class is responsible for serializing and deserializing the votings.
 * @author Marek Čederle
 */
public class VotingSerializer {
    private ArrayList<Voting> votings;

    /**
     * Constructor for the VotingSerializer class. Loads votings from a file.
     */
    public VotingSerializer() {
        loadVotings();
    }

    /**
     * @return votings List of votings.
     */
    public ArrayList<Voting> getVotings() {
        return votings;
    }

    /**
     * @param votings List of votings to set.
     */
    public void setVotings(ArrayList<Voting> votings) {
        this.votings = votings;
        serializeVotings();
    }

    /**
     * Adds a new voting to the list of votings.
     * @param voting Voting to add.
     */
    public void addVoting(Voting voting) {
        this.votings.add(voting);
        serializeVotings();
    }

    /**
     * Removes a voting from the list of votings.
     * @param voting Voting to remove.
     */
    public void removeVoting(Voting voting) {
        this.votings.remove(voting);
        serializeVotings();
    }

    /**
     * Wrapper for serializing votings after updating. Called after Vote is added when voting.
     */
    public void updateVoting() {
        serializeVotings();
    }

    /**
     * This method returns a {@link Voting} object by its name.
     * @param name Name of the voting.
     * @return {@link Voting} object with the given name.
     */
    public Voting getVotingByName(String name) {
        if (votings == null) {
            return null;
        }

        for (Voting voting : votings) {
            if (voting.getVotingName().equals(name)) {
                return voting;
            }
        }
        return null;
    }

    /**
     * Serializes the list of votings to a file.
     * Uses a new thread to serialize the votings.
     * It speeds up application in the case when there are many votings that need to be serialized.
     */
    private void serializeVotings() {
        new Thread(() -> {
            try {
                FileOutputStream fileOut = new FileOutputStream("resources/votings.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(votings);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                i.printStackTrace();
            }
            System.out.println("Votings serialized in thread !");
        }).start();

        // Old way of serializing votings without Threading
//        try {
//            FileOutputStream fileOut = new FileOutputStream("resources/votings.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(votings);
//            out.close();
//            fileOut.close();
//        } catch (IOException i) {
//            i.printStackTrace();
//        }
    }

    /**
     * Deserializes the list of votings from a file.
     */
    private void loadVotings() {
        try {
            File file = new File("resources/votings.ser");
            if (file.length() == 0) {
                votings = new ArrayList<>();
            } else {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                votings = (ArrayList<Voting>) in.readObject();
                in.close();
                fileIn.close();
            }
        } catch (FileNotFoundException f) {
            // If the file doesn't exist, create a new ArrayList
            votings = new ArrayList<>();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Voting class not found");
            c.printStackTrace();
        }
    }
}