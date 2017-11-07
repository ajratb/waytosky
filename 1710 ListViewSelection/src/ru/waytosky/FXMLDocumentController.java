/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ayrat
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private ListView<String> listView;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listView.getItems().add("sdfafasd");
        listView.getItems().add("eeeeeee");
        listView.getItems().add("sssssssssss");
        listView.getItems().add("fffffffff");

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println("clicked on ");// + listView.getSelectionModel().getSelectedItem());
                listView.getSelectionModel().select(1);
            }
        });
        
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
            if(newVal!=null){
                System.out.println("inside selected property change listener");
            }
        });
    }

}
