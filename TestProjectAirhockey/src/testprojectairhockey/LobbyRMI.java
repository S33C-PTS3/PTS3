/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import observer.*;
import Shared.ILobby;
import java.beans.PropertyChangeEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.RemotePublisher;

/**
 *
 * @author rens
 */
public class LobbyRMI extends UnicastRemoteObject implements RemotePropertyListener
{
    private ILobby lobby;
    private Registry registry;
    private final String bindingname = "Lobby";

    /**
     *
     * @throws java.rmi.RemoteException
     */
    public LobbyRMI() throws RemoteException
    {

        System.out.println("Attempting to locate remote registry");
        //Locate registry
        try
        {
            registry = LocateRegistry.getRegistry("145.93.67.6", 1099);
            System.out.println("Registry located");
        }
        catch (RemoteException ex)
        {
            System.out.println("Cannot locate registry");
            System.out.println("RemoteException: " + ex.getMessage());
            registry = null;
        }

        //Bind effectenbeurs
        if (registry!= null)
        {
            try
            {
                lobby = (ILobby) registry.lookup(bindingname);
            }
            catch (RemoteException ex)
            {
                System.out.println("Cannot bind Lobby");
                System.out.println("RemoteException: " + ex.getMessage());
                lobby = null;
            }
            catch (NotBoundException ex)
            {
                System.out.println("Cannot bind Lobby");
                System.out.println("NotBoundException: " + ex.getMessage());
                lobby = null;
            }
        }

        RemotePublisher publisher = null;

        try
        {
            publisher = (RemotePublisher) Naming.lookup("rmi://145.93.67.6:1099/EffectenBeurs");
        }
        catch (MalformedURLException | NotBoundException ex)
        {
            Logger.getLogger(LobbyRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        publisher.addListener(this,"Lobby");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ILobby getLobby()
    {
        return this.lobby;
    }
}
