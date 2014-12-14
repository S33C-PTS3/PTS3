import Chat.Chat;
import Chat.Message;
import Lobby.User;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rens & Eric
 */
public class ChatTest {
    
    @Test
    public void testChatConstructor()
    {
        /**
         * Creates a new instance of the Chat class
         */
        Chat chat = null;
        try {
            chat = new Chat();
        } catch (RemoteException ex) {
            Logger.getLogger(ChatTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(chat.getMessages());
    }

    @Test
    public void testAddMessage()
    {
        /**
         * adds a message to the list of messages
         *
         * @param message
         * @return true/false
         */

        Message message = new Message("Hank", "Hi");
        Chat chat = null;
        try {
            chat = new Chat();
        } catch (RemoteException ex) {
            Logger.getLogger(ChatTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        chat.addMessage(message.getSender(), message.getText());
        Assert.assertEquals("Message is not succesfully added to the list", 1, chat.getMessages().size());

        /**
         * Test if a exception occurs when the message parameter is null
         */
        try
        {
            chat.addMessage(message.getSender(), message.getText());
            fail("Message cannot be null");
        }
        catch (IllegalArgumentException ex)
        {
            Assert.assertTrue(true);
        }
    }
}
