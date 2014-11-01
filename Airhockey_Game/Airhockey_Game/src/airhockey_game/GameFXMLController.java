/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JFrame;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class GameFXMLController implements Initializable {

    private JFrame frame;
    MySketch applet;
    private final NavigationManager navMan = new NavigationManager();
    public Button btnExit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        frame = null;

        //Embed MySketch
        frame = new JFrame("Embedded MySketch PApplet");
        frame.setAlwaysOnTop(true);

        applet = new MySketch();

        applet.init();
        frame.add(applet);
        int frameWidth = (int) Math.round(800 * 0.7);
        int frameHeight = (int) Math.round(700 * 0.7);
        frame.setSize(frameWidth, frameHeight);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Rectangle r = frame.getBounds();

        frame.setLocation((int) Math.round((screenSize.getWidth() / 2) - (r.getWidth() / 2)), (int) Math.round((screenSize.getHeight() / 2) - (r.getHeight() / 2)) + 30);

        Color bg = new Color(235, 235, 235);
        frame.setBackground(bg);

        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    /**
     * Check if the user wants to go to the main menu, if yes go there, if no do
     * nothing.
     *
     * @param event
     */
    @FXML
    private void handleBtnExitEvent(ActionEvent event) {
        frame.dispose();
        applet.dispose();
        navMan.goTo("IT1MainMenu", event);

    }

    /**
     * Make the exit button more visible
     *
     * @param event
     */
    @FXML
    public void handleBtnExitMouseEnterEvent(MouseEvent event) {
        btnExit.setText("Exit game");
        btnExit.setOpacity(1);
    }

    /**
     * Make the exit button discrete
     *
     * @param event
     */
    @FXML
    public void handleBtnExitMouseExitEvent(MouseEvent event) {
        btnExit.setText("x");
        btnExit.setOpacity(0.3);
    }
}
