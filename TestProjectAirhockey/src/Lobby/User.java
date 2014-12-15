/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Shared.IUser;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Eric
 * User with an username
 */
public class User implements Serializable, IUser {

    private String username;
    
    /**
     * Construtor for User.
     * @param username 
     */
    public User(String username) throws RemoteException
    {
        this.username = username;
    }
    
    /**
     * Returns username.
     * @return 
     */
    public String getUsername()
    {
        return username;
    }
}
