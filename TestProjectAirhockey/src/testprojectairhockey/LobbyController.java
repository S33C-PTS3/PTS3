/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Lobby.Game;
<<<<<<< HEAD
=======
import Lobby.User;
import Shared.ILobby;
>>>>>>> FETCH_HEAD
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
<<<<<<< HEAD
import Shared.*;
import java.rmi.registry.Registry;
import java.util.ArrayList;
=======
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
>>>>>>> FETCH_HEAD
import javafx.event.ActionEvent;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import observer.RemotePublisher;

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

    @FXML
    Button btnRefresh;

    ObservableList<String> messages;
    ArrayList<Game> games;
    LobbyRMI rmiController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        lvChatBox.setItems(messages);
        messages = FXCollections.observableArrayList();
        try {
            rmiController = new LobbyRMI();
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            for(Game g : rmiController.getLobby().getGames())
            {
                System.out.println(g.toString());
            }
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void btnCreateGame_Click(ActionEvent evt)
    {
            TitledPane gameTitle = new TitledPane();
            gameTitle.setText("Rens' game");
            GameAccordion.getPanes().add(gameTitle);
            ILobby lobby = rmiController.getLobby();
        try {
            lobby.addGame(new Game("Meny's Game", new User("Meny")));
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rmiController.getGameCount();
            
    }

}
