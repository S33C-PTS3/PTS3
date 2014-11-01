/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *This class handles navigation between different scenes.
 * @author Joep Kerste
 */
public class NavigationManager {
    
    /**
     * Go to a different FXML scene
     * @param fxmlName name of the .fxml file of the destination
     * @param event event passed by button click
     * @return if navigation was succesful return true, else false
     */
    public boolean goTo(String fxmlName, ActionEvent event)
    {
        boolean isSuccess;
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("/airhockey_game/" + fxmlName +".fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
            isSuccess = true;
        }
        catch (IOException ex)
        {
            System.out.println("Parent root could not be loaded.");
            System.out.println(ex.getMessage());
            isSuccess = false;
        }
        
        return isSuccess;
    }    
}
