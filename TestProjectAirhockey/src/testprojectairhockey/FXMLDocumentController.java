/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import testprojectairhockey.domain.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import testprojectairhockey.domain.HockeyField;

/**
 *
 * @author Eric
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText("hallo");
        lvChat.setItems(messages);
        lvChat.setFocusTraversable(true);
        tfMessage.setFocusTraversable(false);
        

        gc = canvas.getGraphicsContext2D();
        hockeyField = new HockeyField();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                hockeyField.getPuck().move();
                hockeyField.checkColl();
                Draw();
            }

        }.start();

    }

    public void Draw() {
        Side[] sides = hockeyField.getSides();
        gc.setLineWidth(1);
        gc.strokeLine(sides[0].getLineX1(), sides[0].getLineY1(), sides[0].getLineX1(), sides[0].getLineY2());
        //gc.strokeLine(sides[1].getLineX1(), sides[1].getLineY1(), (sides[2].getLineX2() + sides[2].getLineX1()) / 2, (sides[2].getLineY2() + sides[2].getLineY1()) / 2);
        //gc.strokeLine(sides[2].getLineX1(), sides[2].getLineY1(), (sides[0].getLineX2() + sides[0].getLineX1()) / 2, (sides[0].getLineY2() + sides[0].getLineY1()) / 2);
        for (Side side : sides) {

            gc.setLineWidth(1);
            gc.setStroke(side.getColor());
            gc.setFill(side.getColor());
            gc.strokeLine(side.getLineX1(), side.getLineY1(), side.getLineX2(), side.getLineY2());
            
            gc.setLineWidth(5);
            gc.strokeLine(side.getGoalX1(), side.getGoalY1(), side.getGoalX2(), side.getGoalY2());

            Bat bat = side.getBat();
            if (side.getSideName().equals(SideName.BOTTOM)) {
                gc.fillOval(bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
            } else if (side.getSideName().equals(SideName.LEFT)) {
                gc.fillOval(bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
            } else if (side.getSideName().equals(SideName.RIGHT)) {
                gc.fillOval(bat.getXpos() - bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
            }

            Puck puck = hockeyField.getPuck();
            gc.fillOval(puck.getXpos() - puck.getRadius(), puck.getYpos() - puck.getRadius(), puck.getDiameter(), puck.getDiameter());
            
            hockeyField.moveAIPlayers();
        }
    }
    
    @FXML
    private void keyEventPressed(KeyEvent evt)
    {
        hockeyField.movePlayerBat(evt.getCode());
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
