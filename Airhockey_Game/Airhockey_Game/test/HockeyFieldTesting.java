/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import airhockey.domain.HockeyField;
import airhockey.domain.*;
import airhockey_game.MySketch;
import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.*;
import processing.core.PApplet;

/**
 *
 * @author Joep Kerste
 */
public class HockeyFieldTesting {
    
    @Test
    public void testConstructor()
    {
        //TODO
    }
    
    @Test
    public void testDisplayField()
    {
        //TODO
    }
    
    @Test
    public void testCheckWinner()
    {
        MySketch applet = new MySketch();
        applet.init();
        Puck puck = new Puck(null);
        
        //Create players
        IPlayer h = new HumanPlayer("human");
        IPlayer r1 = new RobotPlayer("roboteen");
        IPlayer r2 = new RobotPlayer("robottwee");
        
        //Create and add sides
        Side[] sides = new Side[3];
        Side side1 = new Side(2,1,2,1,Color.BLACK,applet,SideName.BOTTOM,h);
        sides[0] = side1;
        Side side2 = new Side(0,0,0,0,Color.BLACK,applet,SideName.RIGHT,r1);
        sides[1] = side2;
        Side side3 = new Side(0,0,0,0,Color.BLACK,applet,SideName.LEFT,r2);
        sides[2] = side3;
        
        //Create hockey field
        HockeyField field = new HockeyField(puck, sides, null);
        
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
