/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Puck;
import javafx.geometry.Point2D;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.*;
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
        
        Puck newPuck = new Puck();
        float radius = (float) diameter / 2;
        
        Assert.assertEquals(X, newPuck.getPosition().getX(), 0);
        Assert.assertEquals(Y, newPuck.getPosition().getY() ,0);
        Assert.assertEquals(diameter, newPuck.getDiameter(), 0);
        Assert.assertEquals(radius, newPuck.getRadius(), 0);
    }
    
    @Test
    public void testMove()
    {
        Puck puck = new Puck();
        Point2D position = puck.getPosition();
        puck.move();
        Point2D positionNieuw = puck.getPosition();
        Assert.assertThat("Puck did not move",position, is(not(positionNieuw.toString())));
    }
    
    @Test
    public void testRandomizer()
    {
        Puck puck = new Puck();
        Point2D velocity = puck.getVelocity();
        puck.randomizePuck();
        Point2D velocityNieuw = puck.getVelocity();
        Assert.assertThat("velocity did not change", velocity, is(not(velocityNieuw.toString())));
    }
}