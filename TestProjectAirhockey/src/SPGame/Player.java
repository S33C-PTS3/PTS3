/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPGame;

/**
 * @author Eric
 * Player has an inGameScore used for 
 */
public class Player extends User{

    private int inGameScore;

    /**
     * Constructor for player with username
     * @param username 
     */
    public Player(String username)
    {
        super(username);
        //bv. ingamescore = 10
    }
    
    /**
     * Returns inGameScore.
     * @return 
     */
    public int getInGameScore()
    {
        return inGameScore;
    }
    
    /**
     * Changes inGameScore by point.
     * @param point 
     */
    public void changeScore(int point)
    {

    }
}