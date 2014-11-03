/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 *
 * @author Roy
 * The HumanPlayer represents a human controlled player
 */
public class HumanPlayer implements IPlayer {

    private int inGameScore;
    private final String name;

    /**
     * Constructor used for HumanPlayer
     */
    public HumanPlayer(String name) {
        this.inGameScore = 20;
        this.name = name;
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
        if (inGameScore < 0) 
        {
            inGameScore = 0;
        }
    }
    
    /**
     * Returns string representing the HumanPlayer, contains name
     * @return 
     */
    @Override
    public String toString()
    {
        return name;
    }
}


