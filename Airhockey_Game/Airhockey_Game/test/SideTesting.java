/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import airhockey.domain.*;
import airhockey_game.MySketch;
import java.awt.Color;
import org.junit.*;
/**
 *
 * @author Joep Kerste
 */
public class SideTesting {
    @Test
    public void testConstructor()
    {
        IPlayer newPlayer = new HumanPlayer("Eric");
        MySketch ms = new MySketch();
        ms.init();
        System.out.println(ms);
        Side side = new Side(100, 200, 200, 100, Color.GREEN, ms, SideName.RIGHT, newPlayer);
        
        
        Assert.assertEquals("Toegewezen speler niet correct", newPlayer, side.getBindedPlayer());
        Assert.assertEquals(100, side.getLineX1(), 0);
        Assert.assertEquals(200, side.getLineX2(), 0);
        Assert.assertEquals(200, side.getLineY1(), 0);
        Assert.assertEquals(100, side.getLineY2(), 0);
        Assert.assertEquals("Sidename incorrect ", SideName.RIGHT, side.getSideName());
    }  
}