/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Game.Mode;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class MenuController implements Initializable {

    @FXML
    Button btnSettings;
    Button btnSP;
    Button btnMP;
    Button btnLogin;

    @FXML
    Label lblHoi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        lblHoi.setText("Airhockey");
    }

    @FXML
    private void btnSP_Click(ActionEvent evt) throws IOException
    {
        //startNewWindow("FXMLDocument", "Airhockey - Singleplayer", evt);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Airhockey - Singleplayer");
        stage.setResizable(false);
        stage.show();
        ((Node) (evt.getSource())).getScene().getWindow().hide();

    }

    @FXML
    private void btnMP_Click(ActionEvent evt)
    {
        startNewWindow("Lobby", "Airhockey - Lobby", evt);
    }
    
    @FXML
    private void btnSettings_Click(ActionEvent evt)
    {
        startNewWindow("Settings", "Airhockey - Settings", evt);
    }

    private void startNewWindow(String file, String name, ActionEvent evt)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource(file + ".fxml"));
            Stage stage = new Stage();
            stage.setTitle(name);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            ((Node) (evt.getSource())).getScene().getWindow().hide();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
