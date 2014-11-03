/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import airhockey.domain.*;
import org.junit.*;
import static org.junit.Assert.*;
import processing.core.PApplet;

/**
 *
 * @author Joep Kerste
 */
public class PuckTesting {
    @Test
    public void testConstructor()
    {
        double X = 280;
        double Y = 323;
        double diameter = 520 * 0.04;
        PApplet parent = new PApplet();
        Puck newPuck = new Puck(parent);
        
        Assert.assertEquals(X, newPuck.getX());
        Assert.assertEquals(Y, newPuck.getY());
        Assert.assertEquals(velocity, newPuck.getVelocity());
    }
    
    @Test
    public void testMove()
    {
        //TODO
    }
    
    @Test
    public void testDisplay()
    {
        //TODO
    }
    
    @Test
    public void checkGoal()
    {
        //TODO
    }
}
