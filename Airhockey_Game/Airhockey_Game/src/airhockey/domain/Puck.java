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
 */
public class Puck extends PApplet {

    PApplet parent;
    private double Xvelocity = 3;
    private double Yvelocity = 3;
    private float Xpos;
    private float Ypos;
    private PVector velocity;
    private PVector position;
    private final Color color;
    private final double diameter;
    private final Random randomizer = new Random();

    public Puck(PApplet parent) {
        this.parent = parent;
        this.color = Color.RED;
        this.Xpos = 280;
        this.Ypos = 323;        
        this.position = new PVector((float) Xpos, (float) Ypos);
        
        this.velocity = new PVector((float) Xvelocity, (float) Yvelocity);
        randomizePuck();
        this.diameter = 520 * 0.04;
    }
    
    public PVector getPosition()
    {
        return position;
    }

    public float getXpos() {
        return position.x;
    }

    public float getYpos() {
        return this.position.y;
    }

    public double getXvelocity() {
        return this.velocity.x;
    }
    
    public void setXpos(float x)
    {
        this.position.x = x;
    }
    
    public void setYpos(float y)
    {
        this.position.y = y;
    }

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

    public double getYvelocity() {
        return this.velocity.y;
    }

    public double getDiameter() {
        return this.diameter;
    }

    public float getRadius() {
        float radius = (float) this.diameter / 2;
        return radius;
    }

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

    public void move() {
        position.add(velocity);

    }

    public void display() {
        parent.ellipseMode(CENTER);
        parent.strokeWeight(0);
        parent.fill(color.getRGB());
        parent.ellipse((float) position.x, (float) position.y, (float) diameter, (float) diameter);
    }
    
    public void randomizePuck()
    {
        if (randomizer.nextDouble() > .5) 
        {
            this.velocity.x *= -1;
        }
        
        if (randomizer.nextDouble() > .5) 
        {
            this.velocity.y *= -1;
        }
        
        //this.Xvelocity = (float)-randomizer.nextInt() +3;
        //this.Yvelocity = (float)-randomizer.nextInt() +3;
    }
}
