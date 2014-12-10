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

/**
 * @author Eric
 * Takes Responsibillity for registering and logging in users.
 */
public class AuthenticationManager {
    
    private Connection dbConnection;
    private String ip;
    private FTPManager ftp = new FTPManager();
    
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
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    /**
     * Checks every user in database with corresponding username and password.
     * @param username
     * @param password
     * @return 
     */
    public User login(String username, String password)
    {
        throw new UnsupportedOperationException("AuthenticationManager.login() nog implementeren");
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
            throw new RuntimeException("JDBC Driver not found!");
        }

        try
        {
            try
            {
                ip = ftp.getIP();
            }
            catch (RuntimeException ex)
            {
                System.out.println("Server IP not found: " + ex.getMessage());
            }
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@//"+ ip +":1521/orcl", "system", "qbNdsAWq123");
        }
        catch (SQLException ex)
        {
            throw new RuntimeException("DB was not found");
        }           

        if (dbConnection != null) 
        {
            System.out.println("DB Connection found");
        }
    }    
}
