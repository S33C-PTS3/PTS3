/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 *
 * @author Roy
 */
public interface IChat extends Remote,RemotePublisher{
    
    public List<Message> getMessages() throws RemoteException;
    
    public boolean addMessage(String sender, String text) throws RemoteException;
     
    public void addListenerO(RemotePropertyListener listener, String property) throws RemoteException;

    
    public void removeListenerO(RemotePropertyListener listener, String property) throws RemoteException ;
}
