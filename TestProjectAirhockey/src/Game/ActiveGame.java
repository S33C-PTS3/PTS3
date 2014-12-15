/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Lobby.User;
import Chat.Chat;
import Shared.IActiveGame;
import Shared.IHockeyField;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import observer.RemotePropertyListener;

/**
 *
 * @author Eric
 */
public class ActiveGame extends UnicastRemoteObject implements IActiveGame {

    private HockeyField hockeyField;
    private Chat chat;
    private int round;
    private boolean started = false;

    //gegenereerde constructor
    //ROUNDS AND SCORES IN ACTIVE GAME ZETTEN EN UIT HOCKEYFIELD
    public ActiveGame(String name, User creator) throws RemoteException {
        //super(name, creator);
        hockeyField = new HockeyField();
        hockeyField.init(Mode.MULTI);
    }

    /**
     * gets the current round of an active game
     *
     * @return
     */
    public int getRound() {
        return round;
    }

    /**
     * goes to the next round of the active game
     */
    public void nextRound() {

    }

    /**
     * calculates and returns the rating at the end of the game
     *
     * @return the calculated rating
     */
    public double calculateRating() {
        return 0;
    }

    @Override
    public String[] getUsers() throws RemoteException {
        return null;
    }

    @Override
    public IHockeyField getHockeyField() throws RemoteException {
        return this.hockeyField;
    }

    @Override
    public void startGame() throws RemoteException {
        if (!started) 
        {
            PuckRunnable runnable = new PuckRunnable(hockeyField);
            Thread threadPuck = new Thread(runnable);
            threadPuck.start();
            started = true;
        }

    }

    @Override
    public boolean getGameStatus() throws RemoteException {
        return this.started;
    }   
}
