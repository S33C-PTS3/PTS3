/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Lobby.User;
import Chat.Chat;
import Chat.Message;
import Shared.IActiveGame;
import Shared.IHockeyField;
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
public class ActiveGame extends UnicastRemoteObject implements IActiveGame, RemotePublisher {

    private HockeyField hockeyField;
    private Chat chat;
    private int round;
    private boolean started = false;
    private String[] users;
    BasicPublisher publisher;

    //gegenereerde constructor
    //ROUNDS AND SCORES IN ACTIVE GAME ZETTEN EN UIT HOCKEYFIELD
    public ActiveGame(String name) throws RemoteException {
        //super(name, creator);
        hockeyField = new HockeyField();
        hockeyField.init(Mode.MULTI);
        users = new String[3];
        publisher = new BasicPublisher(new String[]{"Game"});
        chat = new Chat();
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
        if (!started) {
            HockeyField hockeyFieldGame = (HockeyField) hockeyField;
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

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.removeListener(listener, property);
    }
    
    @Override
    public Chat getChat()
    {   
        return this.chat;
    }
    
    @Override
    public void addMessage(Message m)
    {
        this.chat.getMessages().add(m);
    }
}
