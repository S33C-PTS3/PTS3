/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 * @author Eric
 * User with an username
 */
public class User {

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
