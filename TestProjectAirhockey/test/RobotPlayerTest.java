/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Game.Difficulty;
import Game.RobotPlayer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Roy
 */
public class RobotPlayerTest {

    RobotPlayer rp;

    @Before
    public void setUp()
    {
        rp = new RobotPlayer("Theo");
    }

    @Test
    public void testRobotPlayer()
    {
        /**
         * Constructor for RobotPlayer with a name and difficulty.
         *
         * @param username
         * @param difficulty
         */
        assertNotNull("Should be created", rp);
        assertNotNull("Should be created", new RobotPlayer("henk"));

        try
        {
            rp = new RobotPlayer(null);
            fail("Name cannot be null");
        }
        catch (IllegalArgumentException ex)
        {
            assertTrue(true);
        }
        
        try
        {
            rp = new RobotPlayer("");
            fail("Name cannot be empty");
        }
        catch(IllegalArgumentException ex)
        {
            assertTrue(true);
        }
        
        try
        {
            rp = new RobotPlayer("  ");
            fail("Name cannot be empty/ only contain spaces");
        }
        catch(IllegalArgumentException ex)
        {
            assertTrue(true);
        }

    }

    @Test
    public void testChangeScore()
    {
        /**
         * Changes inGameScore by point.
         *
         * @param point
         */
        int i = rp.getInGameScore();
        rp.changeScore(1);
        assertEquals("Should be the same", i + 1, rp.getInGameScore());
        for (int j = 0; j < 30; j++)
        {
            rp.changeScore(-1);
        }
        assertEquals("Should be zero", 0, rp.getInGameScore());
    }

    @Test
    public void testToString()
    {
        assertEquals("Should be 'Theo'", "Theo", rp.toString());
    }

    @Test
    public void testGetInGameScore()
    {
        RobotPlayer newRobot = new RobotPlayer("x");
        assertEquals("Should be 20", 20, newRobot.getInGameScore());
    }

    @Test
    public void testSetDifficulty()
    {
        try
        {
            rp.setDifficulty(Difficulty.HARD);
        }
        catch (Exception ex)
        {
            fail("Failed to set Difficulty");
        }
    }
}
