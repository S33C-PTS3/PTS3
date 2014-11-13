/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import testprojectairhockey.domain.SideName;
import testprojectairhockey.domain.Side;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Eric
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML Canvas canvas;
    
    GraphicsContext gc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        label.setText("hallo");
        
        gc = canvas.getGraphicsContext2D();
        Draw();
        
    }    
    
    public void Draw()
    {
        //Side s = new Side(0, 0, 200, 300, Color.BLUE, SideName.LEFT);
        //gc.strokeLine(s.getLineX1(), s.getLineY1(), s.getLineX2(), s.getLineY2());
    }
    
}
