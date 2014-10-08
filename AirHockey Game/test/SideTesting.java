/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import airhockey.domain.Player;
import airhockey.domain.Side;
import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Joep Kerste
 */
public class SideTesting {
    @Test
    public void testConstructor()
    {
        Color newColor = new Color(100, 100, 0);
        Player newPlayer = new Player("newUser");
        Side newSide = new Side(newColor, newPlayer);
        
        Assert.assertEquals("Kleur nieuwe Side niet correct", newColor, newSide.getColor());
        Assert.assertEquals("Player nieuwe Side niet correct", newPlayer, newSide.getPlayer());
    }
    
    @Test
    public void testDisplay()
    {
        
    }    
}
