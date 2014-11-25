/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey.domain;

import java.util.Observable;

/**
 *
 * @author Roy
 * The HumanPlayer represents a human controlled player
 */
public class HumanPlayer implements IPlayer {

    private int inGameScore;
    private String name;

    /**
     * Constructor used for HumanPlayer
     */
    public HumanPlayer() {
        this.inGameScore = 20;
        name = "Eric";
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
}


