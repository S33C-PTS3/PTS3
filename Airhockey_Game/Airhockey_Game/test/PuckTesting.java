/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import airhockey.domain.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.*;
import static org.junit.Assert.*;
import processing.core.PApplet;
import processing.core.PVector;
/**
 *
 * @author Joep Kerste
 */
public class PuckTesting {
    
    PApplet parent;
    @Before
    public void setUp()
    {
        parent = new PApplet();
    }
    
    @Test
    public void testConstructor()
    {
        double X = 280;
        double Y = 323;
        double diameter = 520 * 0.04;
        
        Puck newPuck = new Puck(parent);
        float radius = (float) diameter / 2;
        
        Assert.assertEquals(X, newPuck.getXpos(), 0);
        Assert.assertEquals(Y, newPuck.getYpos() ,0);
        Assert.assertEquals(diameter, newPuck.getDiameter(), 0);
        Assert.assertEquals(radius, newPuck.getRadius(), 0);
    }
    
    @Test
    public void testMove()
    {
        Puck puck = new Puck(parent);
        PVector position = puck.getPosition();
        String positionString = position.toString();
        puck.move();
        PVector positionNieuw = puck.getPosition();
        Assert.assertThat(positionString, is(not(positionNieuw.toString())));
    }
    
    @Test
    public void testRandomizer()
    {
        Puck puck = new Puck(parent);
        PVector velocity = puck.getVelocity();
        String velocityString = velocity.toString();
        puck.randomizePuck();
        PVector velocityNieuw = puck.getVelocity();
        Assert.assertThat(velocityString, is(not(velocityNieuw.toString())));
    }
}