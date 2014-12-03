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
    private final Color color;
    private final double diameter;
    private final Random randomizer;

    public Puck(PApplet parent)
    {
        randomizer = new Random();
        this.parent = parent;
        this.color = Color.RED;
        this.Xpos = 400;
        this.Ypos = 300; 
        this.Xvelocity = randomizer.nextDouble() + 2;
        this.Yvelocity = -randomizer.nextDouble() +2;
        this.diameter = 600 * 0.04;
    }

    public double getXpos()
    {
        return this.Xpos;
    }

    public double getYpos()
    {
        return this.Ypos;
    }

    public double getXvelocity()
    {
        return this.Xvelocity;
    }

    public void setXvelocity(double newXvelocity)
    {
        this.Xvelocity *= newXvelocity;
    }

    public double getYvelocity()
    {
        return this.Yvelocity;
    }
    
    public double getDiameter()
    {
        return this.diameter;
    }

    public void setYvelocity(double newYvelocity)
    {
        this.Yvelocity *= newYvelocity;
    }

    public void move()
    {
        this.Xpos += this.Xvelocity;
        this.Ypos += this.Yvelocity;

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

    public void display()
    {
        parent.strokeWeight(0);
        parent.fill(color.getRGB());
        parent.ellipse((float) Xpos, (float) Ypos, (float) diameter, (float) diameter);
    }
}
