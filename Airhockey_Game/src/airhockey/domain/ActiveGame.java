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
public class ActiveGame extends Game{

    private HockeyField hockeyField;
    private Chat chat;
    private int round;
    
    //gegenereerde constructor

    public ActiveGame(String name, User creator, HockeyField hockeyField, Chat chat)
    {
        super(name, creator);
    }
    
    public int getRound()
    {
        return round;
    }
    
    public void nextRound()
    {
        
    }
    
    public int calculateRating()
    {
        return 0;
    }
}

