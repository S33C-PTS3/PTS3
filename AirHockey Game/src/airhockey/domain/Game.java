/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Eric
 */
public class Game {
    private String name;
    private Calendar startTime;
    private int averageGameRating;
    private List<User> users;
    
    public Game(String name, User creator)
    {
        users = new ArrayList<>();
        users.add(creator);
    }
    
    public String getName()
    {
        return null;
    }
    
    public List<Player> getPlayers()
    {
        return null;
    }
    
    public List<Spectator> getSpectators()
    {
        return null;
    }
    
    public int getAverageRating()
    {
        return 0;
    }
    
    public Calendar getStartTime()
    {
        return null;
    }
    
    public void startGame()
    {
        
    }
    
    public boolean addPlayer(User user)
    {
        return false;
    }
    
    public boolean addSpectator(Spectator spectator)
    {
        return false;
    }
    
    public int calculateRating()
    {
        return 0;
    }
}
