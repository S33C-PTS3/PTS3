/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.io.Serializable;

/**
 *
 * @author Roy
 * Interface IPlayer
 */
public interface IPlayer extends Serializable{

    public void setInGameScore(int newInGameScore);

    public int getInGameScore();

    public void changeScore(int point);
    
}
