/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import javafx.scene.shape.*;

/**
 *
 * @author Eric
 */
public class DrawShapes {

    public DrawShapes()
    {
        Path path = new Path();
        path.getElements().add(new MoveTo(0.0f, 50.0f));
        path.getElements().add(new LineTo(100.0f, 100.0f));
    }

    

}
