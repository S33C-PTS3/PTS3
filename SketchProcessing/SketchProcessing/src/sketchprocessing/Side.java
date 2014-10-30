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
 * @author Sasa2905
 */
public class Side {
    
    private int lineX1;
    private int lineY1;
    private int lineX2;
    private int lineY2;
    private double goalX1;
    private double goalY1;
    private double goalX2;
    private double goalY2;
    private PApplet parent;
    private Color color;
    private Bat bat;
    private SideName sideName;
    Side(int x1, int y1, int x2, int y2, Color color, PApplet parent, SideName sideName)
    {
        this.lineX1 = x1;
        this.lineX2 = x2;
        this.lineY1 = y1;
        this.lineY2 = y2;
        this.parent = parent;
        this.color = color;
        this.bat = null;
        this.sideName = sideName;
    }

    public void display()
    {
       parent.stroke(color.getRGB());
       parent.strokeWeight(4);
       parent.line(lineX1,lineY1,lineX2,lineY2);
       //bat = new Bat(color,parent,lineX1+10,lineY1+10,lineX2+10,lineY2+10);
       //bat.display();
    }
    
    public SideName getSideName()
    {
        return sideName;
    }
    
    public void makeGoal(int i)
    {
        parent.stroke(color.getRGB());
        parent.strokeWeight(10);
        double batX1 = 0;
        double batY1 = 0;
        double batX2 = 0;
        double batY2 = 0;
        int distanceFromSide = 30;
        if(sideName == SideName.LEFT)
        {
            goalX1 = ((lineX1+lineX2)*0.7);
            goalY1 = ((lineY1+lineY2)*0.3);
            goalX2 = ((lineX1+lineX2)*0.3);
            goalY2 = ((lineY1+lineY2)*0.7);
            batX1 = ((lineX1 + lineX2) * 0.58 + distanceFromSide);
            batY1 = ((lineY1 + lineY2) * 0.42 + distanceFromSide/2);
            batX2 = ((lineX1 + lineX2) * 0.42 + distanceFromSide);
            batY2 = ((lineY1 + lineY2) * 0.58 + distanceFromSide/2);
        }
        else if(sideName == SideName.BOTTOM)
        {
            goalX1 = lineX1 + ((lineX2-lineX1) * 0.3);
            goalY1 = lineY1;
            goalX2 = lineX1 + ((lineX2 - lineX1) * 0.7);
            goalY2 = lineY2;
            batX1 = lineX1 + ((lineX2-lineX1) * 0.42);
            batY1 = lineY1 - distanceFromSide;
            batX2 = lineX1 + ((lineX2-lineX1) * 0.58);
            batY2 = lineY2 - distanceFromSide;
        }
        else if(sideName == SideName.RIGHT)
        {
            goalX1 = (lineX1-(lineX2*0.7));
            goalY1 = (lineY1-(lineY1*0.7));
            goalX2 = (lineX1-(lineX2*0.3));
            goalY2 = (lineY1-(lineY1*0.3));
            batX1 =  lineX1-(lineX2 * 0.58) - distanceFromSide;
            batY1 =  lineY1-(lineY1 * 0.58) + distanceFromSide/2;
            batX2 =  lineX1-(lineX2 * 0.42) - distanceFromSide;
            batY2 =  lineY1-(lineY1 * 0.42) + distanceFromSide/2;
        }
        parent.line((float)goalX1, (float)goalY1,(float)goalX2,(float)goalY2);
        bat = new Bat(color,parent,(float)batX1, (float)batY1, (float)batX2, (float)batY2);
        bat.display();
    }
    public boolean goal(double x, double y, double speed)
    {
////        if (lineX1 ==200)
////        {
////            if(lineX1<x || x>lineX2)
////            {
////                if(lineY1<y || y>lineY2)
////                {
//                    if(sideName == SideName.LEFT)
//                    {
//                        if(y < ((x*-1.7) + 693) && y > ((x*-1.8) + 693))                    
//                        {
//                            return true;
//                        }
//                    }
//                    
//                    if(sideName == SideName.RIGHT)
//                    {
//                        if(y < ((x*1.7) - 693) && y > (x*1.8) - 693)                    
//                        {
//                            return true;
//                        }
//                    }
////                }
////            }
////        }
////        if (lineX1 ==400)
////        {
////            if(lineX1<x || x>lineX2)
////            {
////                if(lineY1<y || y>lineY2)
////                {
////                    if(y > x*0.577 || y < x*0.578)
////                    {
////                        return true;
////                    }
////                }
//            }
//        }
//        if (lineX1 ==600)
//        {
//            if(lineX1<x || x>lineX2)
//            {
//                if(lineY1<y || y>lineY2)
//                {
//                    if(y > x*0.577 || y < x*0.578)
//                    {
//                        return true;
//                    }
//                }
//            }
//        }
          if(sideName == SideName.BOTTOM)
          {
            if (y>693 - speed)
            {
                return true;
            }
          }
        return false;
    }
}
