/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Shared.ILobby;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rens
 */
public class LobbyRMI extends UnicastRemoteObject {

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
            //System.setProperty("java.rmi.server.hostname", "145.93.162.240");
            registry = LocateRegistry.getRegistry("145.93.163.171", 1099);
            System.out.println("Registry located");
        }
        catch (RemoteException ex)
        {
            System.out.println("Cannot locate registry");
            System.out.println("RemoteException: " + ex.getMessage());
            registry = null;
        }

        //Bind effectenbeurs
        if (registry != null)
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

    }

    public ILobby getLobby()
    {
        return this.lobby;
    }
}
