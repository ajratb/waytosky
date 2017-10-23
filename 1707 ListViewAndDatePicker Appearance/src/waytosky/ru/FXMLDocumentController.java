/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.ru;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;

/**
 *
 * @author Ayrat
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private DatePicker datePicker;

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
        datePicker.setValue(LocalDate.MAX);
//        listView.setMouseTransparent(true);
//        listView.setFocusTraversable(false);
        List<String> strings = new ArrayList<>();
        strings.add("eeeeeeeeeeeee");
        strings.add("gfhgfh");
        strings.add("eeeeeeeeeeeee");
        strings.add("eeeeeeeeeeeee");
        strings.add("hhhhhhhhhhhhhhhh");
        strings.add("zzzzzzzzzzzzzzzzz");
        strings.add("eeeeeeeeeeeee");
        strings.add("eeeeeeeeeeeee");

        listView.setItems(FXCollections.observableArrayList(strings));

//        listView.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println(">> Mouse Clicked: "+event.getSource());
//                
//                event.consume();
//            }
//        });
//            listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)->{
//                if(newVal!=null){
//                  
//                }
//            });
        listView.setSelectionModel(new NoSelectionModel<String>());
    }

    public class NoSelectionModel<T> extends MultipleSelectionModel<T> {

        @Override
        public ObservableList<Integer> getSelectedIndices() {
            return FXCollections.emptyObservableList();
        }

        @Override
        public ObservableList<T> getSelectedItems() {
            return FXCollections.emptyObservableList();
        }

        @Override
        public void selectIndices(int index, int... indices) {
        }

        @Override
        public void selectAll() {
        }

        @Override
        public void selectFirst() {
        }

        @Override
        public void selectLast() {
        }

        @Override
        public void clearAndSelect(int index) {
        }

        @Override
        public void select(int index) {
        }

        @Override
        public void select(T obj) {
        }

        @Override
        public void clearSelection(int index) {
        }

        @Override
        public void clearSelection() {
        }

        @Override
        public boolean isSelected(int index) {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public void selectPrevious() {
        }

        @Override
        public void selectNext() {
        }
    }
}
