/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.HumanPlayer;
import Game.IPlayer;
import Game.Side;
import Game.SideName;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.*;
/**
 *
 * @author Joep Kerste
 */
public class SideTesting {
    Side side;
    IPlayer newPlayer;

    @Before
    public void setUp()
    {
        newPlayer = new HumanPlayer();
        side = new Side(100, 200, 200, 100, Color.GREEN, SideName.RIGHT, newPlayer);
    }
    
    @Test
    public void testConstructor()
    {   
        Assert.assertEquals("Binded player incorrect", newPlayer, side.getBoundPlayer());
        Assert.assertEquals("LineX1 incorrect", 100, side.getLineX1(), 0);
        Assert.assertEquals("LineX2 incorrect", 200, side.getLineX2(), 0);
        Assert.assertEquals("LineY1 incorrect", 200, side.getLineY1(), 0);
        Assert.assertEquals("LineY2 incorrect", 100, side.getLineY2(), 0);
        Assert.assertEquals("Sidename incorrect ", SideName.RIGHT, side.getSideName());
        Assert.assertEquals("Color incorrect ", Color.GREEN, side.getColor());
        Assert.assertNotNull(side.getBat());
    }  
    
    @Test
    public void testGetStartPoint()
    {
        Point2D p = new Point2D(100,200);
        Assert.assertEquals("Startpoint incorrect", p, side.getStartPoint());
    }
    
    @Test
    public void testGetEndPoint()
    {
        Point2D p = new Point2D(200,100);
        Assert.assertEquals("Endpoint incorrect", p, side.getEndPoint());
    }
}