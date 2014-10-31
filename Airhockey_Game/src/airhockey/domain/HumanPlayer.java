/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 *
 * @author Roy
 */
public class HumanPlayer implements IPlayer {

    private int inGameScore;
    private String naam;

    public HumanPlayer() {
        this.inGameScore = 20;
        naam = "Eric";
    }

    @Override
    public void setInGameScore(int newInGameScore) {
        this.inGameScore = newInGameScore;
    }

    @Override
    public int getInGameScore() {
        return this.inGameScore;
    }

    @Override
    public void changeScore(int point) {
        this.inGameScore += point;
    }
    
    @Override
    public String toString()
    {
        return naam;
    }
}


