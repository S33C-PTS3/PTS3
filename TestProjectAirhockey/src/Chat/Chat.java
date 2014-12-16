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

/**
 *
 * @author Eric
 */
public class Chat extends UnicastRemoteObject implements IChat {

    private List<Message> messages;

    /**
     * Creates a new instance of the Chat class
     */
    public Chat() throws RemoteException {
        messages = new ArrayList<>();
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
            b = true;
        } catch (Exception e) {
            System.out.println(e.toString());
            b = false;
        }
        return b;
    }
}
