/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.chance;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Ayrat
 */
public class MainController implements Initializable {

    @FXML
    private TableView<Movement> tvMoves;

    @FXML
    private TableColumn<Movement, String> clmState;

    @FXML
    private TableColumn<Movement, String> clmDep;

    @FXML
    private TableColumn<Movement, String> clmSend;

    @FXML
    private TableColumn<Movement, String> clmReceive;

    @FXML
    private TableColumn<Movement, Boolean> clmScan;

    private ObservableList<Movement> moves = FXCollections.observableArrayList(
            new Movement("715", new Date(950_000_000L), new Date(9_000_000_000L), false),
            new Movement("031", DateUtils.getDateFromString("02.11.2016"), new Date(), false),
            new Movement("600", new Date(), new Date(), false),
            new Movement("015", new Date(), null, true),
            new Movement("112", new Date(), new Date(), false)
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println(new Date().getTime());
        moves.get(1).setEndDate(new Date());

        clmState.setCellValueFactory(row -> {
            Movement move = row.getValue();
            if (move.getPlannedDate() == null) {
                return new ReadOnlyObjectWrapper("Создано");
            } else if (move.getEndDate() != null) {
                return new ReadOnlyObjectWrapper("Получено");
            } else {
                return new ReadOnlyObjectWrapper("Отправлено");
            }
        });

        ContextMenu contextMenu = new ContextMenu();
        MenuItem newItem = new MenuItem("Call PopUp");
        newItem.setOnAction(event -> {
            int index = tvMoves.getSelectionModel().getSelectedIndex();
            tvMoves.getSelectionModel().getSelectedItem().setEndDate(new Date());
//                ObservableList<Movement> temp = FXCollections.observableArrayList(tvMoves.getItems());
            List<Movement> temp = tvMoves.getItems().stream().collect(Collectors.toList());
            tvMoves.getItems().clear();
            tvMoves.getItems().addAll(temp);
            tvMoves.getSelectionModel().select(index);
            System.out.println("pop up");
        }
        );
        contextMenu.getItems().addAll(newItem);

        clmState.setCellFactory(clm -> {
            TableCell cell = new TableCell<Movement, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        setText(item);
                    }
                }
            };
            cell.setOnMouseClicked(event -> {
                if ((cell.getItem()).equals("Отправлено")) {
                    contextMenu.show(cell,
                            event.getScreenX(), event.getScreenY());
                }
            });
            return cell;
        });

        clmDep.setCellValueFactory(row -> new ReadOnlyObjectWrapper(row.getValue().getDep()));
        clmDep.setStyle("-fx-alignment: CENTER;");

        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        clmSend.setCellValueFactory(row -> new ReadOnlyObjectWrapper(
                format.format(row.getValue().getStartDate())));
        clmReceive.setCellValueFactory(row -> {
            if (row.getValue().getPlannedDate() == null) {
                return new SimpleStringProperty("");
            } else {
                return new ReadOnlyObjectWrapper(
                        format.format(row.getValue().getPlannedDate()));
            }
        });

        clmScan.setCellValueFactory(row -> new SimpleBooleanProperty(row.getValue().isScan()));
        clmScan.setStyle("-fx-alignment: CENTER;");
        clmScan.setCellFactory(clm
                -> {
            TableCell cell = new TableCell<Movement, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);

                    } else if (item) {
                        setGraphic(new FontAwesomeIconView(FontAwesomeIcon.FILE_PDF_ALT));
//                            setText("some");
                    } else {
                        setGraphic(null);
                    }
                }
            };

            cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    TableCell c = (TableCell) event.getSource();
                    System.out.println(c.getItem());
                }
            });

            return cell;
        });

        tvMoves.setItems(moves);
    }

}
