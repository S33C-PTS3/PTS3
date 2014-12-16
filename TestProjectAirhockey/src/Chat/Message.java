/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import java.io.Serializable;

/**
 *
 * @author Eric
 */
public class Message implements Serializable {

    private String sender;
    private String text;
    private final int maxChars = 255;

    /**
     * Creates a new instance of the message class
     *
     * @param sender
     * @param text
     */
    public Message(String sender, String text) {
        if (sender == null) {
            throw new IllegalArgumentException("Sender cannot be null");
        }
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        if (text.length() > maxChars) {
            throw new IllegalArgumentException("Text to long");
        }
        if (text.trim().equals("")) {
            throw new IllegalArgumentException("Text is empty");
        }
        this.sender = sender;
        this.text = text;
    }

    /**
     * gets the username of the sender of the message
     *
     * @return the username of the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * gets the text of the message
     *
     * @return the text of the message
     */
    public String getText() {
        return text;
    }

    public int getMaxChars() {
        return maxChars;
    }

    /**
     * Returns a string representing the message. First the sender and followed
     * by the text. Example: "Eric: Hey Meny"
     *
     * @return
     */
    @Override
    public String toString() {
        String s = getSender() + ": " + getText();
        return s;
    }

}
