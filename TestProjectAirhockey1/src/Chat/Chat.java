/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eric
 */
public class Chat {

    private List<Message> messages;

    /**
     * Creates a new instance of the Chat class
     */
    public Chat()
    {
        messages = new ArrayList<>();
    }

    /**
     * gets the list of messages
     *
     * @return list of messages
     */
    public List<Message> getMessages()
    {
        return this.messages;
    }

    /**
     * adds a message to the list of messages
     *
     * @param message
     * @return true/false
     */
    public boolean addMessage(Message message)
    {
        if (message != null)
        {
            messages.add(message);
        }
        else
        {
            throw  new IllegalArgumentException("Message can not be null");
        }
        return messages.contains(message);
    }

}
