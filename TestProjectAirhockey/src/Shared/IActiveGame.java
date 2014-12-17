/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import Chat.Chat;
import Chat.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author rens
 */
public interface IActiveGame extends Remote{

    /**
     * Haalt alle User objecten van deze Game op.
     *
     * @return een lijst met users die in de lobby zitten
     */
    public String[] getUsers() throws RemoteException;
    
    public IHockeyField getHockeyField() throws RemoteException;
    
    public void startGame() throws RemoteException;
    
    public boolean getGameStatus() throws RemoteException;
    
    public Chat getChat() throws RemoteException;
    
    public void addMessage(Message m) throws RemoteException;
}
