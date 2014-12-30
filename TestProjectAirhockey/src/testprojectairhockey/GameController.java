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
import Shared.IActiveGame;
import Shared.IHockeyField;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
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

    //Modus van de game
    Mode mode = Mode.SINGLE;
    String loggedInUser = "";
    Side[] sides;
    double diameterPuck;

    //De afbeeldingen van de bats
    Image batRed = new Image("/testprojectairhockey/batred2.png");
    Image batBlue = new Image("/testprojectairhockey/batblue2.png");
    Image batGreen = new Image("/testprojectairhockey/batgreen2.png");

    GameRMI rmiController;
    RemotePublisher publisher;

    public GameController() throws RemoteException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Focus op het spel zodat je meteen de bat kan bewegen
        lvChat.setItems(messages);
        lvChat.setFocusTraversable(true);
        tfMessage.setFocusTraversable(false);

        gc = canvas.getGraphicsContext2D();
        canvas.setVisible(false);
        //btnStart.setDisable(true);

    }

    @FXML
    public void btnStart_Click(ActionEvent evt) {

        startGame();
    }

    public void startGame() {
        publisher = (RemotePublisher) rmiController.getHockeyField();
        try {
            publisher.addListener(this, "gameOver");
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(() -> {
            setVisibilityWaitingScreen();
            try {
                rmiController.getActiveGame().startGame();
            } catch (RemoteException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //Labels vullen met de namen van de spelers
                sides = hockeyField.getSides();
            } catch (RemoteException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            int rotateIndex = 0;
            for (Side side : sides) {
                if (side.getSideName().equals(SideName.BOTTOM)) {
                    lblPlayer1.setText(side.getBoundPlayer().getUsername());
                    System.out.println("Bottom: " + side.getBoundPlayer().getUsername());
                }
                if (side.getSideName().equals(SideName.RIGHT)) {
                    lblPlayer2.setText(side.getBoundPlayer().getUsername());
                    System.out.println("Right: " + side.getBoundPlayer().getUsername());
                }
                if (side.getSideName().equals(SideName.LEFT)) {
                    lblPlayer3.setText(side.getBoundPlayer().getUsername());
                    System.out.println("Left: " + side.getBoundPlayer().getUsername());
                }
                if (side.getBoundPlayer().getUsername().equals(loggedInUser)) {
                    rotateIndex = side.getBoundPlayer().getID();
                }
            }

            if (rotateIndex == 0) {
                canvas.getTransforms().add(Transform.rotate(-120 * (rotateIndex + 1), 280, 323));
            } else if (rotateIndex == 1) {
                canvas.getTransforms().add(Transform.rotate(-120 * (rotateIndex - 1), 280, 323));
            } else if (rotateIndex == 2) {
                canvas.getTransforms().add(Transform.rotate(-120 * (rotateIndex), 280, 323));
            }

            //De gameloop
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    Draw();
                }
            };
            timer.start();
        });
        lvChat.setFocusTraversable(true);
        tfMessage.setFocusTraversable(false);
    }

    public void Draw() {
        gc.setLineWidth(1);

        //hulplijnen om te kijken of de puck en de bats in het midden staan.
        //gc.strokeLine(sides[0].getLineX1(), sides[0].getLineY1(), sides[0].getLineX1(), sides[0].getLineY2());
        //gc.strokeLine(sides[1].getLineX1(), sides[1].getLineY1(), (sides[2].getLineX2() + sides[2].getLineX1()) / 2, (sides[2].getLineY2() + sides[2].getLineY1()) / 2);
        //gc.strokeLine(sides[2].getLineX1(), sides[2].getLineY1(), (sides[0].getLineX2() + sides[0].getLineX1()) / 2, (sides[0].getLineY2() + sides[0].getLineY1()) / 2);
//        double[] batPositions = null;
//        double batRedX,batRedY,batGreenX,batGreenY,batBlueX,batBlueY;
//        batRedX = batPositions[0];
//        batRedY = batPositions[1];
//        batBlueX = batPositions[2];
//        batBlueY = batPositions[3];
//        batGreenX = batPositions[4];
//        batGreenY = batPositions[5];
        try {
            sides = hockeyField.getSides();
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Side side : sides) {
            gc.setLineWidth(1);
            if (Color.RED.toString().equals(side.getColor().toString())) {
                gc.setStroke(Color.RED);
                gc.setFill(Color.RED);
            }

            if (Color.BLUE.toString().equals(side.getColor().toString())) {
                gc.setStroke(Color.BLUE);
                gc.setFill(Color.BLUE);
            }

            if (Color.GREEN.toString().equals(side.getColor().toString())) {
                gc.setFill(Color.GREEN);
                gc.setStroke(Color.GREEN);
            }

            gc.strokeLine(side.getLineX1(), side.getLineY1(), side.getLineX2(), side.getLineY2());
            gc.setLineWidth(5);
            gc.strokeLine(side.getGoalX1(), side.getGoalY1(), side.getGoalX2(), side.getGoalY2());

            Bat bat = side.getBat();
            if (side.getSideName().equals(SideName.BOTTOM)) {
                gc.drawImage(batBlue, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore1.setText(String.valueOf(side.getBoundPlayer().getInGameScore()));
            } else if (side.getSideName().equals(SideName.RIGHT)) {
                gc.drawImage(batGreen, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore2.setText(String.valueOf(side.getBoundPlayer().getInGameScore()));
            } else if (side.getSideName().equals(SideName.LEFT)) {
                gc.drawImage(batRed, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore3.setText(String.valueOf(side.getBoundPlayer().getInGameScore()));
            }

            try {
                lblRoundNr.setText(String.valueOf(hockeyField.getRound()));
            } catch (RemoteException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Point2D position = null;
        try {
            position = new Point2D(hockeyField.getPuckPosition()[0], hockeyField.getPuckPosition()[1]);
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gc.setFill(Color.BLACK);
        gc.fillOval(position.getX() - diameterPuck / 2, position.getY() - diameterPuck / 2, diameterPuck, diameterPuck);

//            if (hockeyField.getMode().equals(Mode.SINGLE)) {
//                hockeyField.moveAIPlayers();
//            }
    }

    @FXML
    private void keyEventPressed(KeyEvent evt) {
        if (evt.getCode().equals(KeyCode.LEFT) || evt.getCode().equals(KeyCode.RIGHT)) {
            try {
                hockeyField.setPlayerBatPosition(evt.getCode().toString(), loggedInUser);
            } catch (RemoteException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //andere keys werken alleen als de textbox geslecteerd is.
    }

    @FXML
    private void btnSend_Click(ActionEvent evt) {
        sendMessage();
    }

    @FXML
    private void enterPressed(KeyEvent evt) {
        if (evt.getCode().equals(KeyCode.ENTER) && tfMessage.isFocused()) {
            lvChat.setFocusTraversable(true);
            tfMessage.setFocusTraversable(false);
            lvChat.requestFocus();
            sendMessage();
        }
    }

    private void sendMessage() {
        //van wie komt het bericht.. voorbeeldbericht: Eric: Hallo!
        String message = tfMessage.getText();
        if (!message.isEmpty() && message.trim().length() > 0) {
            Message m = new Message(loggedInUser, message);
            try {
                rmiController.getActiveGame().addMessage(m);
            } catch (RemoteException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!message.isEmpty() && message.trim().length() > 0) {
                messages.add(message);
                lvChat.scrollTo(lvChat.getItems().size());
                tfMessage.clear();
            }
        }
    }

    @FXML
    private void btnExit_Click(ActionEvent evt) {
        timer.stop();
        Parent root = null;
        try {
            Stage stage = new Stage();
            if (hockeyField.getMode().equals(Mode.SINGLE)) {
                root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                stage.setTitle("Airhockey - Menu");
            } else if (hockeyField.getMode().equals(Mode.MULTI)) {
                root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
                stage.setTitle("Airhockey - Mulitplayer");
            }

            hockeyField = null;

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            ((Node) (evt.getSource())).getScene().getWindow().hide();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setMode(Mode mode, String loggedInUser, Game g) {
        this.mode = mode;
        if (mode.equals(Mode.MULTI)) {
            try {
                rmiController = new GameRMI();
            } catch (RemoteException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        hockeyField = rmiController.getHockeyField();
        try {
            hockeyField.setBindedPlayers(g);
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loggedInUser = loggedInUser;
        try {
            diameterPuck = rmiController.getHockeyField().getDiameter();
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            IActiveGame game = rmiController.getActiveGame();
            game.addListenerO(this, "Client");
            IChat chat = game.getChat();
            chat.addListenerO(this, "Game");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    private void setVisibilityWaitingScreen() {
        canvas.setVisible(true);
        lblWaiting.setVisible(false);
        btnStart.setVisible(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        if (loggedInUser.equals(evt.getNewValue())) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    btnStart.setDisable(false);
                }
            });
        }
        if (evt.getPropertyName().equals("Game")) {
            messages.add((evt.getNewValue().toString()));
        }
        if (evt.getPropertyName().equals("gameOver")) {
            System.out.println("End game!");
            endGame();
        }
        if (evt.getPropertyName().equals("Client")) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    setVisibilityWaitingScreen();
                    startGame();
                }
            });

        }
    }

    public void endGame() {
        timer.stop();
        try {
            publisher.removeListener(this, "gameOver");
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                Parent root = null;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameResults.fxml"));
                    root = (Parent) fxmlLoader.load();
                    GameResultsController controller = fxmlLoader.<GameResultsController>getController();
                    controller.setResults(hockeyField.getGameResults());

                    Stage stage = new Stage();

                    stage.setTitle("Airhockey - Game Results");

                    hockeyField = null;

                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    btnSend.getScene().getWindow().hide();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

    }
}
