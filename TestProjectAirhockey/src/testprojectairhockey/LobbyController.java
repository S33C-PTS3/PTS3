/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Chat.Message;
import Game.Mode;
import Game.Spectator;
import Lobby.Game;
import Lobby.IGame;
import Lobby.User;
import Security.AuthenticationManager;
import Shared.ILobby;
import Shared.IUser;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 * FXML Controller class
 *
 * @author Eric
 */
public class LobbyController extends UnicastRemoteObject implements Initializable, RemotePropertyListener {

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
    TableView tvRanking;

    @FXML
    TextField tfMessage;

    @FXML
    Button btnSend;

    @FXML
    Button btnClose;

    @FXML
    Button btnRefresh;

    @FXML
    public Label lblLoggedInUser;

    private final AuthenticationManager authMan = new AuthenticationManager();

    private ObservableList<String> messages;
    private ArrayList<IGame> games;
    private LobbyRMI rmiController;
    private IUser loggedInUser = new User("Meny");
    private Spectator spectator;
    // width of accordion / 4 to determine width of the columns
    private final double COLUMNWIDTH = 137.5;
    private final double ROWHEIGHT = 20;
    private final int LASTPLAYERINDEX = 6;
    boolean isOverviewOpen = false;
    Button btnJoin;
    Button btnSpectate;

