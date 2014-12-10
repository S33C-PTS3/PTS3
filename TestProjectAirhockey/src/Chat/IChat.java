/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Roy
 */
public interface IChat extends Remote{
    
    public List<Message> getMessages() throws RemoteException;
    
     public boolean addMessage(String sender, String text) throws RemoteException;
}
