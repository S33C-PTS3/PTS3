/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey.domain;

/**
 *
 * @author Eric
 */
public class Message {
    private String sender;
    private String text;
    private final int maxChars = 255;
    /**
     * Creates a new instance of the message class
     * @param sender
     * @param text 
     */
    public Message(String sender, String text)
    {
        
    }
    
    /**
     * gets the username of the  sender of the message
     * @return the username of the sender
     */
    public String getSender()
    {
        return sender;
    }
    
    /**
     * gets the text of the message
     * @return the text of the message
     */
    public String getText()
    {
        return text;
    }
    
    public int getMaxChars()
    {
        return maxChars;
    }
}
