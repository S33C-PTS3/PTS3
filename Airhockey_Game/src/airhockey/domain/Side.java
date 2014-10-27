/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.awt.Color;

/**
 *
 * @author Eric
 */
public class Side {
    private Color color;
    private double length;
    private double goalBeginX;
    private double goalEndX;
    private double goalBeginY;
    private double goalEndY;
    private Player bindedPlayer;
    private Bat bat;
    
    public Side(Color color, Player bindedPlayer)
    {
        
    }
    
    public void display()
    {
        
    }
    
    public Color getColor()
    {
        return color;
    }
    
    public Player getPlayer()
    {
        return bindedPlayer;
    }
    
    public void score()
    {
        bindedPlayer.changeScore(1);
    }
    
    public boolean goal(double x, double y)
    {
        if(goalBeginX<x || x>goalEndX)
        {
            if(goalBeginY<y || y>goalEndY)
            {
                if(y > x*0.577 || y < x*0.578)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
