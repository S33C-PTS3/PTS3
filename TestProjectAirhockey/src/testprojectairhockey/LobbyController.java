/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Game.Mode;
import Lobby.Game;
import Lobby.IGame;
import Lobby.User;
import Security.AuthenticationManager;
import Shared.ILobby;
import Shared.IUser;
import java.io.IOException;
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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
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
    ScrollPane GamePane;

    @FXML
    ListView lvChatBox;
    
    @FXML
    ListView lvRanking;

    @FXML
    TextField tfMessage;

    @FXML
    Button btnSend;

    @FXML
    Button btnRefresh;
    
    @FXML
    public Label lblLoggedInUser;
    
    private final AuthenticationManager authMan = new AuthenticationManager();
    private ObservableList<String> messages;
    private ArrayList<IGame> games;
    private LobbyRMI rmiController;
    private IUser loggedInUser;
    // widht of accordion / 4 to determine width of the columns
    private final double COLUMNWIDTH = 137.5;
    private final double ROWHEIGHT = 20;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvChatBox.setItems(messages);
        messages = FXCollections.observableArrayList();
        populateRanking();
        try {
            rmiController = new LobbyRMI();
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
    }

    @FXML
    private void btnSend_Click(ActionEvent evt) {
        sendMessage();
    }

    @FXML
    private void enterPressed(KeyEvent evt) {
        if (evt.getCode().equals(KeyCode.ENTER)) {
            sendMessage();
        }
    }

    private void sendMessage() {
        //van wie komt het bericht.. voorbeeldbericht: Eric: Hallo!
        String message = tfMessage.getText();
        if (!message.isEmpty() && message.trim().length() > 0) {
            messages.add(message);
            lvChatBox.scrollTo(lvChatBox.getItems().size());
            tfMessage.clear();
        }
    }

    @FXML
    private void btnRefresh_Click(ActionEvent evt) {
        refresh();
    }

    @FXML
    private void btnCreateGame_Click(ActionEvent evt) {
        ILobby lobby = rmiController.getLobby();
        String[] gameInfo = null;
        try {
            System.out.println(lobby.toString());
            gameInfo = lobby.addGame(new Game(loggedInUser.getUsername() + "'s Game", (User)loggedInUser));
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        createNewGame(gameInfo);
    }

    private void refresh() {
        try {
            GameAccordion.getPanes().clear();
            for (String[] gamesArray : rmiController.getLobby().getGames()) {
                createNewGame(gamesArray);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createNewGame(String[] gameInfo) {
        int playerCount = 0;
        String gameId = gameInfo[0];
        String gameName = gameInfo[1];
        String gameAverageRanking = gameInfo[2];
        String player1 = gameInfo[3];
        String player2 = gameInfo[4];
        String player3 = gameInfo[5];
        TitledPane gameTitle = new TitledPane();
        gameTitle.setText(gameName);
        AnchorPane gamePane = new AnchorPane();

        GridPane gamegrid = new GridPane();
        for(int i = 3; i < gameInfo.length; i++)
        {
            if(gameInfo[i] != null)
            {
                playerCount++;
            }
        }
        //550 /4 = 137,5       
        for (int i = 0; i < 4; i++) {
            ColumnConstraints column = new ColumnConstraints(COLUMNWIDTH);
            gamegrid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++) {
            RowConstraints row = new RowConstraints(ROWHEIGHT);
            gamegrid.getRowConstraints().add(row);
        }
        gamegrid.setPrefSize(550, 300);
        Label idLabel = new Label();
        // grid column 0
        Label labelPlayers = new Label();
        labelPlayers.setText("Players: " + playerCount + "/3");
        Label labelSpectators = new Label();
        labelSpectators.setText("Spectators: 0");
        Label labelRating = new Label();
        labelRating.setText("Avg. rating: " + gameAverageRanking);
        //              node      col row
        gamegrid.add(labelPlayers, 0, 0);
        gamegrid.add(labelSpectators, 0, 1);
        gamegrid.add(labelRating, 0, 2);
        // grid column 1
        Label labelSpeler1 = new Label();
        labelSpeler1.setText("Player 1: " + player1);
        Label labelSpeler2 = new Label();
        labelSpeler2.setText("Player 2: " + player2);
        Label labelSpeler3 = new Label();
        labelSpeler3.setText("Player 3: " + player3);
        gamegrid.add(labelSpeler1, 1, 0);
        gamegrid.add(labelSpeler2, 1, 1);
        gamegrid.add(labelSpeler3, 1, 2);
        //grid column 2
        Button btnJoin = new Button();
        btnJoin.setText("Join game");
        btnJoin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    rmiController.getLobby().addUserToGame(Integer.parseInt(gameId), (User)loggedInUser);
                    navigateToGame();
                } catch (RemoteException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        gamegrid.add(btnJoin, 2, 1);
        //grid column 3
        Button btnSpectate = new Button();
        gamegrid.add(btnSpectate, 3, 1);
        //spectate button is niet zichtbaar voor iteratie 2
        btnSpectate.visibleProperty().set(true);
        gamegrid.visibleProperty().set(false);
        gamePane.getChildren().add(gamegrid);
        gameTitle.setContent(gamegrid);
        idLabel.setText(gameId);
        GameAccordion.getPanes().add(gameTitle);
        for (int i = 0; i < 6; i++) {
            System.out.println(gameInfo[i]);
        }
    }

    private void navigateToGame() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            GameController controller = fxmlLoader.<GameController>getController();
            controller.setMode(Mode.MULTI, loggedInUser.getUsername());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Airhockey - Multiplayer");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void setLoggedInUser(User u)
    {
        this.loggedInUser = u;
        try {
            lblLoggedInUser.setText("Welcome " + loggedInUser.getUsername());
        } catch (RemoteException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void populateRanking()
    {
        ObservableList<String> ratings = FXCollections.observableArrayList();;
                
        ratings.addAll(Arrays.asList(authMan.getRanking()));

        lvRanking.setItems(ratings);
    }
}
