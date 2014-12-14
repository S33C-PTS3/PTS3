import Lobby.Game;
import Lobby.Lobby;
import Lobby.User;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rens & Eric
 */
public class LobbyTest {

    @Test
    public void testLobbyConstructor() {
        /**
         * creates an new instance of the lobby class
         */
        Lobby lobby = null;
        try {
            lobby = new Lobby();
            Assert.assertNotNull(lobby.getGames());
            Assert.assertNotNull(lobby.getUsers());
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    @Test
    public void testAddGame() {
        /**
         * Adds a game to the list of games
         *
         * @param game
         * @return true/false
         */

        User user = new User("testuser");
        Game game = new Game("testgame", user);
        Lobby lobby = null;
        try {
            lobby = new Lobby();
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * Test if game is added correctly to the list.
         */
        try {
            lobby.addGame(game);
            Assert.assertEquals("amount of games is not as expected", 1, lobby.getGames().size());
        } catch (IllegalArgumentException ex) {
            fail("game is not added succesfully.");
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * Test if exception occurs when the game parameters is null
         */
        try {
            lobby.addGame(null);
            fail("game cannot be null");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(true);
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testAddUser() {
        /**
         * Adds a user to the list of users
         *
         * @param user
         * @return true/false
         */
        User user = new User("henk");
        Lobby lobby = null;
        try {
            lobby = new Lobby();
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * Test if the user is added correctly to the list
         */
        try {
            lobby.addUser(user);
            Assert.assertEquals("Amount of users in the list is not correct.", 1, lobby.getUsers().size());
        } catch (IllegalArgumentException ex) {
            fail("User is not added succesfully");
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * Test if exception occurs when the user parameter is null
         */
        try {
            lobby.addUser(null);
            fail("user cannot be null");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(true);
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testSetLoggedInUser() {
        /**
         * sets the loggedInUser field with the user that just logged in
         *
         * @param user
         */
        Lobby lobby = null;
        try {
            lobby = new Lobby();
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = new User("Piet");
        
        /**
         * Test if the loggedInUser field sets succesfully
         */
        try {
            lobby.setLoggedInUser(user);
            Assert.assertEquals("Logged in user is not correct.", user, lobby.getLoggedInUser());
        }
        catch(IllegalArgumentException ex)
        {
            fail("LoggedInUser is not set succesfully");
        }
        
        /**
         * Test if an exception occurs when the user parameter is not null
         */
        try{
            lobby.setLoggedInUser(null);
            fail("LoggedInUser cannot be null");
        }
        catch(IllegalArgumentException ex){
            Assert.assertTrue(true);
        }

    }
}
