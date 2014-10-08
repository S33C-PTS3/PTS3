/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 * @author Eric
 * RobotPlayer with a set difficulty
 */
public class RobotPlayer extends Player {

    private Difficulty difficulty;
    
    /**
     * Constructor for RobotPlayer with a name and difficulty.
     * @param username
     * @param difficulty 
     */
    public RobotPlayer(String username, Difficulty difficulty)
    {
        super(username);
        //nog niet af
    }
}
