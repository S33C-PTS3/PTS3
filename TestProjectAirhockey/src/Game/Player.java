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

    /**
     * Constructor for player with username
     *
     * @param username
     */
    public Player(String username) throws RemoteException {
        super(username);
        //bv. ingamescore = 10
    }

    @Override
    public void setID(int id) {
        super.setID(id);
    }
    @Override
    public String getUsername() {
        return super.getUsername();
    }
    @Override
    public void setInGameScore(int newInGameScore) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getInGameScore() {
        return this.inGameScore;
    }

    @Override
    public void changeScore(int point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getID() {
        return super.id;
    }

}
