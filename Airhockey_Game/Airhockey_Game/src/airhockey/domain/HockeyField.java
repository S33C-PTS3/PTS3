/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author Eric
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
    private PApplet parentApplet;
    private ArrayList gameResult = null;
    private boolean gameOver = false;

    public HockeyField(Puck puck, Side[] sides, PApplet parent) {
        this.puck = puck;
        this.sides = sides;
        this.lastHitSide = null;
        hitSide = null;
        batsHitsPuck = new ArrayList<>();
        rounds = 1;
        scores = new ArrayList<>();
        gameResult = new ArrayList();
        this.parentApplet = parent;
    }

    public boolean display() {
        checkColl();
        if (!gameOver) {
            puck.move();
            for (int i = 0; i < 3; i++) {
                sides[i].display(i * 20);
                sides[i].makeGoal(i);
            }
            puck.display();
            return true;
        } else 
        {
            parentApplet.textSize(30);
            int highscore = 0;
            Collections.sort(gameResult, new Comparator<Side>() {
                @Override
                public int compare(Side p1, Side p2) {
                    return p2.getBindedPlayer().getInGameScore() - p1.getBindedPlayer().getInGameScore(); // Ascending
                }
            });
            for (int i = 0; i < 3; i++) {
                Side tempSide = (Side)gameResult.get(i);

                parentApplet.text(i+1 + ". " + tempSide.getBindedPlayer().toString() + ": " + tempSide.getBindedPlayer().getInGameScore(), 200, 200 + i * 50);
            }
        }
        return false;
    }

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
        parentApplet.textSize(22);
        parentApplet.fill(0);
        parentApplet.text("Round : " + rounds, 50, 70);
    }

    public List<Side> getSides() {
        return null;
    }

    private void resetGame() {
        puck.setXpos(280);
        puck.setYpos(323);
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
