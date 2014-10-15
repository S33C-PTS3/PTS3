/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockeyveldtekenen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Eric
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private ImageView imageview;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        label.setText("Hello World");
        Image image = new Image("side_red.png");
        imageview.setImage(image);
    }   
    
    @FXML
    private void keyPressed(KeyEvent evt)
    {
         KeyCode keyCode = evt.getCode();
        if(keyCode.equals(keyCode.UP))
        {
            imageview.relocate(imageview.getLayoutX(), imageview.getLayoutY() -2);
        }
        else if(keyCode.equals(keyCode.DOWN))
        {
            imageview.relocate(imageview.getLayoutX(), imageview.getLayoutY() + 2);
        }
        else if(keyCode.equals(keyCode.LEFT))
        {
            imageview.relocate(imageview.getLayoutX() - 2, imageview.getLayoutY());
        }
        else if(keyCode.equals(keyCode.RIGHT))
        {
            imageview.relocate(imageview.getLayoutX() +2, imageview.getLayoutY());
        }
        else
        {
            System.out.println("Dikke doei");
        }
    }
    
    
}
