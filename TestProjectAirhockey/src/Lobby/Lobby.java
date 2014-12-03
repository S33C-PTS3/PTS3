/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Chat.Chat;
import java.util.List;

/**
 *
 * @author Eric
 */
// SINGLETON CLASS
public class Lobby {
    private Chat chat;
    private User loggedInUser;
    private List<Game> games;
    private List<User> users;
    
    /**
     * creates an new instance of the lobby class
     */
    public Lobby()
    {
        
    }
    
    /**
     * get the logged in users
     * @return List of logged in users
     */
    public List<User> getUsers()
    {
        return null;
    }
    /**
     * gets all started/not-started games
     * @return list of games
     */
    public List<Game> getGames()
    {
        return null;
    }
    /**
     * Adds a game to the list of games
     * @param game
     * @return true/false
     */
    public boolean addGame(Game game)
    {
        return false;
    }
    /**
     * Adds a user to the list of users
     * @param user
     * @return true/false
     */
    public boolean addUser(User user)
    {
        return false;
    }
    /**
     * sets the loggedInUser field with the user that just logged in
     * @param user 
     */
    public void setLoggedInUser(User user)
    {
        
    }
    /**
     * gets the logged in user
     * @return user that is logged in
     */
    public User getLoggedInUser()
    {
        return null;
    }
    
}
