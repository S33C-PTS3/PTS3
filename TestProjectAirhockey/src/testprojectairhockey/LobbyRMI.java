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
    private RemotePublisher publisher = null;

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
            //System.setProperty("java.rmi.server.hostname", "145.93.162.240");
            registry = LocateRegistry.getRegistry("145.93.162.240", 1099);
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

        try
        {
            publisher = (RemotePublisher) Naming.lookup("rmi://145.93.162.240/Lobby");
        }
        catch (MalformedURLException | NotBoundException ex)
        {
            Logger.getLogger(LobbyRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
        publisher.addListener(this, "lobby");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        System.out.println("IT WORKS");
        getGameCount();
    }
    
    public void getGameCount()
    {
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
        try {
            System.out.println(lobby.getGames().size());
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public RemotePublisher getPublisher()
    {
        return publisher;
    }
    public ILobby getLobby()
    {
        return this.lobby;
    }
}
