/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import airhockey_game.SerializationManager;
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
    private PApplet parentApplet;
    private SideName sidename;
    private SerializationManager serMan = new SerializationManager();
    double rightSpeedModifier = 1;
    double leftSpeedModifier = 1;
    
    public Bat(Color color, PApplet parent, float Xpos, float Ypos, double diameter, SideName sidename)
    {
        this.parentApplet = parent;
        this.diameter = diameter;
        this.color = Color.BLUE;
        this.Xpos = Xpos;
        this.Ypos = Ypos;
        
        //Speed of own bat depends on difficulty
        Settings loadedSettings = serMan.loadAIsettings();
        int overallDifficulty = getOverallDifficulty(loadedSettings);
        int xVel = 10;
        switch(overallDifficulty)
        {
            case 2:
                xVel = 12;
                break;
            case 3:
                xVel = 11;
                break;
            case 4:
                xVel = 10;
                break;
            case 5:
                xVel = 9;
                break;
            case 6:
                xVel = 8;
                break;
        }
        this.Xvelocity = xVel;
        
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
        parentApplet.strokeWeight((float)diameter);
        parentApplet.fill(color.getRGB() + 100);
        parentApplet.noStroke();
        parentApplet.ellipse((float)Xpos, (float)Ypos, (float)diameter, (float)diameter);
    }

    public float getYpos()
    {
        return this.Ypos;
    }
    
    public void move(String direction)
    {
        Settings s = serMan.loadAIsettings();
        
        
        if ("0right".equals(direction)) 
        {
            this.Xpos += (.58*leftSpeedModifier);
            this.Ypos -= (1*leftSpeedModifier);
        }
        else if ("0left".equals(direction))
        {
            this.Xpos -= (.58*leftSpeedModifier);
            this.Ypos += (1*leftSpeedModifier);
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
            this.Xpos += (.58*rightSpeedModifier);
            this.Ypos += (1*rightSpeedModifier);
        }
        else if ("2left".equals(direction))
        {
            this.Xpos -= (.58*rightSpeedModifier);
            this.Ypos -= (1*rightSpeedModifier);
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
    
    //Difficulty of AI get assigned number values to calculte total
    private int getOverallDifficulty(Settings s)
    {
        int overallDiff;
        int diff1=0;
        int diff2=0;
        
        switch (s.getDifficulty1())
        {
            case EASY:
                diff1 = 1;
                leftSpeedModifier = 1;
                break;
            case MEDIUM:
                diff1 = 2;
                leftSpeedModifier = 1.6;
                break;
            case HARD:
                diff1 = 3;
                leftSpeedModifier = 2.2;
                break;
        }
            
        switch (s.getDifficulty2())
        {
            case EASY:
                diff2 = 1;
                rightSpeedModifier = 1;
                break;
            case MEDIUM:
                rightSpeedModifier = 1.6;
                diff2 = 2;
                break;
            case HARD:
                rightSpeedModifier = 2.2;
                diff2 = 3;
                break;
        }
        
        overallDiff = diff1+diff2;
        return overallDiff;
    }
}
