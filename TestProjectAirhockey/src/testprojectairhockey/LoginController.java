/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class LoginController implements Initializable {

    @FXML
    TextField txtUsername;
    TextField txtPassword;
    Button btnBack;
    Button btnRegister;
    Button btnLogin;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void btnLogin_Click(ActionEvent evt)
    {
        
    }
    
    @FXML
    public void btnRegister_Click(ActionEvent evt)
    {
        
    }
    
    @FXML
    public void btnBack_Click(ActionEvent evt)
    {
        startNewWindow("Menu", "Airhockey", evt);
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
            System.out.println(ex.getMessage());
        }
    }
}
