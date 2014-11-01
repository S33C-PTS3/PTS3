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
    private SideName lasthit;
    private Side hitSide;
    private Side hitBat;
    private Side goal;
    private List<Side> batHitters;
    private int rounds;
    private ArrayList<Integer> scores;
    private PApplet parent;
    private ArrayList uitslag = null;
    private boolean gameOver = false;

    public HockeyField(Puck puck, Side[] sides, PApplet parent) {
        this.puck = puck;
        this.sides = sides;
        this.lasthit = null;
        hitSide = null;
        batHitters = new ArrayList<>();
        rounds = 1;
        scores = new ArrayList<>();
        uitslag = new ArrayList();
        this.parent = parent;
    }

    public boolean display() {

        if (!gameOver) {
            puck.move();
            checkColl();
            for (int i = 0; i < 3; i++) {
                sides[i].display(i * 10);
                sides[i].makeGoal(i);
            }
            puck.display();
            return true;
        } else {
            parent.textSize(30);
            boolean isGelijkspel = false;
            for (int i = 0; i < 3; i++) {
                if (uitslag.get(i) == null) {
                    isGelijkspel = true;
                }
            }
            if (isGelijkspel) {
                parent.text("Gelijkspel", 150, 200);
            } else {
                int highscore = 0;
                Collections.sort(uitslag, new Comparator<Side>() {
                    @Override
                    public int compare(Side p1, Side p2) {
                        return p2.getBindedPlayer().getInGameScore() - p1.getBindedPlayer().getInGameScore(); // Ascending
                    }
                });
                for (int i = 0; i < 3; i++) {
                    Side tempSide = (Side)uitslag.get(i);
                    parent.text(i+1 + ". " + tempSide.getBindedPlayer().toString() + ": " + tempSide.getBindedPlayer().getInGameScore(), 180, 200 + i * 50);
                }
            }
        }
        return false;
    }

    public void checkColl() {
        for (int i = 0; i < sides.length; i++) {
            hitSide = sides[i].ballHitsWall(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity());
            hitBat = sides[i].hasCollided(puck);
            goal = sides[i].checkGoalLine(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity(), puck.getRadius());
            if (hitSide != null && lasthit != hitSide.getSideName()) {
                puck.setVelocityWithoutNormal(hitSide.getVector1(), hitSide.getVector2());
                lasthit = sides[i].getSideName();
                hitSide = null;
            }
            if (hitBat != null && lasthit != hitBat.getBat().getSideName()) {
                puck.setVelocityWithNormal(hitBat.getBat().getVector(), puck.getPosition());
                if (batHitters.isEmpty()) {
                    batHitters.add(hitBat);
                } else if (!hitBat.equals(batHitters.get(batHitters.size() - 1))) {
                    batHitters.add(hitBat);
                }
                lasthit = sides[i].getBat().getSideName();
                hitBat = null;
            }
            if (goal != null) {
                System.out.println(goal.getSideName().toString());
                //goal.getBindedPlayer().changeScore(-1);

                if (!batHitters.isEmpty()) {

                    if (goal.equals(batHitters.get(batHitters.size() - 1))) {
                        if (batHitters.size() <= 1) {
                            System.out.println("eigen goal gemaakt en aangeraakt door" + batHitters.get(batHitters.size() - 1).getBindedPlayer().toString());
                            batHitters.get(batHitters.size() - 1).getBindedPlayer().changeScore(-1);
                        } else {
                            System.out.println("1 Gescoord door " + batHitters.get(batHitters.size() - 1).getBindedPlayer().toString());
                            System.out.println("1 Gescoord bij " + goal.getBindedPlayer().toString());
                            batHitters.get(batHitters.size() - 1).getBindedPlayer().changeScore(-1);
                        }
                        resetGame();

                    } else if (batHitters.size() > 0) {
                        System.out.println("2 Gescoord door " + batHitters.get(batHitters.size() - 1).getBindedPlayer().toString());
                        System.out.println("2 Gescoord bij " + goal.getBindedPlayer().toString());
                        batHitters.get(batHitters.size() - 1).getBindedPlayer().changeScore(1);
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
        parent.text("Round : " + rounds, 300, 200);
    }

    public List<Side> getSides() {
        return null;
    }

    private void resetGame() {
        puck.setXpos(280);
        puck.setYpos(323);
        puck.randomizePuck();
        this.batHitters.clear();
        this.hitBat = null;
        this.hitSide = null;
        this.lasthit = null;
        for (Side side : sides) {
            scores.add(side.getBindedPlayer().getInGameScore());
        }
        checkWinner();
    }

    public void checkWinner() {

        if (rounds < 2) {
            rounds++;
        } else {
            int highscore = 0;
            int index = 0;
            for (Side side : sides) {
                //if (side.getBindedPlayer().getInGameScore() > highscore) {
                if (side.getBindedPlayer().getInGameScore() == highscore) {
                    uitslag.add(null);
                } else {
                    highscore = side.getBindedPlayer().getInGameScore();
                    uitslag.add(side);
                }
                gameOver = true;
                index++;
            }

        }
    }
}
