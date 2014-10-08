/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import airhockey.domain.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Meny
 */
public class GameTest {
    User user1;
    User user2;
    User user3;
    Spectator spectator1;
    Game game1;
    ActiveGame active1;
    public GameTest() {
    }
    
    
    @Before
    public void setUp() {
        user1 = new Player("Chain");
        user2 = new Player("Meny");
        user3 = new Player("Test");
        spectator1 = new Spectator("Spec1");
        game1 = new Game("NewGame",user1);
        game1.addPlayer(user2);
        game1.addPlayer(user3);
        active1 = new ActiveGame("NewGame",user1,null,null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testGameConstructor(){
        try{
            Game errorGame = new Game("new",null);
            fail("game moet minimaal 1 speler hebben");
        }
        catch(IllegalArgumentException ix){ 
        }
        try{
            Game errorGame2 = new Game("",user1);
            fail("game moet een naam hebben");
        }
        catch(IllegalArgumentException ix){ 
        }
            
    }
    @Test
    public void testInformation(){
        assertEquals("NewGame", game1.getName());
        assertEquals(1,game1.getPlayers().size());
        assertEquals(user1, game1.getPlayers().get(0));
        assertNull(game1.getStartTime());
    }
    
    @Test
    public void testAddPlayer(){
        assertTrue(game1.addPlayer(user2));
        assertTrue(game1.addPlayer(user3));
        assertFalse(game1.addPlayer(null));
        assertTrue(game1.addSpectator(spectator1));
        assertFalse(game1.addSpectator(null));
        
        assertEquals(3,game1.getPlayers().size());
        assertEquals(1,game1.getSpectators().size());
        
    }
    @Test
    public void getRating(){
        assertEquals(3,game1.getAverageRating());
        Game testGame = new Game("Test", user1);
        assertEquals(1,testGame.getAverageRating());
    }
    @Test
    public void testActive(){
        active1.nextRound();
        assertEquals(2,active1.getRound());
        for(int i = 0; i < 10; i++)
        {
            active1.nextRound();
            fail("active game kan niet meer dan 10 rondes hebben");
        }
        //assertNotNull(active1.calculateRating());
    }
}
