/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import airhockey.domain.ActiveGame;
import airhockey.domain.Game;
import airhockey.domain.HockeyField;
import airhockey.domain.Player;
import airhockey.domain.Spectator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Sasa2905
 */
public class GameTest {
    Player player1;
    Player player2;
    Player player3;
    Spectator spectator1;
    Game game1;
    ActiveGame active1;
    public GameTest() {
    }
    
    
    @Before
    public void setUp() {
        player1 = new Player("Chain");
        player2 = new Player("Meny");
        player3 = new Player("Test");
        spectator1 = new Spectator("Spec1");
        game1 = new Game("NewGame",player1);
        game1.addPlayer(player2);
        game1.addPlayer(player3);
        //active1 = new ActiveGame(new HockeyField(null,null),null,"ActiveNewGame");
    }
    
    @Test
    public void testInformation(){
        assertEquals("NewGame", game1.getName());
        assertEquals(1,game1.getPlayers().size());
        assertEquals(player1, game1.getPlayers().get(0));
        assertNull(game1.getStartTime());
    }
    
    @Test
    public void testAddPlayer(){
        assertTrue(game1.addPlayer(player2));
        assertTrue(game1.addPlayer(player3));
        assertFalse(game1.addPlayer(null));
        assertTrue(game1.addSpectator(spectator1));
        assertFalse(game1.addSpectator(null));
        
        assertEquals(3,game1.getPlayers().size());
        assertEquals(1,game1.getSpectators().size());
        
    }
    @Test
    public void getRating(){
        //int size = 
        for(int i = 0; i < game1.getPlayers().size(); i++)
        {
            
        }
    }
    public void testActive(){
        active1.nextRound();
        //assertEquals(2,active1.getRound());
    }
}
