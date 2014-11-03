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
public class MySketch extends PApplet {

    final private float sizefactor = 0.7f;
    int middleX = 0;
    int middleY = 0;
    Side[] sides;
    Color[] colors;
    Puck puck;
    private HockeyField field;
    String winner;

    @Override
    public void setup() {
        size(Math.round(800 * sizefactor), Math.round(700 * sizefactor));
        sides = new Side[3];
        colors = new Color[3];
        colors[0] = Color.BLUE;
        colors[1] = Color.RED;
        colors[2] = Color.GREEN;
        int zijdeX1 = Math.round(400 * sizefactor);
        int zijdeY1 = 0;
        int zijdeX2 = 0;
        int zijdeY2 = Math.round(693 * sizefactor);
        SideName sideName = SideName.LEFT;
        puck = new Puck(this);
        IPlayer player = new RobotPlayer("Karel");
        for (int i = 0; i < 3; i++) {
            if (i != 0) {
                zijdeX1 = zijdeX2;
                zijdeY1 = zijdeY2;
            }
            if (i == 1) {
                zijdeX2 = Math.round(800 * sizefactor);
                zijdeY2 = Math.round(693 * sizefactor);
                sideName = SideName.BOTTOM;
                player = new HumanPlayer("Eric");
            }
            if (i == 2) {
                zijdeX2 = Math.round(400 * sizefactor);
                zijdeY2 = 0;
                sideName = SideName.RIGHT;
                player = new RobotPlayer("Sjef");
            }
            Side s = new Side(zijdeX1, zijdeY1, zijdeX2, zijdeY2, colors[i], this, sideName, player);
            sides[i] = s;
            middleX += zijdeX2;
            middleY += zijdeY2;
        }
        middleX /= 3;
        middleY /= 3;

        field = new HockeyField(puck, sides, this);
    }

    @Override
    public void draw() {
        background(235);
        //line(x1,y1,x2,y2);
        if (field.display()) {
            ellipse(middleX, middleY, 5, 5);
            moveAIplayers();
        }
        stroke(0);
        strokeWeight(1);
    }

    @Override
    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == LEFT) {
                if (sides[1].getGoalX1() < sides[1].getBat().getXpos()) {
                    sides[1].moveBat("1left");
                }
            } else if (keyCode == RIGHT) {
                if (sides[1].getGoalX2() > sides[1].getBat().getXpos()) {
                    sides[1].moveBat("1right");
                }
            }
        }
    }

    private void moveAIplayers() {
        if (puck.getYpos() < sides[0].getBat().getYpos()
                && sides[0].getGoalY1() + 20 < sides[0].getBat().getYpos()) {
            sides[0].moveBat("0right");
        } else if (puck.getYpos() > sides[0].getBat().getYpos()
                && sides[0].getGoalY2() - 10 > sides[0].getBat().getYpos()) {
            sides[0].moveBat("0left");
        }

        if (puck.getYpos() > sides[2].getBat().getYpos()
                && sides[2].getGoalY2() - 10 > sides[2].getBat().getYpos()) {
            sides[2].moveBat("2right");
        } else if (puck.getYpos() < sides[2].getBat().getYpos()
                && sides[2].getGoalY1() + 20 < sides[2].getBat().getYpos()) {
            sides[2].moveBat("2left");
        }
    }

    public HockeyField getHockeyfield() {
        return this.field;
    }

    public String getWinner() {
        return winner;
    }

}
