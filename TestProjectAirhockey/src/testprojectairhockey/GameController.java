/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

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
import Shared.IHockeyField;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 *
 * @author Eric
 */
public class GameController implements Initializable {

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

    //De afbeeldingen van de bats
    Image batRed = new Image("/testprojectairhockey/batred2.png");
    Image batBlue = new Image("/testprojectairhockey/batblue2.png");
    Image batGreen = new Image("/testprojectairhockey/batgreen2.png");

    GameRMI rmiController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Focus op het spel zodat je meteen de bat kan bewegen
        lvChat.setItems(messages);
        lvChat.setFocusTraversable(true);
        tfMessage.setFocusTraversable(false);

        gc = canvas.getGraphicsContext2D();
    }

    public void startGame() {
        //canvas.getTransforms().add(new Rotate(120, 280, 323));
        try {
            //Labels vullen met de namen van de spelers
            Side[] sides = hockeyField.getSides();
            for (Side side : sides) {
                if (side.getSideName().equals(SideName.BOTTOM)) {
                    lblPlayer1.setText(side.getBindedPlayer().toString());
                }
                if (side.getSideName().equals(SideName.RIGHT)) {
                    lblPlayer2.setText(side.getBindedPlayer().toString());
                }
                if (side.getSideName().equals(SideName.LEFT)) {
                    lblPlayer3.setText(side.getBindedPlayer().toString());
                }
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
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Draw() {
        Side[] sides = null;
        try {
            sides = hockeyField.getSides();
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        gc.setLineWidth(1);

        //hulplijnen om te kijken of de puck en de bats in het midden staan.
        //gc.strokeLine(sides[0].getLineX1(), sides[0].getLineY1(), sides[0].getLineX1(), sides[0].getLineY2());
        //gc.strokeLine(sides[1].getLineX1(), sides[1].getLineY1(), (sides[2].getLineX2() + sides[2].getLineX1()) / 2, (sides[2].getLineY2() + sides[2].getLineY1()) / 2);
        //gc.strokeLine(sides[2].getLineX1(), sides[2].getLineY1(), (sides[0].getLineX2() + sides[0].getLineX1()) / 2, (sides[0].getLineY2() + sides[0].getLineY1()) / 2);
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
                gc.drawImage(batRed, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore1.setText(String.valueOf(side.getBindedPlayer().getInGameScore()));
            } else if (side.getSideName().equals(SideName.RIGHT)) {
                gc.drawImage(batGreen, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore2.setText(String.valueOf(side.getBindedPlayer().getInGameScore()));
            } else if (side.getSideName().equals(SideName.LEFT)) {
                gc.drawImage(batBlue, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore3.setText(String.valueOf(side.getBindedPlayer().getInGameScore()));
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
        try {
            gc.fillOval(position.getX() - hockeyField.getDiameter() / 2, position.getY() - hockeyField.getDiameter() / 2, hockeyField.getDiameter(), hockeyField.getDiameter());

//            if (hockeyField.getMode().equals(Mode.SINGLE)) {
//                hockeyField.moveAIPlayers();
//            }
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            messages.add(message);
            lvChat.scrollTo(lvChat.getItems().size());
            tfMessage.clear();
        }
    }

    @FXML
    private void btnExit_Click(ActionEvent evt) {
        timer.stop();
        Parent root = null;
        try {
            Stage stage = new Stage();
//            if (hockeyField.getMode().equals(Mode.SINGLE)) {
//                root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
//                stage.setTitle("Airhockey - Menu");
//            } else if (hockeyField.getMode().equals(Mode.MULTI)) {
//                root = FXMLLoader.load(getClass().getResource("Lobby.fxml"));
//                stage.setTitle("Airhockey - Mulitplayer");
//            }

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

    public void setMode(Mode mode, String loggedInUser) {
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
            rmiController.getActiveGame().startGame();
        } catch (RemoteException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.loggedInUser = loggedInUser;
        startGame();
    }

}
