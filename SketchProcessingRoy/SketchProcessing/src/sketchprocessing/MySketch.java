/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchprocessing;

import java.awt.Color;
import processing.core.*;
/**
 *
 * @author Sasa2905
 */
public class MySketch extends PApplet{
    
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
    @Override
    public void setup()
    {
      size(800,700);
      sides = new Side[3];
      colors = new Color[3];
      colors[0] = Color.RED;
      colors[1] = Color.BLUE;
      colors[2] = Color.GREEN;
      int zijdeX1 = 400;
      int zijdeY1 = 0;
      int zijdeX2 = 0;
      int zijdeY2 = 693;
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
            zijdeX2 = 800;
            zijdeY2 = 693;
            sideName = SideName.BOTTOM;
          }
          if(i == 2)
          {
            zijdeX2 = 400;
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
      
    }

    @Override
    public void draw()
    {
      background(255);
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
      HockeyField field = new HockeyField(puck,sides);
      field.checkColl();
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
          x1 -= speed;
          x2 -= speed;
        }
        else if(keyCode == RIGHT)
        {
          x1 += speed;
          x2 += speed;
        }
      }
    }
    
}
