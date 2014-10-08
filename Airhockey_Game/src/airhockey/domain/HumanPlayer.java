/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 *
 * @author Eric
 */
public class HumanPlayer extends Player{
    private double rating;

    public HumanPlayer(String username)
    {
        super(username);
    }
    
    public double getRating()
    {
        return rating;
    }
    
    public void calculateRating()
    {
        
    }
    
}
