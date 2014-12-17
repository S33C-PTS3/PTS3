/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import observer.BasicPublisher;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 *
 * @author Eric
 */
public class Chat extends UnicastRemoteObject implements IChat, RemotePublisher {

    private List<Message> messages;
    private BasicPublisher publisher;
    /**
     * Creates a new instance of the Chat class
     */
    public Chat() throws RemoteException {
        messages = new ArrayList<>();
        publisher = new BasicPublisher(new String[] { "Lobby", "Game"});
    }

    /**
     * gets the list of messages
     *
     * @return list of messages
     */
    @Override
    public List<Message> getMessages() {
        return this.messages;
    }

    /**
     * adds a message to the list of messages
     *
     * @param sender
     * @param text
     * @return true/false
     */
    @Override
    public boolean addMessage(String sender, String text) {
        boolean b;
        try {
            Message m = new Message(sender, text);
            if (messages.size() > 100) {
                for (int i = 0; i < 99; i++) {
                    messages.set(i, messages.get(i + 1));
                }
                messages.set(99, m);
            }
            messages.add(m);
            publisher.inform(this, "Lobby", null, m);
            publisher.inform(this, "Game", null, m);
            b = true;
        } catch (Exception e) {
            System.out.println(e.toString());
            b = false;
        }
        return b;
    }

    @Override
    public void addListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.addListener(listener, property);
    }

    @Override
    public void removeListener(RemotePropertyListener listener, String property) throws RemoteException {
        publisher.removeListener(listener, property);
    }

    @Override
    public void addListenerO(RemotePropertyListener listener, String property) throws RemoteException {
        addListener(listener, property);
    }

    @Override
    public void removeListenerO(RemotePropertyListener listener, String property) throws RemoteException {
        removeListener(listener, property);
    }
}
