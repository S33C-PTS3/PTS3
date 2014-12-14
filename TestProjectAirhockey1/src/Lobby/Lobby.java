/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Shared.ILobby;
import Chat.Chat;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import observer.RemotePropertyListener;

/**
 *
 * @author Eric
 */
// SINGLETON CLASS
public class Lobby extends UnicastRemoteObject implements ILobby {

    private Chat chat;
    private User loggedInUser;
    private List<Game> games;
    private List<User> users;
    private int gameCount;

    /**
     * creates an new instance of the lobby class
     *
     * @throws java.rmi.RemoteException
     */
    public Lobby() throws RemoteException {
        games = new ArrayList<>();
        gameCount = 1;
    }

    /**
     * sets the loggedInUser field with the user that just logged in
     *
     * @param user
     */
    public void setLoggedInUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * gets the logged in user
     *
     * @return user that is logged in
     */
    public User getLoggedInUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String[]> getGames() throws RemoteException {
        List<String[]> gamesAsString = new ArrayList<>();
        String[] gameInfo;
        gameInfo = new String[6];
        for (Game game : games) {
            gameInfo[0] = String.valueOf(game.getId());
            gameInfo[1] = game.getName();
            gameInfo[2] = String.valueOf(game.getAverageRating());
            for (int i = 3; i < game.getUsers().length + 3; i++) {
                gameInfo[i] = game.getUsers()[i - 3];
            }
            gamesAsString.add(gameInfo);
        }
        return gamesAsString;
    }

    @Override
    public String[] updateRanking() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] addGame(Game game) throws RemoteException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        game.setId(gameCount);
        games.add(game);
        String[] gameInfo = new String[6];
        gameInfo[0] = String.valueOf(game.getId());
        gameInfo[1] = game.getName();
        gameInfo[2] = String.valueOf(game.getAverageRating());
        for (int i = 3; i < game.getUsers().length + 3; i++) {
            gameInfo[i] = game.getUsers()[i - 3];
        }
        gameCount++;
        return gameInfo;
    }

    @Override
    public boolean addUser(User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUserToGame(int gameId, User user) throws RemoteException {
        for (Game g : games) {
            if (g.getId() == gameId) {
                g.addPlayer((User) user);
                return true;
            }
        }
        return false;
    }
}
