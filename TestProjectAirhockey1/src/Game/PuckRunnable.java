/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sasa2905
 */
public class PuckRunnable implements Runnable {

    private HockeyField hockeyField;
    private int index = 0;
    public PuckRunnable(HockeyField hockeyField) {
        this.hockeyField = hockeyField;
    }
    @Override
    public void run() {
        while(index < 10000 && !Thread.currentThread().interrupted())
        {
            hockeyField.getPuck().move();
            try {
                hockeyField.getPublisher().inform(hockeyField, "puck", null, hockeyField.getPuckPosition());
            } catch (RemoteException ex) {
                Logger.getLogger(PuckRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Logger.getLogger(PuckRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            hockeyField.checkColl();
            index++;
        }
        
    }
    
}
