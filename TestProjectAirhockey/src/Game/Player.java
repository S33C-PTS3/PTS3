/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Lobby.User;
import java.rmi.RemoteException;

/**
 * @author Eric Player has an inGameScore used for
 */
public class Player extends User implements IPlayer {

    private int inGameScore;

    private int playerId;

    /**
     * Constructor for player with username
     *
     * @param username
     */
    public Player(String username) throws RemoteException
    {
        super(username);
        inGameScore = 10;
    }

    @Override
    public void setID(int id)
    {
        this.playerId = id;
    }

    @Override
    public String getUsername()
    {
        return super.getUsername();
    }

    @Override
    public void setInGameScore(int newInGameScore)
    {
        this.inGameScore = newInGameScore;
    }

    @Override
    public int getInGameScore()
    {
        return this.inGameScore;
    }

    @Override
    public void changeScore(int point)
    {
        this.inGameScore += point;
    }

    @Override
    public int getID()
    {
        return this.playerId;
    }

}
