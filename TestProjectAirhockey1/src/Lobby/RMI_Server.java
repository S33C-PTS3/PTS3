/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lobby;

import Game.ActiveGame;
import Game.HockeyField;
import Game.Mode;
import Security.FTPManager;
import Shared.IActiveGame;
import Shared.ILobby;
import java.beans.PropertyChangeEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 *
 * @author rens
 */
public class RMI_Server implements RemotePropertyListener{

    // Set port number
    private static final int portNumber = 1099;
    FTPManager ftp = new FTPManager();
    // Set binding name for student administration
    private static final String BINDINGNAMELOBBY = "Lobby";
    private static final String BINDINGNAMEGAME = "Game";

    // References to registry and student administration
    private Registry registry = null;
    private ILobby lobby = null;
    private AnimationTimer timer;
    private IActiveGame game;
    private HockeyField hockeyField;

    // Constructor
    public RMI_Server() {

        System.setProperty("java.rmi.server.hostname", "192.168.153.1");
        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        // Create lobby
        try {
            lobby = new Lobby();
            System.out.println("Server: Lobby created");
        } catch (Exception ex) {
            System.out.println("Server: Cannot create Lobby");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            lobby = null;
        }

        // Create game
        try {
            game = new ActiveGame("Meny", new User("Sasa2079"));
            RemotePublisher publisher = (RemotePublisher)game.getHockeyField();
            publisher.addListener(this, "game");
            System.out.println("Server: Game created");
        } catch (Exception ex) {
            System.out.println("Server: Cannot create Game");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            game = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        System.setProperty("java.rmi.server.hostname", "192.168.153.1");
        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        //Bind game using registry
        try {
            registry.rebind(BINDINGNAMEGAME, game);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind game");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
        // Bind lobby using registry
        try {
            registry.rebind(BINDINGNAMELOBBY, lobby);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind lobby");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
        try {
            hockeyField = (HockeyField) game.getHockeyField();
        } catch (RemoteException ex) {
            Logger.getLogger(RMI_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        hockeyField.init(Mode.MULTI);

    }

    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }
    
    private void saveLocalServerIP() {
        String ip = "";

        //Get ip
        try {
            InetAddress localhost = InetAddress.getLocalHost();

            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                for (InetAddress allMyIp : allMyIps) {
                    if (allMyIp.toString().contains("145")) {
                        int slashIndex = allMyIp.toString().indexOf("/");
                        ip = allMyIp.toString().substring(slashIndex + 1);
                        System.out.println("Server IP: " + ip);
                    }
                }
            }

            if (ip.equals("")) {
                System.err.println("External ip not found. Running on localhost.");
                ip = "localhost";
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            ftp.writeIP(ip);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Welcome message
        System.out.println("SERVER USING REGISTRY");

        // Print IP addresses and network interfaces
        printIPAddresses();

        // Create server
        RMI_Server server = new RMI_Server();
    }

    public void serverLoop() {
        System.out.println("GOTIMER");
        hockeyField.getPuck().move();
        hockeyField.checkColl();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        serverLoop();
    }
}
