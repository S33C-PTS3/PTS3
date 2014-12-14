/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Security.AuthenticationManager;
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
public class RegisterController implements Initializable {

    private final AuthenticationManager authMan = new AuthenticationManager();
    
    @FXML
    Button btnBackMain;
    Button btnBackLogin;
    Button btnRegister;
    public TextField txtUsername;
    public PasswordField txtPassword1;
    public PasswordField txtPassword2;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    @FXML
    public void btnBackMain_Click(ActionEvent evt)
    {
        startNewWindow("Menu", "Airhockey", evt);
    }
    
    @FXML
    public void btnBackLogin_Click(ActionEvent evt)
    {
        startNewWindow("Login", "Airhockey - Login", evt);
    }
    
    @FXML
    public void btnRegister_Click(ActionEvent evt)
    {   
        if (checkValidInput()) 
        {
            boolean success = authMan.register(txtUsername.getText(), txtPassword1.getText());
            if (success) 
            {
                startNewWindow("Login", "Airhockey - Login", evt);
            }
            else
            {
                txtUsername.setText("");
                txtPassword1.setText("");
                txtPassword2.setText("");
                txtUsername.setPromptText("Gegevens ongeldig!");
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
        
        if (txtPassword1.getText().equals("")) 
        {
            txtPassword1.setPromptText("Vul uw wachtwoord in!");
            isValid = false;
        }
        
        if (txtPassword2.getText().equals("")) 
        {
            txtPassword2.setPromptText("Vul uw wachtwoord in!");
            isValid = false;
        }
        
        if (!txtPassword1.getText().equals(txtPassword2.getText())) 
        {
            isValid = false;
            txtPassword1.setText("");
            txtPassword2.setText("");
            txtPassword1.setPromptText("Wachtwoord incorrect!");
            txtPassword2.setPromptText("Wachtwoord incorrect!");
        }
        
        return isValid;
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
