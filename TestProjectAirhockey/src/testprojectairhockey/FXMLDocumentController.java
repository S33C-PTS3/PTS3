/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import java.io.IOException;
import testprojectairhockey.domain.*;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
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
import javax.swing.text.ChangedCharSetException;
import testprojectairhockey.domain.HockeyField;

/**
 *
 * @author Eric
 */
public class FXMLDocumentController implements Initializable {

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

    ObservableList<String> messages = FXCollections.observableArrayList();

    GraphicsContext gc;
    HockeyField hockeyField;
    AnimationTimer timer;

    Image batRed = new Image("/testprojectairhockey/batred2.png");
    Image batBlue = new Image("/testprojectairhockey/batblue2.png");
    Image batGreen = new Image("/testprojectairhockey/batgreen2.png");

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        lvChat.setItems(messages);
        lvChat.setFocusTraversable(true);
        tfMessage.setFocusTraversable(false);

        gc = canvas.getGraphicsContext2D();
        hockeyField = new HockeyField();

        Side[] sides = hockeyField.getSides();
        for (Side side : sides)
        {
            if (side.getSideName().equals(SideName.BOTTOM))
            {
                lblPlayer1.setText(side.getBindedPlayer().toString());
            }
            if (side.getSideName().equals(SideName.RIGHT))
            {
                lblPlayer2.setText(side.getBindedPlayer().toString());
            }
            if (side.getSideName().equals(SideName.LEFT))
            {
                lblPlayer3.setText(side.getBindedPlayer().toString());
            }
        }

        timer = new AnimationTimer() {
            @Override
            public void handle(long now)
            {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                hockeyField.getPuck().move();
                hockeyField.checkColl();
                Draw();
                if (hockeyField.isGameOver() != null)
                {
                    this.stop();
                    try
                    {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameResults.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        GameResultsController controller = fxmlLoader.<GameResultsController>getController();
                        controller.setResults(hockeyField.isGameOver());
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Airhockey - Game Results");
                        stage.setResizable(false);
                        stage.show();
                        hockeyField = null;
                        Node node = (Node) btnSend;
                        node.getParent().getScene().getWindow().hide();
                    }
                    catch (IOException ex)
                    {
                        System.out.println(ex.getMessage());
                    }

                }
            }
        };
        timer.start();

    }

    public void Draw()
    {
        Side[] sides = hockeyField.getSides();
        gc.setLineWidth(1);
        //gc.strokeLine(sides[0].getLineX1(), sides[0].getLineY1(), sides[0].getLineX1(), sides[0].getLineY2());
        //gc.strokeLine(sides[1].getLineX1(), sides[1].getLineY1(), (sides[2].getLineX2() + sides[2].getLineX1()) / 2, (sides[2].getLineY2() + sides[2].getLineY1()) / 2);
        //gc.strokeLine(sides[2].getLineX1(), sides[2].getLineY1(), (sides[0].getLineX2() + sides[0].getLineX1()) / 2, (sides[0].getLineY2() + sides[0].getLineY1()) / 2);
        for (Side side : sides)
        {
            gc.setLineWidth(1);
            gc.setStroke(side.getColor());
            gc.setFill(side.getColor());
            gc.strokeLine(side.getLineX1(), side.getLineY1(), side.getLineX2(), side.getLineY2());

            gc.setLineWidth(5);
            gc.strokeLine(side.getGoalX1(), side.getGoalY1(), side.getGoalX2(), side.getGoalY2());

            Bat bat = side.getBat();
            if (side.getSideName().equals(SideName.BOTTOM))
            {
                //gc.fillOval(bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                gc.drawImage(batRed, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore1.setText(String.valueOf(side.getBindedPlayer().getInGameScore()));
            }
            else if (side.getSideName().equals(SideName.RIGHT))
            {
                //gc.fillOval(bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                gc.drawImage(batGreen, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore2.setText(String.valueOf(side.getBindedPlayer().getInGameScore()));
            }
            else if (side.getSideName().equals(SideName.LEFT))
            {
                //gc.fillOval(bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                gc.drawImage(batBlue, bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
                lblScore3.setText(String.valueOf(side.getBindedPlayer().getInGameScore()));
            }

            lblRoundNr.setText(String.valueOf(hockeyField.getRound()));

            Puck puck = hockeyField.getPuck();
            gc.fillOval(puck.getXpos() - puck.getRadius(), puck.getYpos() - puck.getRadius(), puck.getDiameter(), puck.getDiameter());

            hockeyField.moveAIPlayers();
        }
    }

    @FXML
    private void keyEventPressed(KeyEvent evt)
    {
        if (evt.getCode().equals(KeyCode.LEFT) || evt.getCode().equals(KeyCode.RIGHT))
        {
            hockeyField.movePlayerBat(evt.getCode());
        }
        
        //andere keys werken alleen als de textbox geslecteerd is.

    }
    
    @FXML void keyEventReleased(KeyEvent evt)
    {
        if(evt.getCode().equals(KeyCode.LEFT) || evt.getCode().equals(KeyCode.RIGHT))
        {
            hockeyField.stopPlayerBat();
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
            lvChat.setFocusTraversable(true);
            tfMessage.setFocusTraversable(false);
            lvChat.requestFocus();
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
            tfMessage.clear();
        }
    }

    @FXML
    private void btnExit_Click(ActionEvent evt)
    {
        timer.stop();
        hockeyField = null;
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Airhockey - Menu");
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
