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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author Joep Kerste
 */
public class SerializationManager 
{
    public boolean saveAIsettings(Settings s)
    {
        return false;
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
            ex.printStackTrace();
        } 
        
        return loadedSettings;
    }
}
