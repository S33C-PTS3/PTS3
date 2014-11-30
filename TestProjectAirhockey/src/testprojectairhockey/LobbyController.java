/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class LobbyController implements Initializable {

    @FXML
    Button btnStartNewGame;
    
    @FXML
    ScrollPane RankingPane;
    
    @FXML
    Accordion GameAccordion;
    
    @FXML
    ListView lvChatBox;
    
    @FXML
    TextField tfMessage;
    
    @FXML
    Button btnSend;
    
    ObservableList<String> messages = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        lvChatBox.setItems(messages);
    }
    
    @FXML
    private void btnSend_Click(ActionEvent evt)
    {
        sendMessage();
    }
    
    @FXML
    private void enterPressed(KeyEvent evt)
    {
        if (evt.getCode().equals(KeyCode.ENTER))
        {
            sendMessage();
        }
    }

    private void sendMessage()
    {
        //van wie komt het bericht.. voorbeeldbericht: Eric: Hallo!
        String message = tfMessage.getText();
        if (!message.isEmpty() && message.trim().length() > 0)
        {
            messages.add(message);
            lvChatBox.scrollTo(lvChatBox.getItems().size());
            tfMessage.clear();
        }
    }
    
}
