/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.rmi.RemoteException;

/**
 *
 * @author Roy
 * This class represents a player not controlled by a human.
 */
public class RobotPlayer extends Player implements IPlayer {
    private int inGameScore;
    private Difficulty difficulty;
    private String name;
    
    /**
     * Constructor for RobotPlayer
     * @param naam 
     */
    public RobotPlayer(String naam) throws RemoteException
    {
        super(naam);
        if(naam == null)
        {
            throw new IllegalArgumentException("Naam mag niet null zijn");
        }
        if(naam.trim().equals(""))
        {
            throw new IllegalArgumentException("Naam mag niet leeg zijn");
        }
        this.inGameScore = 20;
        this.name = naam;
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
        if(inGameScore > 0)
        {
            this.inGameScore += point;
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
        return this.name;
    }
}
