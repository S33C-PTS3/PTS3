/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author rens
 */
public interface IActiveGame extends Remote {

    /**
     * Haalt alle User objecten van deze Game op.
     *
     * @return een lijst met users die in de lobby zitten
     */
    public String[] getUsers() throws RemoteException;
    
    public IHockeyField getHockeyField() throws RemoteException;
    
    public void startGame() throws RemoteException;
    
    public boolean getGameStatus() throws RemoteException;
}
