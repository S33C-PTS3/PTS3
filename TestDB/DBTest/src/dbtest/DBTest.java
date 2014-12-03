/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author Joep Kerste
 */
public class DBTest extends Application {
    
    private Connection conn;
    StackPane root;
    ImageView imgView;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String result = getAdminPassword();
                if (result != null) 
                {
                    showFrans();
                }
                System.out.println("Admin password:" + result);
            }
        });
        
        root = new StackPane();
        root.getChildren().add(btn);
        
        Image frans = new Image("http://static0.ad.nl/static/photo/2012/8/11/2/20120427104017/media_xll_1191422.jpg");
        imgView = new ImageView(frans);      
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private String getAdminPassword()
    {
        try 
        {
            initConnection();
        } 
        catch (SQLException ex) 
        {
            System.out.println("DB init failed");
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Statement prepstat = null;
        String query = "SELECT * FROM AH_ACCOUNT WHERE USERNAME = 'admin'";
        String result = "";
        
        try 
        {
            prepstat = conn.createStatement();
            ResultSet rs = prepstat.executeQuery(query);
            while(rs.next())
            {
                result = rs.getString("PASSWORD");
            }
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
            Logger.getLogger(DBTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return result;
    }
    
    private void initConnection() throws SQLException {
        try
        {
            
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("JDBC Driver not found!");
        }
        
        try
        {
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "system", "qbNdsAWq123");
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@//127.0.0.1:1521/orcl", "system", "qbNdsAWq123");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//145.93.163.65:1521/orcl", "system", "qbNdsAWq123");
        }
        catch (SQLException ex)
        {
            System.out.println("Connection failed.");
            ex.printStackTrace();
        }        
        
        if (conn == null) {
            System.out.println("Connection could not be made.");
        }
        
    }
    
    private void showFrans()
    {
        root.getChildren().add(imgView);
    }
    
    private void hideFrans()
    {
        root.getChildren().remove(imgView);
    }    
}
