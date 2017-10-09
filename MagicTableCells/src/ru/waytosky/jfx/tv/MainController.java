/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.jfx.tv;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tab;
import ru.waytosky.jfx.tv.chance.CheckTable;

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
    private TableColumn<MagicThing, Boolean> clmFav;

    @FXML
    private TableColumn<MagicThing, String> clmStatus;

    @FXML
    private TableColumn<MagicThing, String> clmSendDate;

    @FXML
    private TableColumn<MagicThing, String> clmBackDate;
    
        @FXML
    private Tab tabCheckTable;

    ObservableList<MagicThing> dataList
            = FXCollections.observableArrayList(
                    new MagicThing(true, 0, null, null),
                    new MagicThing(false, 2, new Date(), new Date()),
                    new MagicThing(true, 4, new Date(), new Date()),
                    new MagicThing(false, 4, new Date(), new Date()),
                    new MagicThing(true, 3, new Date(), new Date()));

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clmFav.setCellValueFactory(
                cell->cell.getValue().getFavorite());


        MagicClickCellFactory cellFactory = new MagicClickCellFactory();

        clmFav.setCellFactory(cellFactory);
       
       tvMagic.setItems(dataList);
       
       tabCheckTable.setContent(new CheckTable());

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        tvMagic.getItems().forEach(System.out::println);

    }
}
