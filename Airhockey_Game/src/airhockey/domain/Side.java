/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.awt.Color;
import processing.core.PApplet;
import processing.core.PVector;

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
    double batX1 = 0;
    double batY1 = 0;
    double batX2 = 0;
    double batY2 = 0;

    Side(int x1, int y1, int x2, int y2, Color color, PApplet parent, SideName sideName) {
        this.lineX1 = x1;
        this.lineX2 = x2;
        this.lineY1 = y1;
        this.lineY2 = y2;
        this.parent = parent;
        this.color = color;
        this.bat = null;
        this.sideName = sideName;
        SideName sideBat = null;
        int distanceFromSide = 30;
        if (sideName == SideName.LEFT) {
            goalX1 = ((lineX1 + lineX2) * 0.7);
            goalY1 = ((lineY1 + lineY2) * 0.3);
            goalX2 = ((lineX1 + lineX2) * 0.3);
            goalY2 = ((lineY1 + lineY2) * 0.7);
            batX1 = ((lineX1 + lineX2) * 0.58 + distanceFromSide);
            batY1 = ((lineY1 + lineY2) * 0.42 + distanceFromSide / 2);
            batX2 = ((lineX1 + lineX2) * 0.42 + distanceFromSide);
            batY2 = ((lineY1 + lineY2) * 0.58 + distanceFromSide / 2);
            sideBat = SideName.BATLEFT;
        } else if (sideName == SideName.BOTTOM) {
            goalX1 = lineX1 + ((lineX2 - lineX1) * 0.3);
            goalY1 = lineY1;
            goalX2 = lineX1 + ((lineX2 - lineX1) * 0.7);
            goalY2 = lineY2;
            batX1 = lineX1 + ((lineX2 - lineX1) * 0.42);
            batY1 = lineY1 - distanceFromSide;
            batX2 = lineX1 + ((lineX2 - lineX1) * 0.58);
            batY2 = lineY2 - distanceFromSide;
            sideBat = SideName.BATBOTTOM;
        } else if (sideName == SideName.RIGHT) {
            goalX1 = (lineX1 - (lineX2 * 0.7));
            goalY1 = (lineY1 - (lineY1 * 0.7));
            goalX2 = (lineX1 - (lineX2 * 0.3));
            goalY2 = (lineY1 - (lineY1 * 0.3));
            batX1 = lineX1 - (lineX2 * 0.58) - distanceFromSide;
            batY1 = lineY1 - (lineY1 * 0.58) + distanceFromSide / 2;
            batX2 = lineX1 - (lineX2 * 0.42) - distanceFromSide;
            batY2 = lineY1 - (lineY1 * 0.42) + distanceFromSide / 2;
            sideBat = SideName.BATRIGHT;
        }

        double diameter = 600 * 0.08;
        bat = new Bat(color, parent, (float) batX1, (float) batY1, (float) diameter, sideBat);
        bat.display();
    }

    public void display() {
        parent.stroke(color.getRGB());
        parent.strokeWeight(4);
        parent.line(lineX1, lineY1, lineX2, lineY2);
       //bat = new Bat(color,parent,lineX1+10,lineY1+10,lineX2+10,lineY2+10);
        //bat.display();
    }
    
    public PVector getVector1()
    {
        return new PVector(this.lineX1, this.lineY1);
    }
    
    public PVector getVector2()
    {
        return new PVector(this.lineX2, this.lineY2);
    }
    

    public double getLineX1() {
        return this.lineX1;
    }

    public double getLineY1() {
        return this.lineY1;
    }

    public double getLineX2() {
        return this.lineX2;
    }

    public double getLineY2() {
        return this.lineY2;
    }

    public SideName getSideName() {
        return sideName;
    }

    public void makeGoal(int i) {
        parent.stroke(color.getRGB());
        parent.strokeWeight(10);

        parent.line((float) goalX1, (float) goalY1, (float) goalX2, (float) goalY2);
        bat.display();
    }

    public Side ballHitsWall(double x, double y, double xvel, double yvel) {
//        double dy = (lineY2-lineY1);
//        double dx = (lineX2-lineX1);
//        double rc = dy/dx;
//        double start = lineY2-lineX2*rc;
//        double xn = x - xvel*1.5;
//        double xp = x + xvel*1.5;
//        double yn = rc*xn+start;
//        double yp = rc*xp+start;
//        if(y <= yp + yvel *1.2 && y >= yn - yvel*1.2)
//        {
//            checkGoal(x,y);
//            return true;
//        }
//        return false;

        // first get the length of the line using the Pythagorean theorem
        float distX = lineX1 - lineX2;
        float distY = lineY1 - lineY2;
        double lineLength = Math.sqrt((distX * distX) + (distY * distY));

        // then solve for r
        double r = (((x - lineX1) * (lineX2 - lineX1)) + ((y - lineY1) * (lineY2 - lineY1))) / Math.pow(lineLength, 2);

        // get x,y points of the closest point
        double closestX = lineX1 + r * (lineX2 - lineX1);
        double closestY = lineY1 + r * (lineY2 - lineY1);

        // to get the length of the line, use the Pythagorean theorem again
        double distToPointX = closestX - x;
        double distToPointY = closestY - y;
        double distToPoint = Math.sqrt(Math.pow(distToPointX, 2) + Math.pow(distToPointY, 2));

        // if that distance is less than the radius of the ball: collision
        if (distToPoint <= (600 * 0.04) / 2) {
            return this;
        } else {
            return null;
        }
    }

    public void moveBat(String direction) {
        bat.move(direction);
    }

    public Bat getBat() {
        return bat;
    }

    public double getGoalY1() {
        return goalY1;
    }

    public double getGoalY2() {
        return goalY2;
    }

    public double getGoalX1() {
        return goalX1;
    }

    public double getGoalX2() {
        return goalX2;
    }

    public boolean checkGoal(double x, double y) {
        if (sideName.equals(SideName.LEFT)) {
            if (x < goalX1 && x > goalX2) {
                if (y > goalY1 && y < goalY2) {
                    System.out.println("GOAL");
                    return true;
                }
            }
        } else if (sideName.equals(SideName.RIGHT)) {
            if (x > goalX1 && x < goalX2) {
                if (y > goalY1 && y < goalY2) {
                    System.out.println("GOAL");
                    return true;
                }
            }
        } else if (sideName.equals(SideName.BOTTOM)) {
            if (x > goalX1 && x < goalX2) {
                if (y > goalY1 - 20 && y < goalY1 + 20) {
                    System.out.println("GOAL");
                    return true;
                }
            }
        }
        return false;
    }
    

    Side hasCollided(Puck p) {
        if(parent.dist((float) p.getXpos(), (float) p.getYpos(), (float) bat.getXpos() + bat.getWidth(), (float) bat.getYpos() + bat.getHeight()) < p.getDiameter() / 2 + bat.getDiameter() / 2)
        {
            return this;
        }
        return null;
    }
}
