/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Lobby.Game;
import Lobby.User;
import Shared.ILobby;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

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
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        lvChatBox.setItems(messages);
        messages = FXCollections.observableArrayList();
        try
        {
            rmiController = new LobbyRMI();
        }
        catch (RemoteException ex)
        {
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
    private void btnRefresh_Click(ActionEvent evt)
    {
        try
        {
            GameAccordion.getPanes().clear();
            for (String[] gamesArray : rmiController.getLobby().getGames())            
            {
                createNewGame(gamesArray);
            }
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnCreateGame_Click(ActionEvent evt)
    {
        ILobby lobby = rmiController.getLobby();
        String[] gameInfo = null;
        try
        {
            gameInfo = lobby.addGame(new Game("Meny's Game", new User("Meny")));
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        createNewGame(gameInfo);

    }

    private void createNewGame(String[] gameInfo)
    {
        String gameId = gameInfo[0];
        String gameName = gameInfo[1];
        String gameAverageRanking = gameInfo[2];
        String player1 = gameInfo[3];
        String player2 = gameInfo[4];
        String player3 = gameInfo[5];
        TitledPane gameTitle = new TitledPane();
        gameTitle.setText(gameName);
        AnchorPane gamePane = new AnchorPane();

        Label idLabel = new Label();
        Label ratingLabel = new Label();
        Label labelSpeler1 = new Label();
        Label labelSpeler2 = new Label();
        Label labelSpeler3 = new Label();

        idLabel.setText(gameId);
        ratingLabel.setText(gameAverageRanking);
        labelSpeler1.setText(player1);
        labelSpeler2.setText(player2);
        labelSpeler3.setText(player3);

        ratingLabel.setTranslateX(100);
        ratingLabel.setTranslateY(100);
        labelSpeler1.setTranslateX(200);
        gamePane.getChildren().add(labelSpeler1);
        gamePane.getChildren().add(labelSpeler2);
        gamePane.getChildren().add(labelSpeler3);
        gamePane.getChildren().add(idLabel);
        gamePane.getChildren().add(ratingLabel);
        gameTitle.setContent(gamePane);
        GameAccordion.getPanes().add(gameTitle);
        for (int i = 0; i < 6; i++)
        {
            System.out.println(gameInfo[i]);
        }
    }
}
