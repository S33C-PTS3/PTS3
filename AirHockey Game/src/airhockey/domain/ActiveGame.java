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
public class ActiveGame extends Game{

    private HockeyField hockeyField;
    private Chat chat;
    private int round;
    
//    //gegenereerde constructor
//    public ActiveGame(HockeyField hockeyField, Chat chat, String name)
//    {
//        //nog invullen
//        super(name);
//    }
    
    public int getRound()
    {
        return round;
    }
    
    public void nextRound()
    {
        
    }
}
