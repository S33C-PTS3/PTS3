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
public class Spectator extends User{
    
    private List<Game> games;

    public Spectator(String username)
    {
        super(username);
    }
    
    public List<Game> getGames()
    {
        return games;
    }
    
    
    
    public void zoomIn(Game selectedGame)
    {
        
    }
}
