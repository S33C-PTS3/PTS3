/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airhockey_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import processing.core.PVector;

/**
 * FXML Controller class
 *
 * @author Joep Kerste
 */
public class GameFXMLController implements Initializable {

    private JFrame frame;
    JPanel panel;
    MySketch applet;
    private final NavigationManager navMan = new NavigationManager();
    public Button btnExit;
    public Rectangle recInfo;
    public Label lblSpeler1, lblSpeler2, lblSpeler3, lblSpeler1Score, lblSpeler2Score, lblSpeler3Score, lblRonde;
    private boolean infoIsVisible = false;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        frame = null;
        recInfo.setHeight(25);
        setInfoVisibility();

        //Embed MySketch
        frame = new JFrame("Embedded MySketch PApplet");
        frame.setAlwaysOnTop(true);
        applet = new MySketch();
        
        applet.init();

        frame.add(applet);
        int frameWidth = (int)Math.round(820 * 0.7);
        int frameHeight = (int)Math.round(720 * 0.7);
        frame.setSize(frameWidth, frameHeight);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        java.awt.Rectangle r = frame.getBounds();
        
        frame.setLocation((int)Math.round((screenSize.getWidth()/2)-(r.getWidth()/2)),(int)Math.round((screenSize.getHeight()/2)-(r.getHeight()/2))+50);
        
        
        Color bg = new Color(235,235,235);
        frame.setBackground(bg);

        frame.setUndecorated(true);

        //applet.setLayout(null);
        applet.setLocation(50,50);
        frame.setVisible(true);
    }    
    
    /**
     * Check if the user wants to go to the main menu, if yes go there, if no do nothing.
     * @param event 
     */
    @FXML
    private void handleBtnExitEvent(ActionEvent event)
    {
        frame.dispose();
        applet.dispose();
        navMan.goTo("IT1MainMenu", event);
    }
    
    /**
     * Make the exit button more visible
     * @param event 
     */
    @FXML
    public void handleBtnExitMouseEnterEvent(MouseEvent event)
    {
        btnExit.setText("Exit game");
        btnExit.setOpacity(1);
    }
    
    /**
     * Make the exit button discrete
     * @param event 
     */
    @FXML
    public void handleBtnExitMouseExitEvent(MouseEvent event)
    {
        btnExit.setText("x");
        btnExit.setOpacity(0.3);
    }
    
    /**
     * Make the game info rectangle large and show the info.
     * @param event 
     */
    @FXML
    public void handleRecInfoMouseEnterEvent(MouseEvent event)
    {
        recInfo.setHeight(130);
        changeInfoState();
    }
    
    /**
     * Make the game info rectangle small and hide the info.
     * @param event 
     */
    @FXML
    public void handleRecInfoMouseExitEvent(MouseEvent event)
    {
        recInfo.setHeight(25);
        changeInfoState();
    }
    
    /**
     * Switch the state of the boolean infoIsVisible, and call setInfoVisibility() to set the new state of the info labels.
     */
    private void changeInfoState()
    {
        infoIsVisible = !infoIsVisible;
        setInfoVisibility();
    }
    
    /**
     * If the info should be visible the labels will be all set to visible and the info will be updated.
     * If else the labels will be all set to invisible.
     */
    private void setInfoVisibility()
    {
        if (infoIsVisible) {
            updateGameInfo();
            lblSpeler1.setVisible(true);
            lblSpeler2.setVisible(true);
            lblSpeler3.setVisible(true);
            lblSpeler1Score.setVisible(true);
            lblSpeler2Score.setVisible(true);
            lblSpeler3Score.setVisible(true);            
            lblRonde.setVisible(true);
        }
        else
        {
            lblSpeler1.setVisible(false);
            lblSpeler2.setVisible(false);
            lblSpeler3.setVisible(false);
            lblSpeler1Score.setVisible(false);
            lblSpeler2Score.setVisible(false);
            lblSpeler3Score.setVisible(false);            
            lblRonde.setVisible(false);
        }
    }
    
    /**
     * Game info (Player names, scores, round number) are updated.
     */
    private void updateGameInfo()
    {
        //TODO: GAMEINFO UPDATEN
    }
}
