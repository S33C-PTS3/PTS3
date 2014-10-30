/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketchprocessing;

import java.util.List;

/**
 *
 * @author Eric
 */
public class HockeyField {
    private Puck puck;
    private Side[] sides;
    SideName lasthit;
    
    public HockeyField(Puck puck, Side[] sides)
    {
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
                        puck.setXvelocity(-1);
                        System.out.println("Begin");
                    } else {
                        lasthit = sides[i].getSideName();
                        puck.setXvelocity(-1);
                        System.out.println(lasthit.toString());
                    }
                } else {
                    if (sides[i].getSideName().equals(SideName.BOTTOM) && !lasthit.equals(SideName.BOTTOM)) {
                        lasthit = SideName.BOTTOM;
                        puck.setXvelocity(1);
                        puck.setYvelocity(-1);
                        System.out.println(lasthit.toString());
                    }
                    if (sides[i].getSideName().equals(SideName.LEFT) && !lasthit.equals(SideName.LEFT)) {
                        lasthit = SideName.LEFT;
                        puck.setXvelocity(-1);
                        System.out.println(lasthit.toString());
                    }
                    if (sides[i].getSideName().equals(SideName.RIGHT) && !lasthit.equals(SideName.RIGHT)) {
                        lasthit = SideName.RIGHT;
                        puck.setXvelocity(-1);
                        System.out.println(lasthit.toString());
                    }
                }
            }
            if(sides[i].hasCollided(puck))
            {
                puck.setXvelocity(-1);
                lasthit = SideName.BAT;
            }
        }
    }
    
    public List<Side> getSides()
    {
        return null;
    }
}
