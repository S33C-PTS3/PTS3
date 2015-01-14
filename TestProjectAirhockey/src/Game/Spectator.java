/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Lobby.Game;
import Lobby.User;
import Shared.IUser;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Eric
 * Spectator is a user who is specatating an active game.
 */
public class Spectator extends User implements Serializable, IUser{
    
    private List<Game> games;

    /**
     * Constructor used for Spectator
     * @param username 
     * @throws java.rmi.RemoteException 
     */
    public Spectator(String username) throws RemoteException
    {
        super(username);
    }
    
    /**
     * Returns a list of all games Spectator is watching.
     * @return 
     */
    public List<Game> getGames()
    {
        return games;
    }
    
    
    /**
     * Zooms into the selected game.
     * @param selectedGame 
     */
    public void zoomIn(Game selectedGame)
    {
                     
    }
}
