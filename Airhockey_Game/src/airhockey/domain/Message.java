/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

/**
 *
 * @author Eric
 */
public class Message {
    private String sender;
    private String text;
    
    public Message(String sender, String text)
    {
        
    }
    
    public String getSender()
    {
        return sender;
    }
    
    public String getText()
    {
        return text;
    }
}