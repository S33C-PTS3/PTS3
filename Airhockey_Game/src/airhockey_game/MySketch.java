/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import java.awt.Color;
import airhockey.domain.*;
import processing.core.*;
import static processing.core.PConstants.CODED;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;
/**
 *
 * @author Sasa2905
 */
public class MySketch extends PApplet{
        
    final private float sizefactor = 0.7f;
    
    int x1 = 200;
    int y1 = 500;
    int x2 = 250;
    int y2 = 500;
    int speed = 5;
    int middenX = 0;
    int middenY = 0;
    Side[] sides;
    Color[] colors;
    Puck puck;
    HockeyField field;
    
    @Override
    public void setup()
    {
      size(Math.round(800*sizefactor),Math.round(700*sizefactor));
      sides = new Side[3];
      colors = new Color[3];
      colors[0] = Color.RED;
      colors[1] = Color.BLUE;
      colors[2] = Color.GREEN;
      int zijdeX1 = Math.round(400*sizefactor);
      int zijdeY1 = 0;
      int zijdeX2 = 0;
      int zijdeY2 = Math.round(693*sizefactor);
      SideName sideName = SideName.LEFT;
      puck = new Puck(this);
      for(int i = 0; i < 3; i++)
      {
          if(i != 0)
          {
            zijdeX1 = zijdeX2;
            zijdeY1 = zijdeY2;
          }
          if(i == 1)
          {
            zijdeX2 = Math.round(800*sizefactor);
            zijdeY2 = Math.round(693*sizefactor);
            sideName = SideName.BOTTOM;
          }
          if(i == 2)
          {
            zijdeX2 = Math.round(400*sizefactor);
            zijdeY2 = 0;
            sideName = SideName.RIGHT;
          }
          Side s = new Side(zijdeX1, zijdeY1, zijdeX2, zijdeY2, colors[i], this, sideName);
          sides[i] = s;
          middenX += zijdeX2;
          middenY += zijdeY2;
      }
      middenX /= 3;
      middenY /= 3;
      
    field = new HockeyField(puck,sides);  
    }

    @Override
    public void draw()
    {
      background(235);
      //line(x1,y1,x2,y2);
      for(int i = 0; i < 3; i++)
      {
        sides[i].display();
        sides[i].makeGoal(i);
      }
      stroke(0);
      strokeWeight(1);
      ellipse(middenX,middenY,5,5);
      puck.display();
      puck.move();
      field.checkColl();
//      field.checkBatColl();
      moveAIplayers();
//    line(400, 0, middenX, middenY);
//    line(0,693,middenX, middenY);
//    line(800,693,middenX, middenY);
    }

    @Override
    public void keyPressed()
    {
      if(key == CODED)
      {
        if(keyCode == LEFT)
        {
            if (sides[1].getGoalX1() < sides[1].getBat().getXpos()) 
            {
                sides[1].moveBat("1left");
            }
          x1 -= speed;
          x2 -= speed;
        }
        else if(keyCode == RIGHT)
        {
            if (sides[1].getGoalX2() > sides[1].getBat().getXpos()) {
                sides[1].moveBat("1right");
            }            
          x1 += speed;
          x2 += speed;
        }
      }
    }
    
    private void moveAIplayers()
    {
        if (puck.getYpos() < sides[0].getBat().getYpos()
                && sides[0].getGoalY1() < sides[0].getBat().getYpos()) 
        {
                sides[0].moveBat("0right");
        }
        else if (puck.getYpos() > sides[0].getBat().getYpos()
                && sides[0].getGoalY2() > sides[0].getBat().getYpos()) 
        {
                sides[0].moveBat("0left");
        }
        
        if (puck.getYpos() > sides[2].getBat().getYpos()
                && sides[2].getGoalY2() > sides[2].getBat().getYpos()) 
        {
            sides[2].moveBat("2right");
        }
        else if (puck.getYpos() < sides[2].getBat().getYpos()
                && sides[2].getGoalY1() < sides[2].getBat().getYpos()) 
        {
            sides[2].moveBat("2left");
        }
    }
    
}
