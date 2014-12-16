/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.rmi.RemoteException;
import java.util.Observable;

/**
 *
 * @author Roy
 * The HumanPlayer represents a human controlled player
 */
public class HumanPlayer extends Player implements IPlayer {

    private int inGameScore;
    private String name;

    /**
     * Constructor used for HumanPlayer
     */
    public HumanPlayer(String name) throws RemoteException {
        super(name);
        this.inGameScore = 20;
        this.name = name;
    }

    /**
     * Used to change inGameScore.
     * @param newInGameScore 
     */
    @Override
    public void setInGameScore(int newInGameScore) {
        this.inGameScore = newInGameScore;
    }

    /**
     * Returns the inGamescore 
     * @return 
     */
    @Override
    public int getInGameScore() {
        return this.inGameScore;
    }

    /**
     * Changes inGameScore by one point
     * @param point 
     */
    @Override
    public void changeScore(int point) {
        this.inGameScore += point;
    }
    
    /**
     * Returns string representing the HumanPlayer
     * @return 
     */
    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public int getID() {
        return this.id;
    }
}


