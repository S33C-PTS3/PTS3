/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchprocessing;

import java.awt.Color;
import java.util.Random;
import processing.core.*;

/**
 *
 * @author rens
 */
public class Puck extends PApplet {

    PApplet parent;
    private double Xvelocity;
    private double Yvelocity;
    private double Xpos;
    private double Ypos;
    private PVector velocity;
    private PVector position;
    private final Color color;
    private final double diameter;
    private final Random randomizer;

    public Puck(PApplet parent) {
        randomizer = new Random();
        this.parent = parent;
        this.color = Color.RED;
        this.Xpos = 400;
        this.Ypos = 300;
        this.position = new PVector((float) Xpos, (float) Ypos);
        this.Xvelocity = randomizer.nextDouble() + 2;
        this.Yvelocity = -randomizer.nextDouble() + 2;
        this.velocity = new PVector((float) Xvelocity, (float) Yvelocity);
        this.diameter = 600 * 0.04;
    }

    public double getXpos() {
        return position.x;
    }

    public double getYpos() {
        return this.position.y;
    }

    public double getXvelocity() {
        return this.velocity.x;
    }

    public void setXvelocity(PVector linePos1, PVector linePos2) {
        PVector baseDelta = PVector.sub(linePos2,linePos1);
        baseDelta.normalize();
        PVector normal = new PVector(-baseDelta.y, baseDelta.x);
        PVector incidence = PVector.mult(velocity, -1);
        incidence.normalize();
        // calculate dot product of incident vector and base top normal 
        float dot = incidence.dot(normal);
        velocity.set(2 * normal.x * dot - incidence.x, 2 * normal.y * dot - incidence.y, 0);
        velocity.mult((float)Xvelocity);
    }

    public double getYvelocity() {
        return this.velocity.y;
    }

    public double getDiameter() {
        return this.diameter;
    }

    public void setYvelocity(double newYvelocity) {
        this.Yvelocity *= newYvelocity;
    }

    public void move() {
//        this.Xpos += this.Xvelocity;
//        this.Ypos += this.Yvelocity;
        position.add(velocity);

        // Check horizontal edges
//        if (Xpos > parent.width || Xpos < 0)
//        {
//            Xvelocity *= - 1;
//        }
//        //Check vertical edges
//        if (Ypos > parent.height || Ypos < 0)
//        {
//            Yvelocity *= - 1;
//        }
    }

    public void display() {
        parent.strokeWeight(0);
        parent.fill(color.getRGB());
        parent.ellipse((float) position.x, (float) position.y, (float) diameter, (float) diameter);
    }
}
