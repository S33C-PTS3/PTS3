/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Game.Spectator;
import Shared.IActiveGame;
import Shared.IHockeyField;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 *
 * @author Eric
 */
public class Game implements Serializable{
    private int id;
    private String name;
    private Calendar startTime;
    private int averageGameRating;
    private List<User> users;
    
    public Game(String name, User creator)
    {
        users = new ArrayList<>();
        this.name = name;
        this.averageGameRating = 0;
        users.add(creator);
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    /*
    *returns the name of this game
    */
    public String getName()
    {
        return this.name;
    }
    
    /**
    * @return a list of players that joined this game as players
    */
    public List<User> getUsersGame()
    {
        return this.users;
    }
    
    /**
    * @return a list of spectators that joined this game as spectators
    */
    public List<Spectator> getSpectators()
    {
        return null;
    }
    
    /**
    * calculates and returns the average rating of this game
    * average rating is the sum of the rating of all players in this game divided by the amount of players in this game
    * @return average rating of this game 
    */
    public int getAverageRating()
    {
        return this.averageGameRating;
    }
    
    /**
     * gets the time the Game has started
     * @return the time the Game has started if it has started ,else returns null
     */
    public Calendar getStartTime()
    {
        return null;
    }
    
    /**
     * starts an inactive game if 3 players have joined and the creator of the game has confirmed this.
     * a start-time will also be set at the current time.
     * @return true if the game has started successfully, else returns false. 
     */
    public boolean startGame()
    {
        return false;
    }
    
    /**
     * adds a player to the list of players in this game if it isn't already full
     * @param user the user that will be added to this game.
     * @return true if the player has been added successfully, else returns false.
     */
    public boolean addPlayer(User user)
    {
        return false;
    }
    
    /**
     * adds a spectator to the list of spectators 
     * in this game if the spectator is spectating less than 4 games at the moment.
     * @param spectator
     * @return 
     */
    public boolean addSpectator(Spectator spectator)
    {
        return false;
    }
}
