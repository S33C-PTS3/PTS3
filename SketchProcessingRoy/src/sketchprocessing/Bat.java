/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchprocessing;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author rens
 */
public class Bat extends PApplet {
    private Color color;
    private float Xpos;
    private double diameter;
    private float Ypos;
    private double Xvelocity;
    private PApplet parent;
    private SideName sidename;
    
    public Bat(Color color, PApplet parent, float Xpos, float Ypos, double diameter, SideName sidename)
    {
        this.parent = parent;
        this.diameter = diameter;
        this.color = Color.BLUE;
        this.Xpos = Xpos;
        this.Ypos = Ypos;
        this.Xvelocity = 10;
        this.sidename = sidename;
    }
    
    public PVector getVector()
    {
        return new PVector(Xpos,Ypos);
    }
    
    public float getXpos(){
        return this.Xpos;
    }
    
    public SideName getSideName(){
        return this.sidename;
    }
    
    public void setXpos(float xpos){
        this.Xpos = xpos;
    }
    
    public double getXvelocity(){
        return this.Xvelocity;
    }
    
    public void setXvelocity(double Xvelocity){
        this.Xvelocity = Xvelocity;
    }
    
    public void display(){
        parent.strokeWeight((float)diameter);
        parent.fill(color.getRGB() + 100);
        parent.noStroke();
        parent.ellipse((float)Xpos, (float)Ypos, (float)diameter, (float)diameter);
    }

    public float getYpos()
    {
        return this.Ypos;
    }
    
    public void move(String direction)
    {
        if ("0right".equals(direction)) 
        {
            this.Xpos += .58;
            this.Ypos -= 1;
        }
        else if ("0left".equals(direction))
        {
            this.Xpos -= .58;
            this.Ypos += 1;
        }
        else if ("1right".equals(direction)) 
        {
            this.Xpos += this.Xvelocity;
        }
        else if ("1left".equals(direction))
        {
            this.Xpos -= this.Xvelocity;
        }
        else if ("2right".equals(direction))
        {
            this.Xpos += .58;
            this.Ypos += 1;
        }
        else if ("2left".equals(direction))
        {
            this.Xpos -= .58;
            this.Ypos -= 1;
        }
            
        display();
    }
    
    public double getDiameter()
    {
        return this.diameter;
    }
    public float getRadius()
    {
        float radius = (float)this.diameter/2;
        return radius;
    }
}