    public LobbyController() throws RemoteException
    {

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        messages = FXCollections.observableArrayList();
        lvChatBox.setItems(messages);
        populateRanking();

        try
        {
            rmiController = new LobbyRMI();
            rmiController.getLobby().getChat().addListener(this, "Lobby");
            rmiController.getLobby().addListener(this, "lobby");
            getMessages();
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
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

    @FXML
    private void btnRefresh_Click(ActionEvent evt)
    {
        refresh();
    }

    @FXML
    private void btnCreateGame_Click(ActionEvent evt)
    {
        ILobby lobby = rmiController.getLobby();
        String[] gameInfo = null;
        try
        {
            System.out.println(lobby.toString());
            gameInfo = lobby.addGame(loggedInUser.getUsername() + "'s Game", loggedInUser);
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        createNewGame(gameInfo);
        try
        {
            navigateToGame(rmiController.getLobby().getGame(Integer.valueOf(gameInfo[0])), true);

        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnLogout_Click(ActionEvent evt)
    {
        try
        {
            Stage closingStage = (Stage) ((Node) evt.getSource()).getScene().getWindow();
            closingStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Airhockey");
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    private void refresh()
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

    private void createNewGame(String[] gameInfo)
    {
        int playerCount = 0;
        String gameId = gameInfo[0];
        String gameName = gameInfo[1];
        String gameAverageRanking = gameInfo[2];
        String player1 = gameInfo[3];
        String player2 = gameInfo[4];
        String player3 = gameInfo[5];
        String spectatorCount = gameInfo[6];
        TitledPane gameTitle = new TitledPane();
        gameTitle.setText(gameName);
        AnchorPane gamePane = new AnchorPane();

        GridPane gamegrid = new GridPane();
        for (int i = 3; i < LASTPLAYERINDEX; i++)
        {
            if ((gameInfo[i]) != null)
            {
                playerCount++;
            }
        }
        //550 /4 = 137,5       
        for (int i = 0; i < 4; i++)
        {
            ColumnConstraints column = new ColumnConstraints(COLUMNWIDTH);
            gamegrid.getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++)
        {
            RowConstraints row = new RowConstraints(ROWHEIGHT);
            gamegrid.getRowConstraints().add(row);
        }
        gamegrid.setPrefSize(550, 300);
        Label idLabel = new Label();
        // grid column 0
        Label labelPlayers = new Label();
        labelPlayers.setText("Players: " + playerCount + "/3");
        Label labelSpectators = new Label();
        labelSpectators.setText("Spectators: " + spectatorCount + "/2");
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
        btnJoin = new Button();
        btnJoin.setText("Join game");
        btnJoin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    rmiController.getLobby().addUserToGame(Integer.parseInt(gameId), (User) loggedInUser);
                    navigateToGame(rmiController.getLobby().getGame(Integer.valueOf(gameId)), true);
                    btnJoin.getScene().getWindow().hide();
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        gamegrid.add(btnJoin, 2, 1);
        //grid column 3
        btnSpectate = new Button();
        btnSpectate.setText("Spectate");
        btnSpectate.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    if (spectator == null)
                    {
                        spectator = new Spectator(loggedInUser.getUsername());
                    }
                    spectator = rmiController.getLobby().addSpectatorToGame(Integer.parseInt(gameId), spectator);
                    System.out.println("Nr of games: " + spectator.getGames().size());
                    if (!isOverviewOpen)
                    {
                        navigateToSpectatorOverview();
                        isOverviewOpen = true;
                    }
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        gamegrid.add(btnSpectate, 3, 1);
        //spectate button is niet zichtbaar voor iteratie 2
        btnSpectate.visibleProperty().set(true);
        gamegrid.visibleProperty().set(false);
        gamePane.getChildren().add(gamegrid);
        gameTitle.setContent(gamegrid);
        idLabel.setText(gameId);
        GameAccordion.getPanes().add(gameTitle);
        for (int i = 0; i < 6; i++)
        {
            System.out.println(gameInfo[i]);
        }
    }

    private void navigateToGame(IGame g, boolean player)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            GameController controller = fxmlLoader.<GameController>getController();

            RemotePublisher publisher = (RemotePublisher) rmiController.getLobby();
            publisher.addListener(controller, "client");
            controller.setMode(Mode.MULTI, loggedInUser, g, Mode.PLAYER);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Airhockey - Multiplayer");
            stage.setResizable(false);
            stage.show();
            if (player)
            {
                btnStartNewGame.getScene().getWindow().hide();
            }

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void navigateToSpectatorOverview()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SpectatorOverview.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            SpectatorOverviewController controller = fxmlLoader.<SpectatorOverviewController>getController();
            RemotePublisher publisher = (RemotePublisher) rmiController.getLobby();
            publisher.addListener(controller, "SpectatorOverview");
            controller.setSpectator(spectator);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Airhockey - Spectator overview");
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex)
        {
        }
    }

    public void setLoggedInUser(IUser u)
    {
        this.loggedInUser = u;
        try
        {
            lblLoggedInUser.setText("Welcome " + loggedInUser.getUsername());
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateRanking()
    {
        ObservableList<String> ratings = FXCollections.observableArrayList();

        ratings.addAll(Arrays.asList(authMan.getRanking()));

        tvRanking.setEditable(true);

        TableView<RankedUser> rankingTable = null;
        TableColumn<RankedUser, String> usernameColumn = new TableColumn("Username");
        TableColumn<RankedUser, String> ratingColumn = new TableColumn("Rating");
        usernameColumn.setResizable(false);
        ratingColumn.setResizable(false);

        ObservableList<RankedUser> rankingList = FXCollections.observableArrayList();

        String[] ranking = authMan.getRanking();
        for (String current : ranking)
        {
            rankingList.add(new RankedUser(current.substring(0, current.indexOf("|")), current.substring(current.indexOf("|") + 1, current.length())));
        }

        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        usernameColumn.prefWidthProperty().bind(tvRanking.widthProperty().divide(2));
        ratingColumn.prefWidthProperty().bind(tvRanking.widthProperty().divide(2).subtract(1));
        tvRanking.getColumns().addAll(usernameColumn, ratingColumn);
        tvRanking.setItems(rankingList);

        tvRanking.getColumns().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change)
            {
                change.next();
                if (change.wasReplaced())
                {
                    tvRanking.getColumns().clear();
                    tvRanking.getColumns().addAll(usernameColumn, ratingColumn);
                }
            }
        });
    }

    private void sendMessage()
    {

        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Message m = new Message(loggedInUser.getUsername(), tfMessage.getText());
                    rmiController.getLobby().addMessage(loggedInUser.getUsername(), tfMessage.getText());
                    messages.add(m.toString());
                    tfMessage.clear();
                    lvChatBox.scrollTo(lvChatBox.getItems().size());
                    tfMessage.clear();
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IllegalArgumentException ex)
                {
                    tfMessage.setPromptText("Write a Message..");
                }
            }
        });
    }

    private void getMessages()
    {
        ILobby lobby = rmiController.getLobby();
        try
        {
            for (int i = 0; i < lobby.getMessages().size(); i++)
            {
                messages.add(lobby.getMessages().get(i).toString());
                lvChatBox.scrollTo(lvChatBox.getItems().size());
            }
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeGame(int gameID)
    {
        try
        {
            rmiController.getLobby().removeGame(gameID);
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        if (evt.getNewValue() instanceof Message)
        {
            Message m = (Message) evt.getNewValue();
            if (!m.getSender().equals(loggedInUser.getUsername()))
            {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run()
                    {
                        messages.add(m.toString());
                    }
                });
                //messages.add(m.toString());
            }
        }
        if (evt.getPropertyName().equals("lobby"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run()
                {
                    btnJoin.setDisable(true);
                }
            });
        }
        if (evt.getPropertyName().equals("spectator"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run()
                {
                    btnSpectate.setDisable(true);
                }
            });
        }
    }
}
