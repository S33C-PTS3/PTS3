/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.awt.Color;
import java.util.Random;
import processing.core.*;

/**
 *
 * @author rens
 * This class represents the Puck on the HockeyField
 */
public class Puck extends PApplet {

    PApplet parentApplet;
    private final double Xvelocity = 3;
    private final double Yvelocity = 3;
    private final float Xpos;
    private final float Ypos;
    private final PVector velocity;
    private final PVector position;
    private final Color color;
    private final double diameter;
    private final Random randomizer = new Random();

    /**
     * Constructor used for puck
     * @param parent 
     */
    public Puck(PApplet parent) {
        this.parentApplet = parent;
        this.color = Color.RED;
        this.Xpos = 280;
        this.Ypos = 323;        
        this.position = new PVector((float) Xpos, (float) Ypos);
        
        this.velocity = new PVector((float) Xvelocity, (float) Yvelocity);
        randomizePuck();
        this.diameter = 520 * 0.04;
    }
    
    /**
     * Returns PVector for current position of this object.
     * @return 
     */
    public PVector getPosition()
    {
        return position;
    }

    /**
     * Returns the x postion for this object.
     * @return 
     */
    public float getXpos() {
        return position.x;
    }

    /**
     * Returns the y position for this object.
     * @return 
     */
    public float getYpos() {
        return this.position.y;
    }

    /**
     * Returns the x velocity for this object.
     * @return 
     */
    public double getXvelocity() {
        return this.velocity.x;
    }
    
    /**
     * Sets the x position for this object.
     * @param x 
     */
    public void setXpos(float x)
    {
        this.position.x = x;
    }
    
    /**
     * Sets the y position for this object.
     * @param y 
     */
    public void setYpos(float y)
    {
        this.position.y = y;
    }

    /**
     * Sets velocity when pucks hits a line. Given are the starting point of the line and ending.
     * @param linePos1
     * @param linePos2 
     */
    public void setVelocityWithoutNormal(PVector linePos1, PVector linePos2) {
        PVector baseDelta = PVector.sub(linePos2, linePos1);
        baseDelta.normalize();
        PVector normal = new PVector(-baseDelta.y, baseDelta.x);
        PVector incidence = PVector.mult(velocity, -1);
        incidence.normalize();
        // calculate dot product of incident vector and base top normal 
        float dot = incidence.dot(normal);
        velocity.set(2 * normal.x * dot - incidence.x, 2 * normal.y * dot - incidence.y, 0);
        velocity.mult((float) Xvelocity);
    }

    /**
     * Gets Y velocity for this object
     * @return 
     */
    public double getYvelocity() {
        return this.velocity.y;
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
    public void setVelocityWithNormal(PVector linePos1, PVector linePos2) {
        PVector normal = PVector.sub(linePos2, linePos1);
        normal.normalize();
        PVector incidence = PVector.mult(velocity, -1);
        incidence.normalize();
        // calculate dot product of incident vector and base top normal 
        float dot = incidence.dot(normal);
        velocity.set(2 * normal.x * dot - incidence.x, 2 * normal.y * dot - incidence.y, 0);
        velocity.mult((float) Xvelocity);
    }

    /**
     * Changes position infleunced by velocity
     */
    public void move() {
        position.add(velocity);

    }

    /**
     * Displays Puck on the field.
     */
    public void display() {
        parentApplet.ellipseMode(CENTER);
        parentApplet.strokeWeight(0);
        parentApplet.fill(color.getRGB());
        parentApplet.ellipse((float) position.x, (float) position.y, (float) diameter, (float) diameter);
    }
    
    /**
     * Changes the velocity randomly to negative or positive.
     */
    public void randomizePuck()
    {
        if (randomizer.nextDouble() > .5) 
        {
            this.velocity.x *= -1;
        }
        else
        {
            this.velocity.y *= -1;
        }
    }
    
    public PVector getVelocity()
    {
        return velocity;
    }
}
