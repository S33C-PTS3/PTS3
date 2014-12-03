/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shared;

import Lobby.Game;
import Lobby.User;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author Sasa2905
 */
public interface ILobby extends Remote{
    
    
    /**
     * Haalt alle User objecten van deze Lobby op.
     * @return een lijst met users die in de lobby zitten
     */
    public List<User> getUsers();
    
    /**
     * Haalt alle Game objecten van deze Lobby op.
     * @return een lijst met games die aangemaakt zijn in de lobby
     */
    public List<Game> getGames();
    
    /**
     * Update de ranglijst in de lobby
     * @return nieuwe array ranglijst met 20 plaatsen die gesorteerd zijn op scores van hoog naar laag 
     */
    public String[] updateRanking();
    
    /**
     * Voegt een game toe aan de lijst met games.
     * @return true als dit succesvol is verlopen, anders false.
     */
    public boolean addGame(Game game);
    
    /**
     * Voegt een ingelogde user toe aan de lijst met users in de lobby
     * @return true als dit succesvol is verlopen, anders false.
     */
    public boolean addUser(User user);
    
}
