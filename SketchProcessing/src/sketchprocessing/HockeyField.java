/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchprocessing;

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

    public HockeyField(Puck puck, Side[] sides) {
        this.puck = puck;
        this.sides = sides;
        this.lasthit = null;
    }

    public void checkColl() {
        for (int i = 0; i < sides.length; i++) {
            if (sides[i].goal(puck.getXpos(), puck.getYpos(), puck.getXvelocity(), puck.getYvelocity())) {
                if (lasthit == null) {
                    if (sides[i].getSideName().equals(SideName.BOTTOM)) {
                        lasthit = SideName.BOTTOM;
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        System.out.println("Begin");
                    } else {
                        lasthit = sides[i].getSideName();
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        System.out.println(lasthit.toString());
                    }
                } else {
                    if (sides[i].getSideName().equals(SideName.BOTTOM) && !lasthit.equals(SideName.BOTTOM)) {
                        lasthit = SideName.BOTTOM;
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        System.out.println(lasthit.toString());
                    }
                    if (sides[i].getSideName().equals(SideName.LEFT) && !lasthit.equals(SideName.LEFT)) {
                        lasthit = SideName.LEFT;
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        System.out.println(lasthit.toString());
                    }
                    if (sides[i].getSideName().equals(SideName.RIGHT) && !lasthit.equals(SideName.RIGHT)) {
                        lasthit = SideName.RIGHT;
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        System.out.println(lasthit.toString());
                    }
                }
            }
            if (sides[i].hasCollided(puck)) {

                if (lasthit == null) {
                    if (sides[i].getSideName().equals(SideName.BATBOTTOM) && !lasthit.equals(SideName.BATBOTTOM)) {
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        lasthit = SideName.BATBOTTOM;
                    } else {
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        lasthit = sides[i].getSideName();
                    }
                } else {
                    if (sides[i].getBat().getSideName().equals(SideName.BATBOTTOM) && !lasthit.equals(SideName.BATBOTTOM)) {
                        puck.setXvelocity(new PVector((float)sides[i].getBat().getXpos(),(float)sides[i].getBat().getYpos()),new PVector((float)sides[i].getBat().getXpos()* 2,(float)sides[i].getBat().getYpos() * 2));
                        lasthit = SideName.BATBOTTOM;
                    } else if (sides[i].getBat().getSideName().equals(SideName.BATLEFT) && !lasthit.equals(SideName.BATLEFT)) {
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        lasthit = SideName.BATLEFT;
                    } else if (sides[i].getBat().getSideName().equals(SideName.BATRIGHT) && !lasthit.equals(SideName.BATRIGHT)) {
                        puck.setXvelocity(new PVector((float)sides[i].getLineX1(),(float)sides[i].getLineY1()),new PVector((float)sides[i].getLineX2(),(float)sides[i].getLineY2()));
                        lasthit = SideName.BATRIGHT;
                    }
                }
            }
        }
    }

    public List<Side> getSides() {
        return null;
    }
}
