/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Chat.Chat;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import observer.RemotePropertyListener;

/**
 *
 * @author Eric
 */
// SINGLETON CLASS
public class Lobby extends UnicastRemoteObject implements RemotePropertyListener, ILobby{
    private Chat chat;
    private User loggedInUser;
    private List<Game> games;
    private List<User> users;
    
    /**
     * creates an new instance of the lobby class
     * @throws java.rmi.RemoteException
     */
    public Lobby() throws RemoteException
    {
        
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] updateRanking() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addGame(Game game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
