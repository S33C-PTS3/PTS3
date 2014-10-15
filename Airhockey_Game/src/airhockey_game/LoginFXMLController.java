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
import javafx.scene.control.*;

/**
 *
 * @author Joep Kerste
 */
public class LoginFXMLController implements Initializable {
    
    @FXML
    private final NavigationManager navMan = new NavigationManager();
    private Button btnLogin;
    private Button btnRegister;
    public TextField txtUsername;
    public PasswordField txtPassword;
    
    @FXML
    private void handleBtnLoginAction(ActionEvent event) {
        System.out.println("Ingelogd als " + txtUsername.getText() + " met wachtwoord " + txtPassword.getText());
    }
    
    @FXML
    private void handleBtnRegisterAction(ActionEvent event)
    {
        navMan.goTo("Register", event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
