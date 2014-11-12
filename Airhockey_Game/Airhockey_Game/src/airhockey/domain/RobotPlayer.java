/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 *
 * @author Roy
 * This class represents a player not controlled by a human.
 */
public class RobotPlayer implements IPlayer {
    private int inGameScore;
    private Difficulty difficulty;
    private String username;
    
    /**
     * Constructor for RobotPlayer
     * @param naam 
     */
    public RobotPlayer(String naam)
    {
        this.inGameScore = 20;
        this.username = naam;
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
     * Sets Difficulty for this object.
     * @param diff 
     */
    public void setDifficulty(Difficulty diff)
    {
        this.difficulty = diff;
    }
    
    /**
     * Returns string representing the RobotPlayer.
     * @return 
     */
    @Override
    public String toString()
    {
        return this.username;
    }
    
    
}
