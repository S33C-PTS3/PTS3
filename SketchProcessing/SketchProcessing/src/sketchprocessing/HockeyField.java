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
    
    public HockeyField(Puck puck, Side[] sides)
    {
        this.puck = puck;
        this.sides = sides;
    }
    
//    public void checkOfGoal()
//    {
//        for(Side s : sides)
//        {
//            if(s.goal(puck.getX(), puck.getY()))
//            {
//                for(Side si : sides)
//                {
//                    if(si.equals(puck.lastHit))
//                    {
//                        si.score();
//                    }
//                }
//            }
//        }
//    }
    
    public void checkColl()
    {
        SideName sidename;
        for(int i = 0; i < sides.length; i++)
        {
            if(sides[i].goal(puck.getXpos(), puck.getYpos(), puck.getYvelocity()))
            {
               
                sidename = sides[i].getSideName();
                switch(sidename)
                {
                    case LEFT:
                        puck.setXvelocity(puck.getXvelocity() * -1);
                        puck.setYvelocity(puck.getYvelocity() * -1);
                        break;
                    case BOTTOM:
                        puck.setYvelocity(puck.getYvelocity() * -1);
                        break;
                    case RIGHT:
                        puck.setXvelocity(puck.getXvelocity() * -1);
                        break;
                }
            }
        }
    }
    
    public List<Side> getSides()
    {
        return null;
    }
}
