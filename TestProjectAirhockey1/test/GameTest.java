/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.ActiveGame;
import Game.Player;
import Game.Spectator;
import Lobby.Game;
import Lobby.User;
import static org.junit.Assert.*;
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
    User user4;
    Spectator spectator1;
    Game game1;
    ActiveGame active1;
    
    @Before
    public void setUp() {
        user1 = new Player("Chain");
        user2 = new Player("Meny");
        user3 = new Player("Test");
        user4 = new Player("Fout");
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
        /*
        *returns the name of this game
        */
        assertEquals("NewGame", game1.getName());
        /**
        * @return a list of players that joined this game as players
        */
        assertEquals(1,game1.getUsers().size());
        assertEquals(user1, game1.getUsers().get(0));
         /**
         * gets the time the Game has started
         * @return the time the Game has started if it has started ,else returns null
         */
        assertNull(game1.getStartTime());
    }
    
    @Test
    public void testAddPlayer(){
                /**
         * adds a player to the list of players in this game if it isn't already full
         * @param user the user that will be added to this game.
         * @return true if the player has been added successfully, else returns false.
         */
        assertTrue(game1.addPlayer(user2));
        assertTrue(game1.addPlayer(user3));
        assertFalse(game1.addPlayer(user4));
        assertFalse(game1.addPlayer(null));
                /**
        * @return a list of players that joined this game as players
        */
        assertEquals(3,game1.getUsers().size());
 
    }
    @Test
    public void testAddSpectator(){
                /**
         * adds a spectator to the list of spectators 
         * in this game if the spectator is spectating less than 4 games at the moment.
         * @param spectator
         * @return 
         */
        assertTrue(game1.addSpectator(spectator1));
        for(int i = 0; i < 4; i++)
        {
            Game g = new Game("NewGame" + i,user1);
            if(i == 3)
            {
                assertFalse(g.addSpectator(spectator1));
            }
            else
            {
                assertTrue(g.addSpectator(spectator1));
            }
        }
        assertFalse(game1.addSpectator(null));
        /**
        * @return a list of spectators that joined this game as spectators
        */
        assertEquals(1,game1.getSpectators().size());
        
    }
    
    @Test
    public void testStart(){
         /**
         * starts an inactive game if 3 players have joined and the creator of the game has confirmed this.
         * a start-time will also be set at the current time.
         * @return true if the game has started successfully, else returns false. 
         */
        game1 = new Game("Start",user1);
        assertFalse(game1.startGame());
        game1.addPlayer(user2);
        game1.addPlayer(user3);
        assertTrue(game1.startGame());
         /**
         * gets the time the Game has started
         * @return the time the Game has started if it has started ,else returns null
         */
        assertNotNull(game1.getStartTime());
    }
    
    @Test
    public void testgetRating(){
        /**
        * calculates and returns the average rating of this game
        * average rating is the sum of the rating of all players in this game divided by the amount of players in this game
        * @return average rating of this game 
        */
        assertEquals(3,game1.getAverageRating());
        Game testGame = new Game("Test", user1);
        assertEquals(1,testGame.getAverageRating());
    }
    @Test
    public void testActive(){
         /**
         * goes to the next round of the active game
         */
        active1.nextRound();
         /**
         * gets the current round of an active game
         * @return 
         */
        assertEquals(2,active1.getRound());
        for(int i = 0; i < 10; i++)
        {
            active1.nextRound();
            fail("active game kan niet meer dan 10 rondes hebben");
        }
         /**
         * calculates and returns the rating at the end of the game
         * @return the calculated rating
         */
        assertNotNull(active1.calculateRating());
        assertNotNull(active1.getStartTime());
        
    }
}
