/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.domain;

import fontys.time.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Joep Kerste
 */
public class Appointment {
    private String subject;
    private IPeriod period;
    private ArrayList<Contact> invitees;
    
    //add javadoc
    public Appointment(String subject, IPeriod period)
    {
        
    
    }
    //add javadoc
    public String getSubject()
    {
        return "getSubject() Not yet implemented.";
    }
    
    //add javadoc
    public IPeriod getPeriod()
    {
        return null;
    }
    
    //add javadoc
    public Iterator<Contact> invitees()
    {
        return null;
    }
    
    //add javadoc
    public boolean addContact(Contact c)
    {
        return false;
    }
    
    //add javadoc
    public void removeContact(Contact c)
    {
        
    }
}
