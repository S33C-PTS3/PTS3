/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Game.Mode;
import Game.Spectator;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import observer.RemotePropertyListener;
import observer.RemotePublisher;

/**
 * FXML Controller class
 *
 * @author rens
 */
public class SpectatorOverviewController implements Initializable, RemotePropertyListener {

    @FXML
    Button btnSpecGame1;

    @FXML
    Button btnSpecGame2;
    @FXML
    Button btnSpecGame3;
    @FXML
    Button btnSpecGame4;

    @FXML
    ImageView imgGame1;
    @FXML
    ImageView imgGame2;
    @FXML
    ImageView imgGame3;
    @FXML
    ImageView imgGame4;

    private Spectator spectator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        Image imgGame = new Image(getClass().getResourceAsStream("fieldPicture.png"));

        imgGame1.setImage(imgGame);
        imgGame2.setImage(imgGame);
        imgGame3.setImage(imgGame);
        imgGame4.setImage(imgGame);
        
        imgGame1.setVisible(false);
        imgGame2.setVisible(false);
        imgGame3.setVisible(false);
        imgGame4.setVisible(false);
    }

    @FXML
    private void Game1_Click(MouseEvent evt)
    {
        System.out.println("Nr of games: " + spectator.getGames().size());
        navigateToGame(0);
    }

    public void setSpectator(Spectator spectator)
    {
        this.spectator = spectator;
        try
        {
            this.spectator.addListener(this, "SpectatorGames");
        }
        catch (RemoteException ex)
        {
            System.out.println("Spectator games listener: " + ex.getMessage());
        }
    }

    public void navigateToGame(int gameindex)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            GameController controller = fxmlLoader.<GameController>getController();

            controller.setMode(Mode.MULTI, spectator.getUsername(), spectator.getGames().get(gameindex), Mode.SPECTATOR);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Airhockey - Multiplayer");
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException ex)
        {
            System.out.println("Error navigateToGame: " + ex.getMessage());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException
    {
        if (spectator.getGames().get(0) == null)
        {
            imgGame1.setVisible(false);
        }
        else if (spectator.getGames().get(1) == null)
        {
            imgGame2.setVisible(false);
        }
        else if (spectator.getGames().get(2) == null)
        {
            imgGame3.setVisible(false);
        }
        else if (spectator.getGames().get(3) == null)
        {
            imgGame4.setVisible(false);
        }
        
        if (spectator.getGames().get(0) != null)
        {
            imgGame1.setVisible(true);
        }
        else if (spectator.getGames().get(1) != null)
        {
            imgGame2.setVisible(true);
        }
        else if (spectator.getGames().get(2) != null)
        {
            imgGame3.setVisible(true);
        }
        else if (spectator.getGames().get(3) != null)
        {
            imgGame4.setVisible(true);
        }
    }

}
