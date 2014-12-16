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
    Button btnRestart;
    
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

    public void setResults(List<Side> gameOver)
    {
        lblResult1.setText(gameOver.get(0).getBoundPlayer().toString() + " - " + gameOver.get(0).getBoundPlayer().getInGameScore());
        lblResult2.setText(gameOver.get(1).getBoundPlayer().toString() + " - " + gameOver.get(1).getBoundPlayer().getInGameScore());
        lblResult3.setText(gameOver.get(2).getBoundPlayer().toString() + " - " + gameOver.get(2).getBoundPlayer().getInGameScore());
    }
    
    @FXML
    private void btnMainMenu(ActionEvent evt)
    {
        gotoScherm("Menu.fxml", evt, "Airhockey");
    }
    
    @FXML void btnRestart(ActionEvent evt)
    {
        gotoScherm("FXMLDocument.fxml", evt, "Airhockey - Singleplayer");
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
