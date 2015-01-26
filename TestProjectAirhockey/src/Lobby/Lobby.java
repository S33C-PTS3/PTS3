/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Shared.ILobby;
import Chat.Chat;
import Chat.Message;
import Game.Spectator;
import Shared.IUser;
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
public class Lobby extends UnicastRemoteObject implements ILobby, RemotePublisher {

    private Chat chat;
    private User loggedInUser;
    private List<Game> games;
    private List<User> users;
    private int gameCount;
    private BasicPublisher publisher;
    private final int MAXSPECTATORS = 2;

    /**
     * creates an new instance of the lobby class
     *
     * @throws java.rmi.RemoteException
     */
    public Lobby() throws RemoteException
    {
        games = new ArrayList<>();
        gameCount = 1;
        publisher = new BasicPublisher(new String[]
        {
            "client", "lobby", "spectator", "SpectatorOverview"
        });
        chat = new Chat();
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
        return loggedInUser;
    }

    @Override
    public Chat getChat()
    {
        return chat;
    }

    @Override
    public List<User> getUsers() throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String[]> getGames() throws RemoteException
    {
        List<String[]> gamesAsString = new ArrayList<>();
        String[] gameInfo;
        gameInfo = new String[7];
        for (Game game : games)
        {
            gameInfo[0] = String.valueOf(game.getId());
            gameInfo[1] = game.getName();
            gameInfo[2] = String.valueOf(game.getAverageRating());
            for (int i = 3; i < game.getUsersGame().size() + 3; i++)
            {
                gameInfo[i] = game.getUsersGame().get(i - 3).getUsername();
            }
            gameInfo[6] = String.valueOf(game.getSpectators().size());
            gamesAsString.add(gameInfo);
            gameInfo = new String[7];
        }
        return gamesAsString;
    }

    @Override
    public String[] updateRanking() throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] addGame(String name, IUser user) throws RemoteException
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Game game = new Game(name + gameCount, (User) user);
        game.setId(gameCount);
        games.add(game);
        String[] gameInfo = new String[7];
        gameInfo[0] = String.valueOf(game.getId());
        gameInfo[1] = game.getName();
        gameInfo[2] = String.valueOf(game.getAverageRating());
        for (int i = 3; i < game.getUsersGame().size() + 3; i++)
        {
            gameInfo[i] = game.getUsersGame().get(i - 3).getUsername();
        }
        gameInfo[6] = String.valueOf(game.getSpectators().size());
        gameCount++;
        return gameInfo;
    }

    @Override
    public Spectator addSpectatorToGame(int gameId, Spectator spectator) throws RemoteException
    {
        Spectator spectator2 = spectator;
        for (Game g : games)
        {
            if (g.getId() == gameId)
            {
                g.addSpectator(spectator2);
                spectator2.addGame(g);
                if (g.getSpectators().size() == MAXSPECTATORS)
                {
                    publisher.inform(this, "spectator", null, true);
                }
                publisher.inform(this, "SpectatorOverview", null, gameId);
                return spectator2;
            }
        }
        return spectator2;
    }

    @Override
    public boolean addUser(User user) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addUserToGame(int gameId, User user) throws RemoteException
    {
        int userCount = 0;
        for (Game g : games)
        {
            if (g.getId() == gameId)
            {
                g.addPlayer((User) user);
                for (int i = 0; i < g.getUsers().length; i++)
                {
                    if (g.getUsers()[i] != null)
                    {
                        userCount++;
                    }
                }
                user.setID(userCount - 1);
                if (userCount == 3)
                {
                    publisher.inform(this, "client", g, g.getUsers()[0]);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException
    {
        publisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException
    {
        publisher.removeListener(listener, property);
    }

    @Override
    public IGame getGame(int id) throws RemoteException
    {
        for (Game g : games)
        {
            if (g.getId() == id)
            {
                return (IGame) g;
            }
        }
        return null;
    }

    public boolean addMessage(String sender, String text) throws RemoteException
    {
        return chat.addMessage(sender, text);
    }

    @Override
    public List<Message> getMessages() throws RemoteException
    {
        return chat.getMessages();
    }

    @Override
    public void removeGame(int gameID) throws RemoteException
    {
        games.remove(getGame(gameID));
    }

    @Override
    public void removeUserFromGame(String username, int gameID) throws RemoteException
    {
        for(Game g : games) {
            if(g.getId() == gameID) {
                for(User u : g.getUsersGame()) {
                    if(username.equals(u.getUsername())) {
                        g.removePlayer(u);
                    }
                }
            }
        }
    }
}
