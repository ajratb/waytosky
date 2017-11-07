/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.files;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ayrat
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Stage stage =(Stage)((Button)event.getSource()).getScene().getWindow();
        
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
        FileChooser fChooser=new FileChooser();
        File f = fChooser.showOpenDialog(stage);
        System.out.println(f.length());
        String ext = FilesUtils.getFileExt(f);
        System.out.println(ext);
        System.out.println(FilesUtils.checkFileExt(ext));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
