/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import airhockey.domain.*;
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
    public AuthenticationManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        authMan = new AuthenticationManager();
        authMan.register("test", "test", "test");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test for registering an user, no double usernames allowed.
     */
    @Test
    public void testRegisterUser()
    {
        assertTrue("Should be able to register",authMan.register("henk", "test", "test"));   
        assertFalse("Should not be ablte to register", authMan.register("test","test","test"));
    }
    
    /**
     * Login test should return an user when succesfull.
     */
    @Test
    public void testLoginUser()
    {
        assertEquals("Should be able to login", "henk", authMan.login("henk", "test").getUsername());
        assertNull("Should not work", authMan.login("top", "test"));
                
                
    }
    
    
}
