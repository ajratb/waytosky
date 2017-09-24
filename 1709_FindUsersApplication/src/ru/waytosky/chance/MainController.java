/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.chance;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ayrat
 */
public class MainController implements Initializable {
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       System.out.println("You clicked me!");
       UsersSearchWindow frame = new UsersSearchWindow();
       Scene scene = new Scene(frame);
       Stage stage = new Stage();
       stage.setScene(scene);
       stage.initModality(Modality.APPLICATION_MODAL); 
       stage.initStyle(StageStyle.UNDECORATED);
       stage.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
