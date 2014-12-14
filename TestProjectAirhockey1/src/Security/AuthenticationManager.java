/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import Lobby.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eric
 * Takes Responsibillity for registering and logging in users.
 */
public class AuthenticationManager {
    
    private Connection dbConnection;
    
    /**
     * Constructor for AuthenticationManager
     */
    public AuthenticationManager()
    {
        try 
        {
            initConnection();
        } 
        catch (RuntimeException ex) 
        {
            System.out.println("DB init failed");
        }
        
        throw new UnsupportedOperationException("constructor nog implementeren");
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
        throw new UnsupportedOperationException("AuthenticationManager.register() nog implementeren");
    }
    
    private void initConnection() throws RuntimeException
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("JDBC Driver not found!");
        }

        try
        {
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@//145.93.163.170:1521/orcl", "system", "qbNdsAWq123");
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@//"+ ip +":1521/orcl", "system", "qbNdsAWq123");
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }        

        if (dbConnection == null) {
            throw new RuntimeException("Connection could not be made.");
        }
    }
}
