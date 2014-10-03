/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 *
 * @author Eric
 */
public class Player extends User{

    private int inGameScore;
    
    //geen constructor in het klassendiagram en geen inGameScore in de constructor?
    public Player(String username)
    {
        super(username);
    }

    public void changeScore(int point)
    {

    }
}
