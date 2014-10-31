/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.util.List;
import processing.core.PVector;

/**
 *
 * @author Eric
 */
public class HockeyField {

    private Puck puck;
    private Side[] sides;
    SideName lasthit;
    Side hitSide;
    Side hitBat;

    public HockeyField(Puck puck, Side[] sides) {
        this.puck = puck;
        this.sides = sides;
        this.lasthit = null;
        hitSide = null;
    }

    public void checkColl() {
        for (int i = 0; i < sides.length; i++) {
            hitSide = sides[i].ballHitsWall(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity());
            hitBat = sides[i].hasCollided(puck);
            if (hitSide != null) {
                puck.setVelocityWithoutNormal(hitSide.getVector1(), hitSide.getVector2());

                hitSide = null;
            }
            if (hitBat != null) {
                puck.setVelocityWithNormal(hitBat.getBat().getVector(), puck.getPosition());
                hitBat = null;
            }
        }
    }

    public List<Side> getSides() {
        return null;
    }
}
