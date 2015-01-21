/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import Game.IPlayer;
import Lobby.User;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eric Takes Responsibillity for registering and logging in users.
 */
public class AuthenticationManager {

    private Connection dbConnection;
    private String ip;
    private final FTPManager ftp = new FTPManager();

    /**
     * Constructor for AuthenticationManager
     */
    public AuthenticationManager() {
    }

    /**
     * Checks every user in database with corresponding username and password.
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        try {
            initConnection();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        User loggedInUser = null;
        PreparedStatement prepstat = null;
        String query = "SELECT * FROM AH_ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
        int userID = 0;

        try {
            prepstat = dbConnection.prepareStatement(query);
            prepstat.setString(1, username);
            prepstat.setString(2, password);
            ResultSet rs = prepstat.executeQuery();
            
            while (rs.next()) {
                userID = rs.getInt(1);
            }

            if (userID == 0) {
                throw new SQLException();
            } else {
                try {
                    loggedInUser = new User(username);
                } catch (RemoteException ex) {
                    Logger.getLogger(AuthenticationManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.err.println("User not found");
        }

        return loggedInUser;
    }

    /**
     * Adds an user with a password and unique username. No duplicate usernames
     * are allowed.
     *
     * @param username
     * @param password
     * @return
     */
    public boolean register(String username, String password) {
        try {
            initConnection();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        boolean isSucces = false;
        Statement stat = null;
        String query = "SELECT MAX(ID) FROM AH_ACCOUNT";
        int highestID = 0;

        try {
            stat = dbConnection.createStatement();
            ResultSet rs = stat.executeQuery(query);
            while (rs.next()) {
                highestID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println("Highest User ID not found");
            isSucces = false;
        }

        int newID = highestID + 1;
        PreparedStatement prepstat;
        query = "INSERT INTO AH_ACCOUNT VALUES (?, ?, ?, 15, 15, 15, 15, 15, 15)";
        int result = 0;

        try {
            prepstat = dbConnection.prepareStatement(query);
            prepstat.setInt(1, newID);
            prepstat.setString(2, username);
            prepstat.setString(3, password);

            result = prepstat.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Registering not completed: " + ex.getMessage());
            isSucces = false;
        }

        if (result == 1) {
            isSucces = true;
        } else {
            isSucces = false;
        }

        return isSucces;
    }

    private void initConnection() throws RuntimeException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("JDBC Driver not found!");
        }

        try {
            try {
                ip = ftp.getIP();
            } catch (RuntimeException ex) {
                System.err.println("Server IP not found: " + ex.getMessage());
            }
            dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@//" + ip + ":1521/orcl", "system", "qbNdsAWq123");
        } catch (SQLException ex) {
            throw new RuntimeException("DB was not found");
        }

        if (dbConnection != null) {
            System.out.println("DB Connection found");
        }
    }

    public String[] getRanking() {
        String[] rankingList = new String[20];
        int lineIndex = 0;
        try {
            initConnection();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        String query = "SELECT * FROM (SELECT USERNAME, RATING FROM AH_ACCOUNT ORDER BY RATING DESC) WHERE ROWNUM < 22";
        PreparedStatement prepstat = null;
        try {
            prepstat = dbConnection.prepareStatement(query);
            ResultSet rs = prepstat.executeQuery();

            if (!rs.next()) {
                System.err.println("RESULTSET IS LEEG");
            }
            
            while (rs.next()) {
                String newLine = "";
                newLine += rs.getString("USERNAME");
                newLine += "|";
                newLine += rs.getInt("RATING");
                rankingList[lineIndex] = newLine;
                lineIndex++;
            }
        } catch (SQLException ex) {}

        return rankingList;
    }
    
    public boolean updatePlayerRatingscores(IPlayer player, double ratingscore) throws SQLException
    {
        boolean isSuccess = false;
        
        try {
            initConnection();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        PreparedStatement prepstat;
        String query = "UPDATE AH_ACCOUNT SET GAME5=GAME4, GAME4=GAME3, GAME3=GAME2, GAME2=GAME1, GAME1=? WHERE USERNAME = ?";
        int result = 0;

         try {
            prepstat = dbConnection.prepareStatement(query);
            prepstat.setInt(1, (int)ratingscore);
            prepstat.setString(2, player.getUsername());
            result = prepstat.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("updating not completed: " + ex.getMessage());
            isSuccess = false;
        }

        if (result == 1) {
            isSuccess = true;
        } else {
            isSuccess = false;
        }

        if (isSuccess) 
        {
            if (!updatePlayerAverageRating(player)) 
            {
                throw new SQLException("Average rating could not be updated for player " + player.getUsername());
            }
        }
        else
        {
            throw new SQLException("Ratingscore for player " + player.getUsername() + " failed.");
        }
        
        return isSuccess;
    }
    
    public double getPlayerRating(String playerName)
    {
        double rating = 0;

        try {
            initConnection();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        PreparedStatement prepstat = null;
        String query = "SELECT RATING FROM AH_ACCOUNT WHERE USERNAME = ?";

        try {
            prepstat = dbConnection.prepareStatement(query);
            prepstat.setString(1, playerName);
            ResultSet rs = prepstat.executeQuery();
            
            if (rs.next()) 
            {
                rating = rs.getDouble("RATING");
            }
            else
            {
                throw new SQLException("No row available, rating not found");
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return rating;
    }
    
    private boolean updatePlayerAverageRating(IPlayer player)
    {
        boolean isSuccess = false;
        
        try {
            initConnection();
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        
        PreparedStatement prepstat;
        String query = "UPDATE AH_ACCOUNT SET RATING = (GAME1*5+GAME2*4+GAME3*3+GAME4*2+GAME5)/15 WHERE USERNAME = ?";
        int result = 0;

         try {
            prepstat = dbConnection.prepareStatement(query);
            prepstat.setString(1, player.getUsername());
            result = prepstat.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("updating of average rating not completed: " + ex.getMessage());
            isSuccess = false;
        }

        if (result == 1) {
            isSuccess = true;
        } else {
            isSuccess = false;
        }
        
        return isSuccess;
    }
}
