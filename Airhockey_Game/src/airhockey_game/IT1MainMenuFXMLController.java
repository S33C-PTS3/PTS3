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
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class IT1MainMenuFXMLController implements Initializable {

    private final NavigationManager navMan = new NavigationManager();
    private Button btnStart;
    private Button btnSettings;
    private Button btnExit;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleBtnStartEvent(ActionEvent event)
    {
        navMan.goTo("Game", event);
    }
    
    @FXML
    private void handleBtnSettingsEvent(ActionEvent event)
    {
        navMan.goTo("Settings", event);
    }
    
    @FXML
    private void handleBtnExitEvent(ActionEvent event)
    {
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "WARNING", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) 
        {
            System.exit(0);
        }
    }
}
