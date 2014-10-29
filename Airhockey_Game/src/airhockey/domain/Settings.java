/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.io.Serializable;

/**
 *
 * @author rens
 */
public class Settings implements Serializable {

    private Difficulty diffRobot1;
    private Difficulty diffRobot2;

    public Settings(Difficulty diffrobot1, Difficulty diffrobot2)
    {
        this.diffRobot1 = diffrobot1;
        this.diffRobot2 = diffrobot2;
    }

    public Difficulty getDifficulty1()
    {
        return diffRobot1;
    }

    public Difficulty getDifficulty2()
    {
        return diffRobot2;
    }

    public void setDifficulty1(Difficulty newDifficulty1)
    {
        this.diffRobot1 = newDifficulty1;
    }

    public void setDifficulty2(Difficulty newDifficulty2)
    {
        this.diffRobot2 = newDifficulty2;
    }

}
