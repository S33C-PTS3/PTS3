/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Game.Side;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class GameResultsController implements Initializable {

    @FXML
    Button btnMainMenu;
    
    @FXML
    Label lblResult1;
    
    @FXML
    Label lblResult2;
    
    @FXML
    Label lblResult3;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    public void setResults(String[] gameOver)
    {
        lblResult1.setText(gameOver[0]);
        lblResult2.setText(gameOver[1]);
        lblResult3.setText(gameOver[2]);
    }
    
    @FXML
    private void btnMainMenu(ActionEvent evt)
    {
        gotoScherm("Lobby.fxml", evt, "Airhockey");
    }
    
    private void gotoScherm(String bestand, ActionEvent evt, String title)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource(bestand));
            Stage stage = new Stage();
            stage.setTitle(title);
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
