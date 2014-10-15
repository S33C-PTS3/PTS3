/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import javafx.scene.input.MouseEvent;
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
public class GameFXMLController implements Initializable {

    private NavigationManager navMan = new NavigationManager();
    public Button btnExit;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleBtnExitEvent(ActionEvent event)
    {
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "WARNING", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) 
        {
            navMan.goTo("IT1MainMenu", event);
        }
    }
    
    @FXML
    public void handleBtnExitMouseEnterEvent(MouseEvent event)
    {
        btnExit.setText("Exit game");
        btnExit.setOpacity(1);
    }
    
    @FXML
    public void handleBtnExitMouseExitEvent(MouseEvent event)
    {
        btnExit.setText("x");
        btnExit.setOpacity(0.3);
    }
}
