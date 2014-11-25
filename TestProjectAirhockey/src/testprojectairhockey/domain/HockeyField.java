/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.InvalidationListener;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 *
 * @author Eric The HockeyField represents the board where the game is played on
 */
public class HockeyField {

    final private float sizefactor = 0.7f;
    int middleX = 0;
    int middleY = 0;
    Color[] colors;

    private Puck puck;
    private Side[] sides;
    private SideName lastHitSide;
    private Side hitSide;
    private Side hitBat;
    private Side goal;
    private List<Side> batsHitsPuck;
    private int rounds;
    private ArrayList<Integer> scores;
    private ArrayList gameResult = null;
    private boolean gameOver = false;

    /**
     * Constructor used for HockeyField.
     */
    public HockeyField()
    {
        puck = new Puck();

        this.lastHitSide = null;
        hitSide = null;
        batsHitsPuck = new ArrayList<>();
        rounds = 1;
        scores = new ArrayList<>();
        gameResult = new ArrayList();

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
        IPlayer player = new RobotPlayer("Karel");
        for (int i = 0; i < 3; i++)
        {
            if (i != 0)
            {
                zijdeX1 = zijdeX2;
                zijdeY1 = zijdeY2;
            }
            if (i == 1)
            {
                zijdeX2 = Math.round(800 * sizefactor);
                zijdeY2 = Math.round(693 * sizefactor);
                sideName = SideName.BOTTOM;
                player = new HumanPlayer();
            }
            if (i == 2)
            {
                zijdeX2 = Math.round(400 * sizefactor);
                zijdeY2 = 0;
                sideName = SideName.RIGHT;
                player = new RobotPlayer("Sjef");
            }
            Side s = new Side(zijdeX1, zijdeY1, zijdeX2, zijdeY2, colors[i], sideName, player);
            sides[i] = s;
            middleX += zijdeX2;
            middleY += zijdeY2;
        }
        middleX /= 3;
        middleY /= 3;
    }

    /**
     * Method used to display the HockeyField
     *
     * @return
     */
    /*public boolean display() {
     checkColl();
     if (!gameOver) {
     puck.move();
     for (int i = 0; i < 3; i++) {
     // sides[i].display(i * 20);
     //sides[i].makeGoal(i);
     }
     puck.display();
     return true;
     } else 
     {
     int highscore = 0;
     Collections.sort(gameResult, new Comparator<Side>() {
     @Override
     public int compare(Side p1, Side p2) {
     return p2.getBindedPlayer().getInGameScore() - p1.getBindedPlayer().getInGameScore(); // Ascending
     }
     });
     for (int i = 0; i < 3; i++) {
     Side tempSide = (Side)gameResult.get(i);

     //parentApplet.text(i+1 + ". " + tempSide.getBindedPlayer().toString() + ": " + tempSide.getBindedPlayer().getInGameScore(), 200, 200 + i * 50);
     }
     }
     return false;
     }*/
    /**
     * Method to check if the puck collided with a side, a goal or a bat and
     * changes its direction
     */
    public void checkColl()
    {
        for (int i = 0; i < sides.length; i++)
        {
            hitSide = sides[i].ballHitsWall(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity());
            hitBat = sides[i].hasCollided(puck);
            goal = sides[i].checkGoalLine(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity(), puck.getRadius());
            if (hitSide != null && lastHitSide != hitSide.getSideName())
            {
                puck.setVelocityWithoutNormal(hitSide.getVector1(), hitSide.getVector2());
                lastHitSide = sides[i].getSideName();
                hitSide = null;
                System.out.println("1");
            }
            if (hitBat != null && lastHitSide != hitBat.getBat().getSideName())
            {
                puck.setVelocityWithNormal(hitBat.getBat().getVector(), puck.getPosition());
                if (batsHitsPuck.isEmpty())
                {
                    batsHitsPuck.add(hitBat);
                }
                else if (!hitBat.equals(batsHitsPuck.get(batsHitsPuck.size() - 1)))
                {
                    batsHitsPuck.add(hitBat);
                }
                lastHitSide = sides[i].getBat().getSideName();
                hitBat = null;
                System.out.println("2");
            }
            if (goal != null)
            {
                System.out.println(goal.getSideName().toString());
                //goal.getBindedPlayer().changeScore(-1);

                if (!batsHitsPuck.isEmpty())
                {

                    if (goal.equals(batsHitsPuck.get(batsHitsPuck.size() - 1)))
                    {
                        if (batsHitsPuck.size() <= 1)
                        {
                            System.out.println("eigen goal gemaakt en aangeraakt door" + batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().toString());
                            batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().changeScore(-1);
                        }
                        else
                        {
                            System.out.println("1 Gescoord door " + batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().toString());
                            System.out.println("1 Gescoord bij " + goal.getBindedPlayer().toString());
                            batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().changeScore(-1);
                        }
                        resetGame();

                    }
                    else if (batsHitsPuck.size() > 0)
                    {
                        System.out.println("2 Gescoord door " + batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().toString());
                        System.out.println("2 Gescoord bij " + goal.getBindedPlayer().toString());
                        batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().changeScore(1);
                        goal.getBindedPlayer().changeScore(-1);
                        resetGame();
                    }
                }
                else
                {
                    System.out.println("3 Eigen goal, niemand geraakt " + goal.getBindedPlayer().toString());
                    goal.getBindedPlayer().changeScore(-1);
                    resetGame();
                }
            }
        }
    }

