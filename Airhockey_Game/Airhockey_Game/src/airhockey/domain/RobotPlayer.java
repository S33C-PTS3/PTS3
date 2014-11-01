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
public class RobotPlayer implements IPlayer {
    private int inGameScore;
    private Difficulty difficulty;
    private String naam;
    
    public RobotPlayer(String naam)
    {
        this.inGameScore = 20;
        this.naam = naam;
    }
    
    @Override
    public void setInGameScore(int newInGameScore){
        this.inGameScore = newInGameScore;
    }
    
    @Override
    public int getInGameScore(){
        return this.inGameScore;
    }
    
    @Override
    public void changeScore(int point){
        this.inGameScore += point;
    }
    
    public void setDifficulty(Difficulty diff)
    {
        this.difficulty = diff;
    }
    
    @Override
    public String toString()
    {
        return this.naam;
    }
    
    
}
