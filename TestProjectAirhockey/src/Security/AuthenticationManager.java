/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import Lobby.User;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
        
        //throw new UnsupportedOperationException("constructor nog implementeren");
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
            getServerIP();
            //dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@//145.93.162.112:1521/orcl", "system", "qbNdsAWq123");
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
    
     private void getServerIP() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();

            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                for (InetAddress allMyIp : allMyIps) {
                    if (allMyIp.toString().contains("145")) 
                    {
                        int slashIndex = allMyIp.toString().indexOf("/");
                        ip = allMyIp.toString().substring(slashIndex + 1);
                        System.out.println("Server IP: " + ip);
                    }
                }
            }
        } catch (UnknownHostException ex) 
        {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }
    
}
