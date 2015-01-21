/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.HumanPlayer;
import java.rmi.RemoteException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Roy
 */
public class HumanPlayerTest {
    HumanPlayer hp;
    
    @Before
    public void setUp() throws RemoteException {
        hp = new HumanPlayer("Henk");
    }
    
    @Test
    public void testHumanPlayer() throws RemoteException
    {
        /**
         * Constructor for HumanPlayer.
         * @param username 
         */
        assertNotNull("Should be created", hp);
        assertNotNull("Should be created", new HumanPlayer("Henk"));
    }
    
    @Test
    public void testToString()
    {
        assertEquals("Should be 'Eric'", "Eric", hp.toString());
    }
    
    @Test 
    public void testGetInGameScore()
    {
        assertEquals("Should be 20", 20, hp.getInGameScore());
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
