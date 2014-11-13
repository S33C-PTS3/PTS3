/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey.domain;

import java.awt.Color;
import java.util.Random;
import javafx.geometry.Point2D;

/**
 *
 * @author rens
 * This class represents the Puck on the HockeyField
 */
public class Puck{

    private double Xvelocity = 3;
    private double Yvelocity = 3;
    private float Xpos;
    private float Ypos;
    private Point2D velocity;
    private Point2D position;
    private final Color color;
    private final double diameter;
    private final Random randomizer = new Random();

    /**
     * Constructor used for puck
     */
    public Puck() {
        this.color = Color.RED;
        this.Xpos = 280;
        this.Ypos = 323;        
        this.position = new Point2D((float) Xpos, (float) Ypos);
        
        this.velocity = new Point2D((float) Xvelocity, (float) Yvelocity);
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

    /**
     * Returns the x postion for this object.
     * @return 
     */
    public float getXpos() {
        return (float)position.getX();
    }

    /**
     * Returns the y position for this object.
     * @return 
     */
    public float getYpos() {
        return (float)this.position.getY();
    }

    /**
     * Returns the x velocity for this object.
     * @return 
     */
    public double getXvelocity() {
        return this.velocity.getX();
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
        Point2D baseDelta = linePos2.subtract(linePos1);
        baseDelta.normalize();
        Point2D normal = new Point2D(-baseDelta.getY(), baseDelta.getX());
        Point2D incidence = velocity.multiply(-1);
        incidence.normalize();
        // calculate dot product of incident vector and base top normal 
        float dot = (float)incidence.dotProduct(normal);
        velocity = new Point2D(2 * normal.getX() * dot - incidence.getX(), 2 * normal.getY() * dot - incidence.getY());
        velocity.multiply((float) Xvelocity);
    }

    /**
     * Gets Y velocity for this object
     * @return 
     */
    public double getYvelocity() {
        return this.velocity.getY();
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
        Point2D normal = linePos2.subtract(linePos1);
        normal.normalize();
        Point2D incidence = velocity.multiply(-1);
        incidence.normalize();
        // calculate dot product of incident vector and base top normal 
        float dot = (float)incidence.dotProduct(normal);
        velocity = new Point2D(2 * normal.getX() * dot - incidence.getX(), 2 * normal.getY() * dot - incidence.getY());
        velocity.multiply((float) Xvelocity);
    }

    /**
     * Changes position influenced by velocity
     */
    public void move() {
        position.add(velocity);
    }

    /**
     * Displays Puck on the field.
     */
    /*public void display() {
        parentApplet.ellipseMode(CENTER);
        parentApplet.strokeWeight(0);
        parentApplet.fill(color.getRGB());
        parentApplet.ellipse((float) position.x, (float) position.y, (float) diameter, (float) diameter);
    }*/
    
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
