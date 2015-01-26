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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import observer.RemotePublisher;

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
    private void btnSP_Click(ActionEvent evt)
    {
//        try
//        {
//            lblHoi.setText("1");
//            URL url = getClass().getResource("FXMLDocument.fxml");
//            lblHoi.setText("2");
//            if(url != null)
//            {
//                lblHoi.setText(url.toString());
//            }
//            else
//            {
//                lblHoi.setText("3.4444");
//            }
//            
//            //lblHoi.setText("3.5");
//            Parent root = FXMLLoader.load(url);
//            lblHoi.setText("3");
//            Scene scene = new Scene(root);
//            lblHoi.setText("4");
//            Stage stage = new Stage();
//            lblHoi.setText("5");
//            stage.setScene(scene);
//            lblHoi.setText("6");
//            stage.setTitle("Airhockey - Singleplayer");
//            lblHoi.setText("7");
//            stage.setResizable(false);
//            lblHoi.setText("8");
//            stage.show();
//            lblHoi.setText("9");
//            ((Node) (evt.getSource())).getScene().getWindow().hide();
//            lblHoi.setText("10");
//        }
//        catch (IOException ex)
//        {
//            lblHoi.setText(ex.getMessage());
//        }
        
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            FXMLDocumentController controller = fxmlLoader.<FXMLDocumentController>getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Airhockey - Singleplayer");
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex)
        {
            lblHoi.setText(ex.getLocalizedMessage());
        }
    }

    @FXML
    private void btnMP_Click(ActionEvent evt)
    {
        startNewWindow("Login", "Airhockey - Login", evt);
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
