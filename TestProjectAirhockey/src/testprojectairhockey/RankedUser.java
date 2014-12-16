/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Joep Kerste
 */
public class RankedUser {
    private final StringProperty username;
    private final StringProperty rating;
    
    public RankedUser()
    {
        this("", "");
    }
    
    public RankedUser(String username, String rating)
    {
        this.username = new SimpleStringProperty(username);
        this.rating = new SimpleStringProperty(rating);        
    }
    
    public StringProperty usernameProperty()
    {
        return username;
    }
    
    public String getUsername()
    {
        return username.get();
    }
    
    public void setUsername(String username)
    {
        this.username.set(username);
    }
    
    public StringProperty ratingProperty()
    {
        return rating;
    }
    
    public String getRating()
    {
        return rating.get();
    }
    
    public void setRating(String rating)
    {
        this.rating.set(rating);
    }
}
