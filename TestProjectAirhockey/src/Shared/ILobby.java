/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import Chat.IChat;
import Chat.Message;
import Game.Spectator;
import Lobby.IGame;
import Lobby.User;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import observer.RemotePublisher;

/**
 *
 * @author Sasa2905
 */
public interface ILobby extends Remote, RemotePublisher{
    
    
    /**
     * Haalt alle User objecten van deze Lobby op.
     * @return een lijst met users die in de lobby zitten
     */
    public List<User> getUsers() throws RemoteException;
    
    /**
     * Haalt alle Game objecten van deze Lobby op.
     * @return een lijst met games die aangemaakt zijn in de lobby
     */
    public List<String[]> getGames() throws RemoteException;
    
    public IGame getGame(int id) throws RemoteException;
    
    /**
     * Update de ranglijst in de lobby
     * @return nieuwe array ranglijst met 20 plaatsen die gesorteerd zijn op scores van hoog naar laag 
     */
    public String[] updateRanking() throws RemoteException;
    
    /**
     * Voegt een game toe aan de lijst met games.
     * @return true als dit succesvol is verlopen, anders false.
     */
    public String[] addGame(String name, IUser creator) throws RemoteException;
    
    /**
     * Voegt een ingelogde user toe aan de lijst met users in de lobby
     * @return true als dit succesvol is verlopen, anders false.
     */
    public boolean addUser(User user) throws RemoteException;
    
    public boolean addUserToGame(int gameId, User user) throws RemoteException;
    
    public Spectator addSpectatorToGame(int gameId, Spectator spectator) throws RemoteException;
    
    public boolean addMessage(String sender, String text) throws RemoteException;
    
    public List<Message> getMessages() throws RemoteException;
    
    public IChat getChat() throws RemoteException;
    
    public void removeGame(int gameID) throws RemoteException;
    
    public void removeUserFromGame(String username, int gameID) throws RemoteException;
    
}
