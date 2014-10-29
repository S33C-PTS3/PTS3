/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import airhockey.domain.Difficulty;
import airhockey.domain.Settings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class SettingsFXMLController implements Initializable {

    private final NavigationManager navMan = new NavigationManager();
    @FXML
    private Slider difficultySlider1;
    @FXML
    private Slider difficultySlider2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO 
        try
        {
            FileInputStream stream = new FileInputStream((new File("robotplayersSettings")));
            ObjectInputStream input = new ObjectInputStream(stream);
            Settings settings = (Settings) input.readObject();
            input.close();

            //SET THE VALUE OF SLIDER 1
            if (settings.getDifficulty1().equals(Difficulty.EASY))
            {
                difficultySlider1.setValue(0);
            }
            else if (settings.getDifficulty1().equals(Difficulty.MEDIUM))
            {
                difficultySlider1.setValue(1);
            }
            else if (settings.getDifficulty1().equals(Difficulty.HARD))
            {
                difficultySlider1.setValue(2);
            }

            //SET THE VALUE OF SLIDER 2
            if (settings.getDifficulty2().equals(Difficulty.EASY))
            {
                difficultySlider2.setValue(0);
            }
            else if (settings.getDifficulty2().equals(Difficulty.MEDIUM))
            {
                difficultySlider2.setValue(1);
            }
            else if (settings.getDifficulty2().equals(Difficulty.HARD))
            {
                difficultySlider2.setValue(2);
            }
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println(ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(SettingsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleBtnBackEvent(ActionEvent event)
    {
        navMan.goTo("IT1MainMenu", event);
    }

    @FXML        //SAVING ROBOTPLAYER SETTINGS

    private void handleBtnSaveAndBackEvent(ActionEvent event) throws IOException
    {
        //TODO: IMPLEMENTATIE INSTELLINGEN OPSLAAN
        FileOutputStream stream = null;
        //SETTING THE DIFFICULTY AND SAVE IT IN THE SETTINGS
        try
        {
            Difficulty diff1 = Difficulty.MEDIUM;
            Difficulty diff2 = Difficulty.MEDIUM;

            int diff = (int) difficultySlider1.getValue();
            switch (diff)
            {
                case 0:
                    diff1 = Difficulty.EASY;
                    break;
                case 1:
                    diff1 = Difficulty.MEDIUM;
                    break;
                case 2:
                    diff1 = Difficulty.HARD;
                    break;
            }

            diff = (int) difficultySlider2.getValue();
            switch (diff)
            {
                case 0:
                    diff2 = Difficulty.EASY;
                    break;
                case 1:
                    diff2 = Difficulty.MEDIUM;
                    break;
                case 2:
                    diff2 = Difficulty.HARD;
                    break;
            }

            Settings settings = new Settings(diff1, diff2);

            //SAVING ROBOTPLAYER SETTINGS
            stream = new FileOutputStream(new File("robotplayersSettings"));
            ObjectOutputStream out = new ObjectOutputStream(stream);
            out.writeObject(settings);
            out.close();
            stream.close();
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        } finally
        {
            stream.close();
        }

        navMan.goTo("IT1MainMenu", event);
    }
}
