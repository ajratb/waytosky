/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.jfx.tv;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ayrat
 */
public class MainController implements Initializable {
   
    @FXML
    private Button button;

    @FXML
    private TableView<MagicThing> tvMagic;

    @FXML
    private TableColumn clmFav;

    @FXML
    private TableColumn<MagicThing, String> clmStatus;

    @FXML
    private TableColumn<MagicThing, String> clmSendDate;

    @FXML
    private TableColumn<MagicThing, String> clmBackDate;

    ObservableList<MagicThing> dataList
                = FXCollections.observableArrayList(
                        new MagicThing(true,0, null, null),
                        new MagicThing(false,2, new Date(), new Date()),
                        new MagicThing(true,4, new Date(), new Date()),
                        new MagicThing(false,4, new Date(), new Date()),
                        new MagicThing(true,3, new Date(), new Date()));
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         clmFav.setCellValueFactory(
                new PropertyValueFactory<MagicThing, BooleanProperty>("favorite"));

        ImageClickCellFactory cellFactory
                = new ImageClickCellFactory(
                        new CreateFavoriteClickMouseEventHandler());

        clmFav.setCellFactory(cellFactory);
        tvMagic.setItems(dataList);
    }

// This class implement the mouse event handler
    private class CreateFavoriteClickMouseEventHandler
            implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 1) {

                try {
                    MagicThing magic = dataList.get(
                            ((TableCell) event.getSource()).getIndex());

                    if (magic.isFavorite()) {
                        magic.setFavorite(Boolean.FALSE);
                        System.out.println("magic get fav - false: " + magic.isFavorite());
                    } else {
                        magic.setFavorite(Boolean.TRUE);
                         System.out.println("magic get fav - true: " + magic.isFavorite());
                    }

                    // do something here...
                   
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }
     @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
}
