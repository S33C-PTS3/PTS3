/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Security.FTPManager;
import Shared.IActiveGame;
import Shared.IHockeyField;
import Shared.ILobby;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javafx.geometry.Point2D;
import observer.BasicPublisher;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 *
 * @author Eric
 */
public class GameRMI extends UnicastRemoteObject implements RemotePropertyListener, RemotePublisher{

    private Registry registry;
    private IActiveGame game;
    private Object gameObject;
    private IHockeyField hockeyField;
    private final String BINDINGNAME = "Game";
    private Point2D puckPosition;
    private BasicPublisher bpublisher;
    private final FTPManager ftp = new FTPManager();

    public GameRMI() throws RemoteException
    {
        bpublisher = new BasicPublisher(new String[] { "Server" });
        System.out.println("Attempting to locate remote registry");
        //Locate registry
        try
        {
            registry = LocateRegistry.getRegistry(ftp.getIP(), 1099);
            System.out.println("Registry located");
        }
        catch (RemoteException ex)
        {
            System.out.println("Cannot locate registry");
            System.out.println("RemoteException: " + ex.getMessage());
            registry = null;
        }

        //Bind game
        if (registry != null)
        {
            try
            {
                game = (IActiveGame)registry.lookup(BINDINGNAME);
                hockeyField = (IHockeyField)game.getHockeyField();
            }
            catch (RemoteException ex)
            {
                ex.printStackTrace();
                System.out.println("Cannot bind Game");
                System.out.println("RemoteException: " + ex.getMessage());
                game = null;
            }
            catch (NotBoundException ex)
            {
                System.out.println("Cannot bind Game");
                System.out.println("NotBoundException: " + ex.getMessage());
                game = null;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        if(evt.getPropertyName().equals("puck"))
        {    
            puckPosition = new Point2D(hockeyField.getPuckPosition()[0], hockeyField.getPuckPosition()[1]);
        }
        else if(evt.getPropertyName().equals("bat"))
        {
            System.out.println("bat");
        }
    }

    public Point2D getPuckPosition()
    {
        return puckPosition;
    }
    
    public IHockeyField getHockeyField()
    {
        return this.hockeyField;
    }
    
    public IActiveGame getActiveGame()
    {
        return this.game;
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        bpublisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        bpublisher.addListener(listener, property);
    }
}
