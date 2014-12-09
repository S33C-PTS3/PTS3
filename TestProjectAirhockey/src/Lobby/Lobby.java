/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Shared.ILobby;
import Chat.Chat;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import observer.BasicPublisher;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 *
 * @author Eric
 */
// SINGLETON CLASS
public class Lobby extends UnicastRemoteObject implements RemotePublisher, ILobby {

    private Chat chat;
    private User loggedInUser;
    private List<Game> games;
    private List<User> users;
    private BasicPublisher publisher;
    private String[] lobby;
    private int gameCount;

    /**
     * creates an new instance of the lobby class
     *
     * @throws java.rmi.RemoteException
     */
    public Lobby() throws RemoteException
    {
        lobby = new String[1];
        lobby[0] = "lobby";
        publisher = new BasicPublisher(lobby);
        games = new ArrayList<Game>();
        gameCount = 1;
    }

    /**
     * sets the loggedInUser field with the user that just logged in
     *
     * @param user
     */
    public void setLoggedInUser(User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * gets the logged in user
     *
     * @return user that is logged in
     */
    public User getLoggedInUser()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Game> getGames() throws RemoteException
    {
        return this.games;
    }

    @Override
    public String[] updateRanking() throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] addGame(Game game) throws RemoteException
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        game.setId(gameCount);
        games.add(game);
        String[] gameInfo = new String[6];
        gameInfo[0] = String.valueOf(game.getId());
        gameInfo[1] = game.getName();
        gameInfo[2] = String.valueOf(game.getAverageRating());
        for (int i = 3; i < game.getUsers().size() + 3; i++)
        {
            gameInfo[i] = game.getUsers().get(i - 3).getUsername();
        }
        gameCount++;
        publisher.inform(this, null, null, gameInfo);
        return gameInfo;
    }

    @Override
    public boolean addUser(User user) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException
    {
        publisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException
    {
        publisher.addListener(listener, property);
    }

}
