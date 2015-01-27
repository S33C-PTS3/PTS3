/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Game.Player;
import Game.Spectator;
import Shared.IActiveGame;
import Shared.IHockeyField;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author rens
 */
    public interface IGame extends Remote{
       
        IHockeyField getHockeyField() throws RemoteException;
        
        IActiveGame getActiveGame() throws RemoteException; 
       
        List<Player> getUsersObject() throws RemoteException;
        
        String getName() throws RemoteException;
        
        List<Spectator> getSpectators() throws RemoteException;
        
        int getId() throws RemoteException;
}
