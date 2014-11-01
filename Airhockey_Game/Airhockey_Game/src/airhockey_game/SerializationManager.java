/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import airhockey.domain.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Joep Kerste
 */
public class SerializationManager 
{
    public boolean saveAIsettings(Settings s)
    {
        boolean wasSuccess = true;
        try
        {
            FileOutputStream stream = null;
            stream = new FileOutputStream(new File("robotplayersSettings"));
            ObjectOutputStream out = new ObjectOutputStream(stream);
            out.writeObject(s);
            out.close();
            stream.close();
        }
        catch (IOException ex)
        {
            wasSuccess = false;
        }
        
        return wasSuccess;
    }
    
    public Settings loadAIsettings()
    {
        Settings loadedSettings = null;
        try
        {
            FileInputStream stream = new FileInputStream((new File("robotplayersSettings")));
            ObjectInputStream input = new ObjectInputStream(stream);
            Settings settings = (Settings) input.readObject();
            input.close();
            
            loadedSettings = settings;
        } 
        catch (IOException | ClassNotFoundException ex)
        {
            System.out.println("Settings were not found/loaded.");
        } 
        
        return loadedSettings;
    }
}
