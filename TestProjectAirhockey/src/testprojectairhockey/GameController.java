/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Chat.IChat;
import Chat.Message;
import Game.SideName;
import Game.Bat;
import Game.IPlayer;
import Game.Side;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import Game.Mode;
import Lobby.Game;
import Lobby.IGame;
import Security.AuthenticationManager;
import Shared.IActiveGame;
import Shared.IHockeyField;
import Shared.IUser;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Transform;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 *
 * @author Eric
 */
public class GameController extends UnicastRemoteObject implements Initializable, RemotePropertyListener {

    @FXML
    Label label;

    @FXML
    Label lblRoundNr;

    @FXML
    Label lblPlayer1;

    @FXML
    Label lblPlayer2;

    @FXML
    Label lblPlayer3;

    @FXML
    Label lblScore1;

    @FXML
    Label lblScore2;

    @FXML
    Label lblScore3;

    @FXML
    Canvas canvas;

    @FXML
    Button btnSend;

    @FXML
    TextField tfMessage;

    @FXML
    ListView lvChat;

    @FXML
    Button btnStart;

    @FXML
    Label lblWaiting;

    //lijst met alles messages in de chatbox
    ObservableList<String> messages = FXCollections.observableArrayList();

    //graphicscontext om te tekenen op de canvas
    GraphicsContext gc;

    //HockeyField, het startpunt van het spel (root)
    IHockeyField hockeyField;

    //Animation timer zodat het spel een loop is
    AnimationTimer timer;

    IGame myGame;

    //Modus van de game
    Mode mode = Mode.SINGLE;
    Mode usermode;
    IUser loggedInUser;
    Side[] sides;
    double diameterPuck;
    double diameterBat;

    //De afbeeldingen van de bats
    Image batRed = new Image("/testprojectairhockey/batred2.png");
    Image batBlue = new Image("/testprojectairhockey/batblue2.png");
    Image batGreen = new Image("/testprojectairhockey/batgreen2.png");

    RemotePublisher publisher;
    boolean gameActive = true;
    boolean ratingUpdated = false;
    AuthenticationManager authMan = new AuthenticationManager();

    private final int GAMESTARTDELAY = 5;

    public GameController() throws RemoteException
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Focus op het spel zodat je meteen de bat kan bewegen
        lvChat.setItems(messages);
        lvChat.setFocusTraversable(true);
        tfMessage.setFocusTraversable(false);

