/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import SPGame.Difficulty;
import SPGame.Settings;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class SettingsController implements Initializable {
    @FXML
    private Slider difficultySlider1;
    @FXML
    private Slider difficultySlider2;
    private SerializationManager serMan = new SerializationManager();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Settings loadedSettings = serMan.loadAIsettings();
        if (serMan.loadAIsettings() != null) 
        {
            switch (loadedSettings.getDifficulty1())
            {
                case EASY:
                    difficultySlider1.setValue(0);
                    break;
                case MEDIUM:
                    difficultySlider1.setValue(1);
                    break;
                case HARD:
                    difficultySlider1.setValue(2);
                    break;
            }
            
            switch (loadedSettings.getDifficulty2())
            {
                case EASY:
                    difficultySlider2.setValue(0);
                    break;
                case MEDIUM:
                    difficultySlider2.setValue(1);
                    break;
                case HARD:
                    difficultySlider2.setValue(2);
                    break;
            }
        }
        else
        {
            difficultySlider1.setValue(1);
            difficultySlider2.setValue(1);
        }
    }

    @FXML
    private void handleBtnBackEvent(ActionEvent event)
    {
        gotoMenu(event);
    }

    @FXML        //SAVING ROBOTPLAYER SETTINGS
    private void handleBtnSaveAndBackEvent(ActionEvent event) throws IOException
    {
        Difficulty diff1 = null;
        Difficulty diff2 = null;
        
        switch ((int)difficultySlider1.getValue())
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
        
        switch ((int)difficultySlider2.getValue())
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
        
        Settings settingsToBeSaved = new Settings(diff1,diff2);
        
        if (serMan.saveAIsettings(settingsToBeSaved)) 
        {
            gotoMenu(event);
        }
        else
        {
            System.out.println("SETTINGS WERE NOT SAVED");
        }
    }
    
    private void gotoMenu(ActionEvent evt)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Airhockey - Menu");
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            ((Node) (evt.getSource())).getScene().getWindow().hide();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