    /**
     * returns null
     *
     * @return
     */
    public Side[] getSides()
    {
        return sides;
    }
    
    public int getRound()
    {
        return this.rounds;
    }

    public Puck getPuck()
    {
        return this.puck;
    }

    /**
     * Used to reset the ball and checks each time if there is a winner.
     */
    private void resetGame()
    {
        puck.setPosition(new Point2D(280, 323));
        puck.randomizePuck();
        this.batsHitsPuck.clear();
        this.hitBat = null;
        this.hitSide = null;
        this.lastHitSide = null;
        for (Side side : sides)
        {
            scores.add(side.getBindedPlayer().getInGameScore());
        }
        checkWinner();
    }

    public void movePlayerBat(KeyCode key)
    {
        if (key.equals(KeyCode.LEFT))
        {
            if (sides[1].getGoalX1() < sides[1].getBat().getXpos())
            {
                sides[1].moveBat("1left");
            }
        }
        else if (key.equals(KeyCode.RIGHT))
        {
            if (sides[1].getGoalX2() > sides[1].getBat().getXpos())
            {
                sides[1].moveBat("1right");
            }
        }
    }

    public void moveAIPlayers()
    {
        if (puck.getYpos() < sides[0].getBat().getYpos()
                && sides[0].getGoalY1() + 20 < sides[0].getBat().getYpos())
        {
            sides[0].moveBat("0right");
        }
        else if (puck.getYpos() > sides[0].getBat().getYpos()
                && sides[0].getGoalY2() - 10 > sides[0].getBat().getYpos())
        {
            sides[0].moveBat("0left");
        }

        if (puck.getYpos() > sides[2].getBat().getYpos()
                && sides[2].getGoalY2() - 10 > sides[2].getBat().getYpos())
        {
            sides[2].moveBat("2right");
        }
        else if (puck.getYpos() < sides[2].getBat().getYpos()
                && sides[2].getGoalY1() + 20 < sides[2].getBat().getYpos())
        {
            sides[2].moveBat("2left");
        }
    }

    /**
     * Checks if there is a winner.
     */
    public void checkWinner()
    {
        if (rounds < 10)
        {
            rounds++;
        }
        else
        {
            int highscore = 0;
            int index = 0;
            for (Side side : sides)
            {
                highscore = side.getBindedPlayer().getInGameScore();
                gameResult.add(side);
                gameOver = true;
                index++;
            }
        }
    }
}
