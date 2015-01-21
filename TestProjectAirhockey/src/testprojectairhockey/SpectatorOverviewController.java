/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprojectairhockey;

import Game.Mode;
import Game.Spectator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import observer.RemotePublisher;

/**
 * FXML Controller class
 *
 * @author rens
 */
public class SpectatorOverviewController implements Initializable {

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

        btnSpecGame1 = new Button("", imgGame1);
        btnSpecGame2 = new Button("", imgGame2);
        btnSpecGame3 = new Button("", imgGame3);
        btnSpecGame4 = new Button("", imgGame4);

        btnSpecGame1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event)
            {

            }
        });

    }

    public void setSpectator(Spectator spectator)
    {
        this.spectator = spectator;
    }

    public void navigateToGame(int gameindex)
    {
        try{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        GameController controller = fxmlLoader.<GameController>getController();

        controller.setMode(Mode.MULTI, spectator.getUsername(), spectator.getGames().get(gameindex));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Airhockey - Multiplayer");
        stage.setResizable(false);
        stage.show();
        }
        catch(IOException ex)
        {}
    }

}
