/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey.domain;

/**
 * @author Eric
 * Takes Responsibillity for registering and logging in users.
 */
public class AuthenticationManager {
    
    /**
     * Constructor for AuthenticationManager
     */
    public AuthenticationManager()
    {
        
    }
    
    /**
     * Checks every user in database with corresponding username and password.
     * @param username
     * @param password
     * @return 
     */
    public User login(String username, String password)
    {
        return null;
    }
    
    /**
     * Adds an user with a password and unique username. No duplicate usernames
     * are allowed.
     * @param username
     * @param password
     * @return 
     */
    public boolean register(String username, String password)
    {
        return false;
    }
}
