import airhockey.domain.Message;
import junit.framework.Assert;
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
public class MessageTest {

    @Test
    public void TestMessageConstructor() {
        /**
         * Creates a new instance of the message class
         *
         * @param sender
         * @param text
         */
        Message message = new Message("Jan", "Hello, this is a test message.");
        Assert.assertEquals("Sender is not set correctfully", "Jan", message.getSender());
        Assert.assertEquals("Text is not set correctfully", "Hello, this is a test message", message.getText());

        /**
         * Test if an exception occurs if the sender of an message is null
         */
        try {
            Message message1 = new Message(null, "This is a testmessage");
            fail("Sender cannot be null");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(true);
        }
        /**
         * Test if an exception occurs if the text of an message is null;
         */
        try {
            Message message2 = new Message("Hank", null);
            fail("Text cannot be null");
        } catch (IllegalArgumentException ex) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testMaxCharRestriction() {

        /**
         * Test if the amount of characters of the text of a message cannot be
         * higher than the set maxChar value.
         */
        try {
            Message messageMaxChars = new Message("Pete", "qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqazwsxedcrfvtgbyhnujmikolpwijeoirjdboijfxobinsoifnboisdnfoinveronboinzoifnbononroinvonvoznovnoieno");
            Assert.assertTrue("Amount of text is too long", messageMaxChars.getText().length() < messageMaxChars.getMaxChars());
        } catch (IllegalArgumentException ex) {
            fail("amount of characters is too long.");
        }
    }
}
