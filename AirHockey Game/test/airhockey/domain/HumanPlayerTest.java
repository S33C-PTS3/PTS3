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
    
    /**
     * Test for the constructor of spectator
     */
    @Test
    public void testHumanPlayer()
    {
        assertNotNull("Human Player should be created", hp);
    }
}
