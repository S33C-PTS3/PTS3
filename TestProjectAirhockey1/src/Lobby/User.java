/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import java.io.Serializable;

/**
 * @author Eric
 * User with an username
 */
public class User implements Serializable{

    private String username;
    
    /**
     * Construtor for User.
     * @param username 
     */
    public User(String username)
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
