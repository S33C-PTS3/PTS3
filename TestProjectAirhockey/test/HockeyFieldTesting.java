/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Game.HockeyField;
import Game.HumanPlayer;
import Game.IPlayer;
import Game.Puck;
import Game.RobotPlayer;
import Game.Side;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author Joep Kerste
 */
public class HockeyFieldTesting {
    
    HockeyField field;
    Puck puck;
    IPlayer h;
    IPlayer r1;
    IPlayer r2;
    @Before
    public void setUp()
    {
        puck = new Puck();
        
        //Create players
        h = new HumanPlayer();
        r1 = new RobotPlayer("Sjef");
        r2 = new RobotPlayer("Karel");
        
        //Create hockey field
        field = new HockeyField();
    }
    
    @Test
    public void testConstructor()
    {
        assertNotNull("Field was not instantiated", field);
        Side[] sides = field.getSides();
        IPlayer robot1 = sides[2].getBoundPlayer();
        IPlayer human = sides[1].getBoundPlayer();
        IPlayer robot2 = sides[0].getBoundPlayer();
        
        assertEquals("Naam van r1 is niet goed", r1.toString(), robot1.toString());
        assertEquals("Naam van r2 is niet goed", r2.toString(), robot2.toString());
        assertEquals("Naam van humanplayer is niet goed", h.toString(), human.toString());
        
        assertEquals("Score van r1 is niet goed", r1.getInGameScore(), robot1.getInGameScore());
        assertEquals("Score van r2 is niet goed", r2.getInGameScore(), robot2.getInGameScore());
        assertEquals("Score van humanplayer is niet goed", h.getInGameScore(), human.getInGameScore());
    }
    
    @Test
    public void testCheckWinner()
    {
        //Simulate 10 rounds
        for (int i = 0; i < 10; i++) 
        {
            try
            {
                field.checkWinner();
            }
            catch (Exception ex)
            {
                fail(ex.getMessage());
            }
        }
    }
}
