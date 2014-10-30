/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchprocessing;

import java.awt.Color;
import processing.core.PApplet;

/**
 *
 * @author rens
 */
public class Bat extends PApplet {
    private Color color;
    private double diameter;
    private double Xpos;
    private double Xpos2;
    private double Ypos;
    private double Ypos2;
    private double Xvelocity;
    private PApplet parent;
    
    public Bat(Color color, PApplet parent, double Xpos, double Ypos, double Xpos2, double Ypos2)
    {
        this.parent = parent;
        this.diameter = 10;
        this.color = Color.BLUE;
        this.Xpos = Xpos;
        this.Xpos2 = Xpos2;
        this.Ypos = Ypos;
        this.Ypos2 = Ypos2;
        this.Xvelocity = 10;
    }
    
    public double getXpos(){
        return this.Xpos;
    }
    
    public void setXpos(double xpos){
        this.Xpos = xpos;
    }
    
    public double getXpos2(){
        return this.Xpos2;
    }
    
    public void setXpos2(double xpos2){
        this.Xpos2 = xpos2;
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
        parent.line((float)Xpos, (float)Ypos, (float)Xpos2, (float)Ypos2);
    }

    public double getYpos()
    {
        return this.Ypos;
    }
    
    public double getYpos2()
    {
        return this.Ypos2;
    }
}
