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
public interface IPlayer {

    public void setInGameScore(int newInGameScore);

    public int getInGameScore();

    public void changeScore(int point);
    
}
