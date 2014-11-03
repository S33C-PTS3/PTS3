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
public class HumanPlayerTest {
    HumanPlayer hp;
    public HumanPlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hp = new HumanPlayer("Henk");
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testHumanPlayer()
    {
        /**
         * Constructor for HumanPlayer.
         * @param username 
         */
        assertNotNull("Should be created", hp);
        assertNotNull("Should be created", new HumanPlayer("Theo"));
    }
    
    @Test
    public void testToString()
    {
        assertEquals("Henk", hp.toString());
    }
    
    @Test 
    public void testGetInGameScore()
    {
        assertEquals(20, hp.getInGameScore());
    }
    
    @Test
    public void testChangeScore()
    {
        /**
         * Changes inGameScore by point.
         * @param point 
         */
        int i = hp.getInGameScore();
        hp.changeScore(1);
        assertEquals("Should be the same", i+1, hp.getInGameScore());
        
        for (int j = 0; j < 30; j++) 
        {
            hp.changeScore(-1);
        }
        assertEquals("Should be zero", 0, hp.getInGameScore());
        
    }
}
