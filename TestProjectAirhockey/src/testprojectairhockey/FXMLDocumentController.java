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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
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

    GraphicsContext gc;
    HockeyField hockeyField;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO annimation timer
        label.setText("hallo");

        gc = canvas.getGraphicsContext2D();
        hockeyField = new HockeyField();
        

        new AnimationTimer() {
            @Override
            public void handle(long now)
            {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                hockeyField.getPuck().move();
                Draw();
            }

        }.start();

    }

    public void Draw()
    {
        Side[] sides = hockeyField.getSides();
        //gc.strokeLine(sides[0].getLineX1(), sides[0].getLineY1(), sides[0].getLineX1(), sides[0].getLineY2());
        //gc.strokeLine(sides[1].getLineX1(), sides[1].getLineY1(), (sides[2].getLineX2() + sides[2].getLineX1()) / 2, (sides[2].getLineY2() + sides[2].getLineY1()) / 2);
        //gc.strokeLine(sides[2].getLineX1(), sides[2].getLineY1(), (sides[0].getLineX2() + sides[0].getLineX1()) / 2, (sides[0].getLineY2() + sides[0].getLineY1()) / 2);
        for (Side side : sides)
        {
            gc.setStroke(side.getColor());
            gc.setFill(side.getColor());
            gc.strokeLine(side.getLineX1(), side.getLineY1(), side.getLineX2(), side.getLineY2());

            Bat bat = side.getBat();
            if (side.getSideName().equals(SideName.BOTTOM))
            {
                gc.fillOval(bat.getXpos() + bat.getRadius(), bat.getYpos() - bat.getRadius(), bat.getDiameter(), bat.getDiameter());
            }
            else if (side.getSideName().equals(SideName.LEFT))
            {
                gc.fillOval(bat.getXpos() - bat.getDiameter(), bat.getYpos() + bat.getRadius(), bat.getDiameter(), bat.getDiameter());
            }
            else if (side.getSideName().equals(SideName.RIGHT))
            {
                gc.fillOval(bat.getXpos(), bat.getYpos() + bat.getRadius(), bat.getDiameter(), bat.getDiameter());
            }

            Puck puck = hockeyField.getPuck();
            //System.out.println("x: " + puck.getXpos() + " y: " + puck.getYpos());
            gc.fillOval(puck.getXpos() - puck.getRadius(), puck.getYpos() - puck.getRadius(), puck.getDiameter(), puck.getDiameter());
        }
    }

}
