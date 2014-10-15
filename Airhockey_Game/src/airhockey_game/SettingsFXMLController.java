/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import java.net.URL;
import java.util.ResourceBundle;
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
    private Slider difficultySlider1;
    private Slider difficultySlider2;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
