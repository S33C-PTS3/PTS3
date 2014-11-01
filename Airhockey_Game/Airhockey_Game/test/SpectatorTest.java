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
public class SpectatorTest {
    Spectator s;
    public SpectatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        s = new Spectator("Henk");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test for registering an user.
     */
    @Test
    public void testSpectator()
    {
        assertNotNull("Should be created",new Spectator("theo"));
        assertNotNull("Should be created", s);
    }
    
    
}
