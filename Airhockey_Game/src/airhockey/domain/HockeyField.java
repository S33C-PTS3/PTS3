/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.util.List;

/**
 *
 * @author Eric
 */
public class HockeyField {
    private Puck puck;
    private List<Side> sides;
    
    public HockeyField(Puck puck, List<Side> sides)
    {
        
    }
    
    public void checkOfGoal()
    {
        for(Side s : sides)
        {
            if(s.goal(puck.getX(), puck.getY()))
            {
                for(Side si : sides)
                {
                    if(si.equals(puck.lastHit))
                    {
                        si.score();
                    }
                }
            }
        }
    }
    
    public List<Side> getSides()
    {
        return null;
    }
}
