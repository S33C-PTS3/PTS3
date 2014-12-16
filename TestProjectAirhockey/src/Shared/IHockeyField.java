/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import Game.Mode;
import Game.Side;
import java.rmi.Remote;
import java.rmi.RemoteException;
import javafx.geometry.Point2D;

/**
 *
 * @author Eric
 */
public interface IHockeyField extends Remote{
    public double[] getPuckPosition() throws RemoteException;
    
    public Side[] getSides() throws RemoteException;
    
    public void setPuckPosition(double x, double y) throws RemoteException;
    
    public int getRound() throws RemoteException;
    
    public double getDiameter() throws RemoteException;
    
    public void setPlayerBatPosition(String direction, String userName) throws RemoteException;
    
    public void init(Mode mode) throws RemoteException;
    
    public Mode getMode() throws RemoteException;
}
