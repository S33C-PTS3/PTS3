/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import java.awt.Insets;
import java.io.IOException;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class SettingsFXMLController implements Initializable {

    private NavigationManager navMan = new NavigationManager();
    private Slider difficultySlider1;
    private Slider difficultySlider2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void handleBtnBackEvent(ActionEvent event)
    {
        navMan.goTo("IT1MainMenu", event);
    }
    
    @FXML
    private void handleBtnSaveAndBackEvent(ActionEvent event)
    {
        //TODO: IMPLEMENTATIE INSTELLINGEN OPSLAAN
        navMan.goTo("IT1MainMenu", event);
    }
}