        gc = canvas.getGraphicsContext2D();
        canvas.setVisible(false);
        btnStart.setDisable(true);
    }

    @FXML
    public void btnStart_Click(ActionEvent evt)
    {
        try
        {
            startGame();
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startGame() throws RemoteException
    {
        publisher = (RemotePublisher) myGame.getHockeyField();
        try
        {
            publisher.addListener(this, "gameOver");
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(() ->
        {
            setVisibilityWaitingScreen();
            try
            {
                myGame.getActiveGame().startGame();
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try
            {
                //Labels vullen met de namen van de spelers
                sides = hockeyField.getSides();
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            int rotateIndex = 0;
            for (Side side : sides)
            {
                if (side.getSideName().equals(SideName.LEFT))
                {
                    lblPlayer1.setText(side.getBoundPlayer().getUsername());
                    System.out.println("Bottom: " + side.getBoundPlayer().getUsername());
                }
                if (side.getSideName().equals(SideName.BOTTOM))
                {
                    lblPlayer2.setText(side.getBoundPlayer().getUsername());
                    System.out.println("Right: " + side.getBoundPlayer().getUsername());
                }
                if (side.getSideName().equals(SideName.RIGHT))
                {
                    lblPlayer3.setText(side.getBoundPlayer().getUsername());
                    System.out.println("Left: " + side.getBoundPlayer().getUsername());
                }
                try
                {
                    if (side.getBoundPlayer().getUsername().equals(loggedInUser.getUsername()))
                    {
                        rotateIndex = side.getBoundPlayer().getID();
                    }
                }
                catch (RemoteException ex)
                {
                    Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (rotateIndex == 0)
            {
                canvas.getTransforms().add(Transform.rotate(-120 * (rotateIndex), 280, 323));
            }
            else if (rotateIndex == 1)
            {
                canvas.getTransforms().add(Transform.rotate(-120 * (rotateIndex), 280, 323));
            }
            else if (rotateIndex == 2)
            {
                canvas.getTransforms().add(Transform.rotate(-120 * (rotateIndex), 280, 323));
            }

            try
            {
                sides = hockeyField.getSides();
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //De gameloop
            timer = new AnimationTimer() {
                @Override
                public void handle(long now)
                {
                    if (gameActive)
                    {
                        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                        Draw();
                    }
                }
            };
            timer.start();
        });
        lvChat.setFocusTraversable(true);
        tfMessage.setFocusTraversable(false);
    }

    public void Draw()
    {
        
        gc.setLineWidth(1);
        double[] batPositions = null;
        int[] scores = null;
        try
        {
            batPositions = hockeyField.getBatPositions();
            scores = hockeyField.getPlayerScores();
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        double batRedX, batRedY, batGreenX, batGreenY, batBlueX, batBlueY;
        int scorePlayer1 = 0, scorePlayer2 = 0, scorePlayer3 = 0;
        batRedX = batPositions[0];
        batRedY = batPositions[1];
        batBlueX = batPositions[2];
        batBlueY = batPositions[3];
        batGreenX = batPositions[4];
        batGreenY = batPositions[5];

        if (scores.length == 3)
        {
            scorePlayer1 = scores[0];
            scorePlayer2 = scores[1];
            scorePlayer3 = scores[2];
        }

        if (scores[0] == 0 && scores[1] == 0 & scores[2] == 0) 
        {
            lblScore1.setText("20");
            lblScore2.setText("20");
            lblScore3.setText("20");
        }
        
        for (Side side : sides)
        {
            gc.setLineWidth(1);
            if ("RED".equals(side.getColor().toString()))
            {
                gc.setStroke(Color.RED);
                gc.setFill(Color.RED);
            }

            if ("BLUE".equals(side.getColor().toString()))
            {
                gc.setStroke(Color.BLUE);
                gc.setFill(Color.BLUE);
            }

            if ("GREEN".equals(side.getColor().toString()))
            {
                gc.setFill(Color.GREEN);
                gc.setStroke(Color.GREEN);
            }

            gc.strokeLine(side.getLineX1(), side.getLineY1(), side.getLineX2(), side.getLineY2());
            gc.setLineWidth(5);
            gc.strokeLine(side.getGoalX1(), side.getGoalY1(), side.getGoalX2(), side.getGoalY2());

            if (side.getSideName().equals(SideName.BOTTOM))
            {
                gc.drawImage(batRed, batBlueX - diameterBat / 2, batBlueY - diameterBat / 2, diameterBat, diameterBat);
                lblScore1.setText(String.valueOf(scorePlayer1));
            }
            else if (side.getSideName().equals(SideName.RIGHT))
            {
                gc.drawImage(batGreen, batGreenX - diameterBat / 2, batGreenY - diameterBat / 2, diameterBat, diameterBat);
                lblScore2.setText(String.valueOf(scorePlayer2));
            }
            else if (side.getSideName().equals(SideName.LEFT))
            {
                gc.drawImage(batBlue, batRedX - diameterBat / 2, batRedY - diameterBat / 2, diameterBat, diameterBat);
                lblScore3.setText(String.valueOf(scorePlayer3));
            }

            try
            {
                lblRoundNr.setText(String.valueOf(hockeyField.getRound()));
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Point2D position = null;
        try
        {
            position = new Point2D(hockeyField.getPuckPosition()[0], hockeyField.getPuckPosition()[1]);
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gc.setFill(Color.BLACK);
        gc.fillOval(position.getX() - diameterPuck / 2, position.getY() - diameterPuck / 2, diameterPuck, diameterPuck);

//            if (hockeyField.getMode().equals(Mode.SINGLE)) {
//                hockeyField.moveAIPlayers();
//            }
    }

    @FXML
    private void keyEventPressed(KeyEvent evt)
    {
        if (evt.getCode().equals(KeyCode.LEFT) || evt.getCode().equals(KeyCode.RIGHT))
        {
            try
            {
                hockeyField.setPlayerBatPosition(evt.getCode().toString(), loggedInUser.getUsername());
            }
            catch (RemoteException ex)
            {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //andere keys werken alleen als de textbox geslecteerd is.
    }

    @FXML
    private void btnSend_Click(ActionEvent evt)
    {
        sendMessage();
    }

    @FXML
    private void enterPressed(KeyEvent evt)
    {
        if (evt.getCode().equals(KeyCode.ENTER) && tfMessage.isFocused())
        {
            lvChat.setFocusTraversable(true);
            tfMessage.setFocusTraversable(false);
            lvChat.requestFocus();
            sendMessage();
        }
    }

    private void sendMessage()
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    String message = tfMessage.getText();
                    if (!message.isEmpty() && message.trim().length() > 0)
                    {

                        try
                        {
                            Message m = new Message(loggedInUser.getUsername(), message);
                            myGame.getActiveGame().addMessage(m);
                        }
                        catch (RemoteException ex)
                        {
                            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (!message.isEmpty() && message.trim().length() > 0)
                        {
                            //messages.add(message);
                            lvChat.scrollTo(lvChat.getItems().size());
                            tfMessage.clear();
                        }
                    }
                }
                catch (IllegalArgumentException ex)
                {
                    tfMessage.setPromptText("Write a Message..");
                }
            }
        });
    }

    @FXML
    private void btnExit_Click(ActionEvent evt)
    {
        if (timer != null)
        {
            timer.stop();
            gameActive = false;
            Parent root = null;
            try
            {
                Stage stage = new Stage();
                if (hockeyField.getMode().equals(Mode.SINGLE))
                {
                    root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                    stage.setTitle("Airhockey - Menu");
                }
                else if (hockeyField.getMode().equals(Mode.MULTI))
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
                    root = (Parent) fxmlLoader.load();
                    LobbyController controller = fxmlLoader.<LobbyController>getController();
                    controller.removeGame(myGame.getId());
                    myGame.getActiveGame().stopGame();
                    controller.setLoggedInUser(loggedInUser);
                    stage.setTitle("Airhockey - Mulitplayer");
                }

                hockeyField = null;

            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            try
            {
                Stage stage = new Stage();
                Parent root = null;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
                root = (Parent) fxmlLoader.load();
                LobbyController controller = fxmlLoader.<LobbyController>getController();
                controller.removeUserFromGame(myGame.getId(),loggedInUser);
                controller.setLoggedInUser(loggedInUser);
                stage.setTitle("Airhockey - Mulitplayer");

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
                ((Node) (evt.getSource())).getScene().getWindow().hide();
            }

            catch (IOException ex)
            {
                System.out.println("IOException: " + ex.getMessage());
            }
        }

    }

    public void setMode(Mode mode, IUser loggedInUser, IGame g, Mode usermode)
    {
        try
        {
            this.mode = mode;
            this.myGame = g;
            this.usermode = usermode;
            myGame.getActiveGame().addListenerO(this, "GameEnd");
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Mode.SPECTATOR.equals(usermode))
        {
            try
            {
                if (myGame.getActiveGame().getGameStatus())
                {
                    setVisibilityWaitingScreen();
                    try
                    {
                        startGame();
                    }
                    catch (RemoteException ex)
                    {
                        System.out.println("Error start watching game: " + ex.getMessage());
                    }
                }
            }
            catch (RemoteException ex)
            {
                System.out.println("Get active game  error " + ex.getMessage());
            }
        }

        try
        {
            hockeyField = myGame.getHockeyField();
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loggedInUser = loggedInUser;
        try
        {
            diameterPuck = myGame.getHockeyField().getDiameter();
            diameterBat = myGame.getHockeyField().getDiameterBat();
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            IActiveGame game = myGame.getActiveGame();
            game.addListenerO(this, "Client");
            IChat chat = game.getChat();
            chat.addListenerO(this, "Game");
            hockeyField.addListenerO(this, "gameOver");
        }
        catch (RemoteException ex)
        {
            ex.printStackTrace();
        }
    }

    private void setVisibilityWaitingScreen()
    {
        canvas.setVisible(true);
        lblWaiting.setVisible(false);
        btnStart.setVisible(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        if (loggedInUser.getUsername().equals(evt.getNewValue()))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run()
                {
                    btnStart.setDisable(false);

                    try
                    {
                        hockeyField.setBindedPlayers((IGame) evt.getOldValue());
                    }
                    catch (RemoteException ex)
                    {
                        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
        if (evt.getPropertyName().equals("Game"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run()
                {
                    messages.add((evt.getNewValue().toString()));
                }
            });

            //messages.add((evt.getNewValue().toString()));
        }
        if (evt.getPropertyName().equals("gameOver"))
        {
            System.out.println("End game!");
            endGame();
        }
        if (evt.getPropertyName().equals("Client"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run()
                {
                    setVisibilityWaitingScreen();
                    try
                    {
                        startGame();
                    }
                    catch (RemoteException ex)
                    {
                        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        }
        if (evt.getPropertyName().equals("GameEnd"))
        {

                Platform.runLater(new Runnable() {

                    @Override
                    public void run()
                    {
                        try{
                        Stage stage = new Stage();
                        Parent root = null;
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Lobby.fxml"));
                        root = (Parent) fxmlLoader.load();
                        LobbyController controller = fxmlLoader.<LobbyController>getController();
                        controller.setLoggedInUser(loggedInUser);
                        stage.setTitle("Airhockey - Mulitplayer");

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.show();
                        btnSend.getScene().getWindow().hide();
                        }
                        catch(IOException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                });

        }
    }

    public void endGame()
    {
        timer.stop();
        gameActive = false;

        if (updateRatings())
        {
            System.out.println("Player ratings were updated");
            ratingUpdated = true;
        }
        else
        {
            System.err.println("Player rating update failed");
        }

        Platform.runLater(new Runnable() {

            @Override
            public void run()
            {
                Parent root = null;
                try
                {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameResults.fxml"));
                    root = (Parent) fxmlLoader.load();
                    GameResultsController controller = fxmlLoader.<GameResultsController>getController();
                    controller.setResults(hockeyField.getGameResults(), loggedInUser, myGame.getId());

                    Stage stage = new Stage();

                    stage.setTitle("Airhockey - Game Results");

                    hockeyField = null;

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    btnSend.getScene().getWindow().hide();

                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        });

    }

    private boolean updateRatings()
    {
        try
        {
            String log = loggedInUser.getUsername();
            String sideNil = sides[0].getBoundPlayer().getUsername();

            if (!log.equals(sideNil))
            {
                return true;
            }
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean isSuccess = false;

        try
        {
            for (Side s : hockeyField.getSides())
            {
                double ratingscore;
                double endScore = s.getBoundPlayer().getInGameScore();
                double correction;
                ArrayList<IPlayer> opponents = new ArrayList<>();

                for (Side s2 : hockeyField.getSides())
                {
                    if (s2.getBoundPlayer().getID() != s.getBoundPlayer().getID())
                    {
                        opponents.add(s2.getBoundPlayer());
                    }
                }

                if (opponents.size() != 2)
                {
                    throw new RuntimeException("Opponents size is incorrect, should be 2, is " + opponents.size());
                }

                double ratingOpp1 = authMan.getPlayerRating(opponents.get(0).getUsername());
                double ratingOpp2 = authMan.getPlayerRating(opponents.get(1).getUsername());
                double ratingPlayer = authMan.getPlayerRating(s.getBoundPlayer().getUsername());
                correction = ((ratingOpp1 + ratingOpp2) - (2 * ratingPlayer)) / 8;

                ratingscore = endScore + correction;

                try
                {
                    if (authMan.updatePlayerRatingscores(s.getBoundPlayer(), ratingscore))
                    {
                        isSuccess = true;
                    }
                }
                catch (SQLException ex)
                {
                    System.err.println("SQLException in GameController.UpdateRankings() " + ex.getMessage());
                }

            }
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSuccess;
    }
}
