/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

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
        boolean register = authMan.register("Bert", "test","test");
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * An user may not have the same username as another.
     */
    public void testRegisterUser()
    {
        assertTrue("User should be created", authMan.register("Henk", "test","test"));
        assertFalse("User should not be created, naam bestaat al", authMan.register("Bert", "test", "test"));
    }
    
    /**
     * The correct user must be logged in
     */
    public void testLogin()
    {
        assertNull("Invalid login, no such user", authMan.login("theo", "test"));
        assertNull("Invalid login, no such user", authMan.login("Bert", ""));
        assertNotNull("Login should be valid", authMan.login("Bert", "test"));
    }
}
