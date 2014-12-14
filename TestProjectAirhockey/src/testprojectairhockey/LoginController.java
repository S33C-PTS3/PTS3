/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Game.Mode;
import Lobby.User;
import Security.AuthenticationManager;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class LoginController implements Initializable {

    AuthenticationManager authMan;
    private final int dbTimeout = 5000;
    private User loggingInUser = null;
    
    @FXML
    public TextField txtUsername;
    public PasswordField txtPassword;
    Button btnBack;
    Button btnRegister;
    Button btnLogin;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        while(true)
        {
            try
            {
                authMan = new AuthenticationManager();
                break;
            }
            catch(RuntimeException ex)
            {
                System.out.println("Database connection could not be made: " + ex.getMessage());
                try 
                {
                    Thread.sleep(dbTimeout);
                } 
                catch (InterruptedException ex1) {}
            }
        }
    }    
    
    @FXML
    public void btnLogin_Click(ActionEvent evt)
    {
        if (checkValidInput()) 
        {
            User foundUser = authMan.login(txtUsername.getText(), txtPassword.getText());
            if (foundUser != null) 
            {
                loggingInUser = foundUser;
                navToLobby();
            }
            else
            {
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.setPromptText("Gegevens ongeldig!");
                txtPassword.setPromptText("Gegevens ongeldig!");
            }
        }
    }
    
    private boolean checkValidInput()
    {
        boolean isValid = true;
        
        if (txtUsername.getText().equals("")) 
        {
            txtUsername.setPromptText("Vul uw username in!");
            isValid = false;
        }
        
        if (txtPassword.getText().equals("")) 
        {
            txtPassword.setPromptText("Vul uw wachtwoord in!");
            isValid = false;
        }
        
        return isValid;
    }
    
    @FXML
    public void btnRegister_Click(ActionEvent evt)
    {
        startNewWindow("Register", "Airhockey - Register", evt);
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
    
    private void navToLobby()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            LobbyController controller = fxmlLoader.<LobbyController>getController();
            controller.setLoggedInUser(loggingInUser);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Airhockey - Lobby");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
