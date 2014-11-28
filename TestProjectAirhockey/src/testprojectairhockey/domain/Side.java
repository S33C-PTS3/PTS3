/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey.domain;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Sasa2905
 * this class represent a side on the HockeyField
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
    private Color color;
    private Bat bat;
    private SideName sideName;
    private IPlayer bindedPlayer;
    double batX1 = 0;
    double batY1 = 0;
    double batX2 = 0;
    double batY2 = 0;

    /**
     * Constructor used for Side.
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param color
     * @param sideName
     * @param bp 
     */
    public Side(int x1, int y1, int x2, int y2, Color color, SideName sideName, IPlayer bp) {
        this.lineX1 = x1;
        this.lineX2 = x2;
        this.lineY1 = y1;
        this.lineY2 = y2;
        this.color = color;
        this.bat = null;
        this.sideName = sideName;
        this.bindedPlayer = bp;
        SideName sideBat = null;
        int distanceFromSide = 30;
        if (sideName.equals(SideName.LEFT)) {
            goalX1 = ((lineX1 + lineX2) * 0.7);
            goalY1 = ((lineY1 + lineY2) * 0.3);
            goalX2 = ((lineX1 + lineX2) * 0.3);
            goalY2 = ((lineY1 + lineY2) * 0.7);
            batX1 = ((lineX1 + lineX2) * 0.58 + distanceFromSide);
            batY1 = ((lineY1 + lineY2) * 0.42 + distanceFromSide / 2);
            batX2 = ((lineX1 + lineX2) * 0.42 + distanceFromSide);
            batY2 = ((lineY1 + lineY2) * 0.58 + distanceFromSide / 2);
            sideBat = SideName.BATLEFT;
        } else if (sideName.equals(SideName.BOTTOM)) {
            goalX1 = lineX1 + ((lineX2 - lineX1) * 0.3);
            goalY1 = lineY1;
            goalX2 = lineX1 + ((lineX2 - lineX1) * 0.7);
            goalY2 = lineY2;
            batX1 = lineX1 + ((lineX2 - lineX1) * 0.42);
            batY1 = lineY1 - distanceFromSide;
            batX2 = lineX1 + ((lineX2 - lineX1) * 0.58);
            batY2 = lineY2 - distanceFromSide;
            sideBat = SideName.BATBOTTOM;
        } else if (sideName.equals(SideName.RIGHT)) {
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

        double diameter = 520 * 0.08;
        bat = new Bat(color, (float) batX1, (float) batY1, (float) diameter, sideBat);
    }
    
    public Color getColor()
    {
        return this.color;
    }

    /**
     * Returns player binded to this side
     * @return 
     */
    public IPlayer getBindedPlayer() {
        return this.bindedPlayer;
    }

    /**
     * Returns a Point2D for the starting point of this line.
     * @return 
     */
    public Point2D getStartPoint() {
        return new Point2D(this.lineX1, this.lineY1);
    }

    /**
     * Returns a Point2D for the ending point of thies line.
     * @return 
     */
    public Point2D getEndPoint() {
        return new Point2D(this.lineX2, this.lineY2);
    }

    /**
     * Returns the x coordinate of starting point for this sideline
     * @return te
     */
    public double getLineX1() {
        return this.lineX1;
    }

    /**
     * Returns the y coordinate of starting point for this sideline
     * @return te
     */
    public double getLineY1() {
        return this.lineY1;
    }

    /**
     * Returns the x coordinate of ending point for this sideline
     * @return te
     */
    public double getLineX2() {
        return this.lineX2;
    }

    /**
     * Returns the y coordinate of ending point for this sideline
     * @return te
     */
    public double getLineY2() {
        return this.lineY2;
    }

    /**
     * Returns the SideName for this Side.
     * @return 
     */
    public SideName getSideName() {
        return sideName;
    }

    /**
     * Collision check between puck and this side.
     * @param x
     * @param y
     * @param xvel
     * @param yvel
     * @return 
     */
    public Side ballHitsWall(double x, double y, double xvel, double yvel) {
        // first get the length of the line using the Pythagorean theorem
        float distX = lineX1 - lineX2;
        float distY = lineY1 - lineY2;
        double lineLength = Math.sqrt((distX * distX) + (distY * distY));

        // then solve for radius
        double radius = (((x - lineX1) * (lineX2 - lineX1)) + ((y - lineY1) * (lineY2 - lineY1))) / Math.pow(lineLength, 2);

        // get x,y points of the closest point
        double closestX = lineX1 + radius * (lineX2 - lineX1);
        double closestY = lineY1 + radius * (lineY2 - lineY1);

        // to get the length of the line, use the Pythagorean theorem again
        double distToPointX = closestX - x;
        double distToPointY = closestY - y;
        double distToPoint = Math.sqrt(Math.pow(distToPointX, 2) + Math.pow(distToPointY, 2));

        // if that distance is less than the radius of the ball: collision
        if (distToPoint <= (520 * 0.04) / 2) {
            return this;
        } else {
            return null;
        }
    }

    /**
     * Moves the bat to a given direction.
     * @param direction 
     */
    public void moveBat(String direction) {
        bat.move(direction);
    }

    /**
     * Returns this sides bat
     * @return 
     */
    public Bat getBat() {
        return bat;
    }

    /**
     * Returns starting y point of this sides goal
     * @return 
     */
    public double getGoalY1() {
        return goalY1;
    }
    
    /**
     * Returns ending y point of this sides goal
     * @return 
     */
    public double getGoalY2() {
        return goalY2;
    }

    /**
     * Returns starting x point of this sides goal
     * @return 
     */
    public double getGoalX1() {
        return goalX1;
    }

    /**
     * Returns ending x point of this sides goal
     * @return 
     */
    public double getGoalX2() {
        return goalX2;
    }

    /**
     * Checks if the boal collides with the goal line.
     * @param x
     * @param y
     * @param xvel
     * @param yvel
     * @param radius
     * @return 
     */
    public Side checkGoalLine(double x, double y, double xvel, double yvel, float radius) {
        // first get the length of the line using the Pythagorean theorem
        float distX = lineX1 - lineX2;
        float distY = lineY1 - lineY2;
        double lineLength = Math.sqrt((distX * distX) + (distY * distY));

        // then solve for radius
        double ballRadius = (((x - lineX1) * (lineX2 - lineX1)) + ((y - lineY1) * (lineY2 - lineY1))) / Math.pow(lineLength, 2);

        // get x,y points of the closest point
        double closestX = lineX1 + ballRadius * (lineX2 - lineX1);
        double closestY = lineY1 + ballRadius * (lineY2 - lineY1);

        // to get the length of the line, use the Pythagorean theorem again
        double distToPointX = closestX - x;
        double distToPointY = closestY - y;
        double distToPoint = Math.sqrt(Math.pow(distToPointX, 2) + Math.pow(distToPointY, 2));

        // if that distance is less than the radius of the ball: collision
        if (distToPoint <= (600 * 0.04) / 2 && checkGoal(x,y)) {
            return this;
        } else {
            return null;
        }
       
    }

    /**
     * Checks if th puck is inside the goal area.
     * @param puckX
     * @param puckY
     * @return 
     */
    public boolean checkGoal(double puckX, double puckY) {
        if (sideName.equals(SideName.LEFT)) {
            if (puckX < goalX1 && puckX > goalX2) {
                if (puckY > goalY1 && puckY < goalY2) {
                    System.out.println("GOAL");
                    return true;
                }
            }
        } else if (sideName.equals(SideName.RIGHT)) {
            if (puckX > goalX1 && puckX < goalX2) {
                if (puckY > goalY1 && puckY < goalY2) {
                    System.out.println("GOAL");
                    return true;
                }
            }
        } else if (sideName.equals(SideName.BOTTOM)) {
            if (puckX > goalX1 && puckX < goalX2) {
                if (puckY > goalY1 - 20 && puckY < goalY1 + 20) {
                    System.out.println("GOAL");
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the puck collided with this sides bat.
     * @param p
     * @return 
     */
    Side hasCollided(Puck p) {
        
        float xd = bat.getXpos() - (float)p.getPosition().getX();
        float yd = bat.getYpos() - (float)p.getPosition().getY();
        
        float sumRadius = bat.getRadius() + p.getRadius();
        float sqrRadius = sumRadius * sumRadius;
        
        float distSqr = (xd * xd) + (yd*yd);
        if(distSqr <= sqrRadius)
        {
            return this;
        }
        return null;
    }
}
