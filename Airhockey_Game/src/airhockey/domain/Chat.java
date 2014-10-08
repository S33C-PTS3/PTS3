/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey.domain;

import java.util.List;

/**
 *
 * @author Eric
 */
public class Chat {
    
    //waarde van MAXAMOUNTOFCHARS nog veranderen naar de gewenste waarde
    private final int MAXAMOUNTOFCHARS = 255;
    private List<Message> messages;
    
    public Chat()
    {
        
    }
    
    public List<Message> getMessages()
    {
        return null;
    }
    
    public boolean addMessage(String message)
    {
        return false;
    }
    
    
}
