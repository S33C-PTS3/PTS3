/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Lobby.Game;
import Lobby.User;
import Chat.Chat;

/**
 *
 * @author Eric
 */
public class ActiveGame extends Game{

    private HockeyField hockeyField;
    private Chat chat;
    private int round;
    
    //gegenereerde constructor
    //ROUNDS AND SCORES IN ACTIVE GAME ZETTEN EN UIT HOCKEYFIELD

    public ActiveGame(String name, User creator, HockeyField hockeyField, Chat chat)
    {
        super(name, creator);
    }
    
    /**
     * gets the current round of an active game
     * @return 
     */
    public int getRound()
    {
        return round;
    }
    
    /**
     * goes to the next round of the active game
     */
    public void nextRound()
    {
        
    }
    
    /**
     * calculates and returns the rating at the end of the game
     * @return the calculated rating
     */
    public double calculateRating()
    {
        return 0;
    }
}
