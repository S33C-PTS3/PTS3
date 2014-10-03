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
    
    //constructor met betrekking tot overerving user?
    public Spectator(String username)
    {
        super(username);
    }
    
    public void zoomIn(Game selectedGame)
    {
        
    }
}
