/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Security.AuthenticationManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Roy
 */
public class AuthenticationManagerTest {
    AuthenticationManager authMan;

    @Before
    public void setUp() {
        authMan = new AuthenticationManager();
        authMan.register("test", "test");
    }

    @Test
    public void testRegisterUser()
    {
        /**
        * Adds an user with a password and unique username. No duplicate usernames
        * are allowed.
        * @param username
        * @param password
        * @return 
        */
        assertTrue("Should be able to register",authMan.register("henk", "test"));   
        assertFalse("Should not be able to register", authMan.register("test","test"));
    }
    
    @Test
    public void testLoginUser()
    {
        /**
        * Checks every user in database with corresponding username and password.
        * @param username
        * @param password
        * @return 
        */
        assertEquals("Should be able to login", "henk", authMan.login("henk", "test").getUsername());
        assertNull("Should not work", authMan.login("top", "test"));    
    }
    
    
}
