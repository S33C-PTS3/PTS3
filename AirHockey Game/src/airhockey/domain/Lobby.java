/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.util.List;

/**
 *
 * @author Eric
 */

public class Lobby {
    private Chat chat;
    private User loggedInUser;
    private List<Game> games;
    private List<User> users;
    
    
    public Lobby()
    {
        
    }
    
    public List<User> getUsers()
    {
        return null;
    }
    
    public List<Game> getGames()
    {
        return null;
    }
    
    public boolean addGame(Game game)
    {
        return false;
    }
    
    public boolean addUser(User user)
    {
        return false;
    }
    
    public void setLoggedInUser(User user)
    {
        
    }
    
}
