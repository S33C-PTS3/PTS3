/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 * * @author Eric
 * HumanPlayer is a Player that plays a game.
 */
public class HumanPlayer extends Player{
    private double rating;
    
    /**
     * Constructor for HumanPlayer.
     * @param username 
     */
    public HumanPlayer(String username)
    {
        super(username);
    }
    
    /**
     * Returns rating.
     * @return 
     */
    public double getRating()
    {
        return rating;
    }
    
    /**
     * Used to determine rating from played game.
     */
    public void calculateRating()
    {
        
    }
    
}
