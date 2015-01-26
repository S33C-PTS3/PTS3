/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Lobby.Game;
import Lobby.IGame;
import Lobby.User;
import Shared.IUser;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import observer.BasicPublisher;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 * @author Eric
 * Spectator is a user who is spectating an active game.
 */
public class Spectator extends User implements Serializable, IUser{
    
    private List<IGame> games;

    /**
     * Constructor used for Spectator
     * @param username 
     * @throws java.rmi.RemoteException 
     */
    public Spectator(String username) throws RemoteException
    {
        super(username);
        games = new ArrayList<>();
    }
    
    /**
     * Returns a list of all games Spectator is watching.
     * @return 
     */
    public List<IGame> getGames()
    {
        return games;
    }
    
    public void addGame(Game game)
    {
        this.games.add(game);
    }
    
    /**
     * Zooms into the selected game.
     * @param selectedGame 
     */
    public void zoomIn(Game selectedGame)
    {
                     
    }
}
