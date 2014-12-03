/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;


import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author rens
 * This class represents the Puck on the HockeyField
 */
public class Puck{

    private final double baseSpeed = 3;
    private Point2D velocity;
    private Point2D position;
    private final Color color;
    private final double diameter;
    private final Random randomizer = new Random();

    /**
     * Constructor used for puck
     */
    public Puck() {
        this.color = Color.BLACK;     
        this.position = new Point2D((float) 280, (float) 323);
        
        this.velocity = new Point2D((float) baseSpeed, (float) baseSpeed);
        randomizePuck();
        this.diameter = 520 * 0.04;
    }
    
    /**
     * Returns Point2D for current position of this object.
     * @return 
     */
    public Point2D getPosition()
    {
        return position;
    }
    
    public Color getColor()
    {
        return this.color;
    }
    /**
     * Sets the position for this object.
     * @param position
     */
    public void setPosition(Point2D position)
    {
        this.position = position;
    }

    /**
     * Sets velocity when pucks hits a line. Given are the starting point of the line and ending.
     * @param linePos1
     * @param linePos2 
     */
    public void setVelocityWithoutNormal(Point2D linePos1, Point2D linePos2) {
        Point2D baseDelta = linePos2.subtract(linePos1).normalize();
        Point2D normal = new Point2D(-baseDelta.getY(), baseDelta.getX());
        Point2D incidence = velocity.multiply(-1).normalize();
        // calculate dot product of incident vector and base top normal 
        float dot = (float)incidence.dotProduct(normal);
        velocity = new Point2D(2 * normal.getX() * dot - incidence.getX(), 2 * normal.getY() * dot - incidence.getY());
        velocity = velocity.multiply((float)baseSpeed);
    }

    /**
     * Gets diameter for this object.
     * @return 
     */
    public double getDiameter() {
        return this.diameter;
    }

    /**
     * Gets radius for this object.
     * @return 
     */
    public float getRadius() {
        float radius = (float) this.diameter / 2;
        return radius;
    }

    /**
     * Sets velocity when pucks hits a line. Given are the coordinates of the line between point of impatct and center of the this object.
     * @param linePos1
     * @param linePos2 
     */
    public void setVelocityWithNormal(Point2D linePos1, Point2D linePos2) {
        Point2D normal = linePos2.subtract(linePos1).normalize();
        Point2D incidence = velocity.multiply(-1).normalize();
        // calculate dot product of incident vector and base top normal 
        float dot = (float)incidence.dotProduct(normal);
        velocity = new Point2D(2 * normal.getX() * dot - incidence.getX(), 2 * normal.getY() * dot - incidence.getY());
        velocity = velocity.multiply((float)baseSpeed);
    }

    /**
     * Changes position influenced by velocity
     */
    public void move() {
        
        position = position.add(velocity);
    }
    
    /**
     * Changes the velocity randomly to negative or positive.
     */
    public void randomizePuck()
    {
        if (randomizer.nextDouble() > .5) 
        {
            this.velocity = new Point2D(velocity.getX() * -1, velocity.getY());
        }
        
        if (randomizer.nextDouble() > .5) 
        {
            this.velocity = new Point2D(velocity.getX(), velocity.getY() *-1);
        }
    }
    
    public Point2D getVelocity()
    {
        return this.velocity;
    }
}
