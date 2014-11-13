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
import javafx.geometry.Point2D;

/**
 *
 * @author Eric
 * The HockeyField represents the board where the game is played on
 */
public class HockeyField {

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
     * @param puck
     * @param sides
     */
    public HockeyField(Puck puck, Side[] sides) {
        this.puck = puck;
        this.sides = sides;
        this.lastHitSide = null;
        hitSide = null;
        batsHitsPuck = new ArrayList<>();
        rounds = 1;
        scores = new ArrayList<>();
        gameResult = new ArrayList();
    }

    /**
     * Method used to display the HockeyField
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
     * Method to check if the puck collided with a side, a goal or a bat and changes its direction
     */
    public void checkColl() {
        for (int i = 0; i < sides.length; i++) {
            hitSide = sides[i].ballHitsWall(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity());
            hitBat = sides[i].hasCollided(puck);
            goal = sides[i].checkGoalLine(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity(), puck.getRadius());
            if (hitSide != null && lastHitSide != hitSide.getSideName()) {
                puck.setVelocityWithoutNormal(hitSide.getVector1(), hitSide.getVector2());
                lastHitSide = sides[i].getSideName();
                hitSide = null;
            }
            if (hitBat != null && lastHitSide != hitBat.getBat().getSideName()) {
                puck.setVelocityWithNormal(hitBat.getBat().getVector(), puck.getPosition());
                if (batsHitsPuck.isEmpty()) {
                    batsHitsPuck.add(hitBat);
                } else if (!hitBat.equals(batsHitsPuck.get(batsHitsPuck.size() - 1))) {
                    batsHitsPuck.add(hitBat);
                }
                lastHitSide = sides[i].getBat().getSideName();
                hitBat = null;
            }
            if (goal != null) {
                System.out.println(goal.getSideName().toString());
                //goal.getBindedPlayer().changeScore(-1);

                if (!batsHitsPuck.isEmpty()) {

                    if (goal.equals(batsHitsPuck.get(batsHitsPuck.size() - 1))) {
                        if (batsHitsPuck.size() <= 1) {
                            System.out.println("eigen goal gemaakt en aangeraakt door" + batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().toString());
                            batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().changeScore(-1);
                        } else {
                            System.out.println("1 Gescoord door " + batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().toString());
                            System.out.println("1 Gescoord bij " + goal.getBindedPlayer().toString());
                            batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().changeScore(-1);
                        }
                        resetGame();

                    } else if (batsHitsPuck.size() > 0) {
                        System.out.println("2 Gescoord door " + batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().toString());
                        System.out.println("2 Gescoord bij " + goal.getBindedPlayer().toString());
                        batsHitsPuck.get(batsHitsPuck.size() - 1).getBindedPlayer().changeScore(1);
                        goal.getBindedPlayer().changeScore(-1);
                        resetGame();
                    }
                } else {
                    System.out.println("3 Eigen goal, niemand geraakt " + goal.getBindedPlayer().toString());
                    goal.getBindedPlayer().changeScore(-1);
                    resetGame();
                }
            }
        }
    }
    
    /**
     * returns null
     * @return 
     */
    public List<Side> getSides() {
        return null;
    }

    /**
     * Used to reset the ball and checks each time if there is a winner.
     */
    private void resetGame() {
        puck.setPosition(new Point2D(280, 323));
        puck.randomizePuck();
        this.batsHitsPuck.clear();
        this.hitBat = null;
        this.hitSide = null;
        this.lastHitSide = null;
        for (Side side : sides) {
            scores.add(side.getBindedPlayer().getInGameScore());
        }
        checkWinner();
    }
    
    /**
     * Checks if there is a winner.
     */
    public void checkWinner() {

        if (rounds < 10) {
            rounds++;
        } else {
            int highscore = 0;
            int index = 0;
            for (Side side : sides) {
                highscore = side.getBindedPlayer().getInGameScore();
                gameResult.add(side);
                gameOver = true;
                index++;
            }

        }
    }
}
