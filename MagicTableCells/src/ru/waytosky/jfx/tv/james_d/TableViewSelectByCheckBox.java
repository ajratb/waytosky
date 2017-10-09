/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.jfx.tv.james_d;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener.Change;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TableViewSelectByCheckBox extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView<String> table = createTable();

        TableColumn<String, String> selectedCol = new TableColumn<>();
        selectedCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));

        Set<String> buf= new HashSet<>();
        buf.add("Item 1");
        ObservableSet<String> selectedItems = FXCollections.observableSet(buf);
        selectedItems.add("Item 2");
        selectedCol.setCellFactory(col -> {

            // boolean property represents if the check box in the cell is selected:
            BooleanProperty selected = new SimpleBooleanProperty();
            // create check box cell and bind its state to property:
            CheckBoxTableCell<String, String> cell = new CheckBoxTableCell<>(index -> selected);

            // update set of selected indices if checkbox state changes:
            selected.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    selectedItems.add(cell.getItem());
                } else {
                    selectedItems.remove(cell.getItem());
                }
            });

            // update check box state if set of selected indices changes:
            selectedItems.addListener((Change<? extends String> change) -> {
                selected.set(cell.getItem() != null && selectedItems.contains(cell.getItem()));
            });

            // update check box when cell is reused for a different index:
            cell.itemProperty().addListener((obs, oldItem, newItem) -> {
                selected.set(newItem != null && selectedItems.contains(newItem));
            });

            return cell ;
        });

        table.getColumns().add(0, selectedCol);

        Button button = new Button("Show selected");
        button.setOnAction(e -> selectedItems.forEach(System.out::println));

        BorderPane.setAlignment(button, Pos.CENTER);
        BorderPane.setMargin(button, new Insets(5));


        BorderPane root = new BorderPane(table, null, null, button, null);
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableView<String> createTable() {
        TableView<String> table = new TableView<>();
        table.setEditable(true);
        TableColumn<String, String> itemCol = new TableColumn<>("Item");
        itemCol.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));
        table.getColumns().add(itemCol);
        for (int item = 1 ; item <= 100; item++) {
            table.getItems().add("Item "+item);
        }
        return table ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}