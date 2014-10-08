/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import airhockey.domain.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Joep Kerste
 */
public class PuckTesting {
    @Test
    public void testConstructor()
    {
        double X = 100;
        double Y = 100;
        double velocity = 25;
        Puck newPuck = new Puck(X, Y, velocity);
        
        Assert.assertEquals("X cöordinaat incorrect", X, newPuck.getX());
        Assert.assertEquals("Y cöordinaat incorrect", Y, newPuck.getY());
        Assert.assertEquals("Velocity incorrect", velocity, newPuck.getVelocity());
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
