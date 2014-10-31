/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

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

    public HockeyField(Puck puck, Side[] sides)
    {
        this.puck = puck;
        this.sides = sides;
        this.lasthit = null;
        hitSide = null;
        batHitters = new ArrayList<>();
    }

    public void checkColl()
    {
        for (int i = 0; i < sides.length; i++)
        {
            hitSide = sides[i].ballHitsWall(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity());
            hitBat = sides[i].hasCollided(puck);
            goal = sides[i].checkGoalLine(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity(), puck.getRadius());
            if (hitSide != null && lasthit != hitSide.getSideName())
            {
                puck.setVelocityWithoutNormal(hitSide.getVector1(), hitSide.getVector2());
                lasthit = sides[i].getSideName();
                hitSide = null;
            }
            if (hitBat != null && lasthit != hitBat.getBat().getSideName())
            {
                puck.setVelocityWithNormal(hitBat.getBat().getVector(), puck.getPosition());
                if(batHitters.isEmpty())
                {
                    batHitters.add(hitBat);
                }
                else if (!hitBat.equals(batHitters.get(batHitters.size() - 1)))
                {
                    batHitters.add(hitBat);
                }
                lasthit = sides[i].getBat().getSideName();
                hitBat = null;
            }
            if (goal != null)
            {
                System.out.println(goal.getSideName().toString());
                goal.getBindedPlayer().changeScore(-1);

                if (!batHitters.isEmpty())
                {
                    if (goal.equals(batHitters.get(batHitters.size() - 1)))
                    {
                        System.out.println("1 Gescoord door " + batHitters.get(batHitters.size() - 2).getBindedPlayer().toString());
                        System.out.println("1 Gescoord bij " + goal.getBindedPlayer().toString());
                        batHitters.get(batHitters.size() - 1).getBindedPlayer().changeScore(-1);
                        resetGame();

                    }
                    else if (batHitters.size() > 0)
                    {
                        System.out.println("2 Gescoord door " + batHitters.get(batHitters.size() - 1).getBindedPlayer().toString());
                        System.out.println("2 Gescoord bij " + goal.getBindedPlayer().toString());
                        batHitters.get(batHitters.size() - 1).getBindedPlayer().changeScore(1);
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
//            if (goal != null)
//            {
//                goal.getBindedPlayer().changeScore(-1);
//                for (int bh = batHitters.size(); bh <= batHitters.size(); bh--)
//                {
//                    if (!batHitters.isEmpty())
//                    {
//                        if (goal != batHitters.get(bh))
//                        {
//                            batHitters.get(bh).getBindedPlayer().changeScore(1);
//                            System.out.println(batHitters.get(bh).getBindedPlayer().toString());
//                            System.out.println(batHitters.get(bh).getBindedPlayer().getInGameScore());
//                            System.out.println(goal.getBindedPlayer().toString());
//                            System.out.println(goal.getBindedPlayer().getInGameScore());
//                        }
//                    }
//                }
//                goal = null;
//            }
        }
    }

    public List<Side> getSides()
    {
        return null;
    }
    
    private void resetGame()
    {
        puck.setXpos(400);
        puck.setYpos(462);
        this.batHitters.clear();
        this.hitBat = null;
        this.hitSide = null;
    }
}
