/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.ru.chance;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 *
 * @author Ayrat
 */
public class FXMLDocumentController implements Initializable {


    @FXML
    private ListView<Move> listView;


    private List<Move> data = new ArrayList<>();

    @FXML
    private void handleButtonAction(ActionEvent event) {
        data.forEach(System.out::println);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        data.add(new Move(0, 5, "Max"));
        data.add(new Move(1, 1, "Vera"));
        data.add(new Move(3, 0, "Adam"));
        data.add(new Move(2, "Yandex"));
        data.add(new Move(7, 3, "M56"));
        data.add(new Move(6, 0, "xe4"));
        data.add(new Move(5, "rrmmx"));

//        Comparator<Move> c1 = new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//
//                if (((Move) o1).getOrder() > ((Move) o2).getOrder()) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        };

//        data.sort(c1);
        data.sort(Comparator.comparing(Move::getOrder));

            listView.setItems(FXCollections.observableArrayList(data));

    }

    private class Move {

        int id;
        int order;
        String toWhom;

        public Move(int id, int order, String toWhom) {
            this.id = id;
            this.order = order;
            this.toWhom = toWhom;

        }

        public Move(int id, String toWhom) {
            this.id = id;
            this.toWhom = toWhom;
        }

        public int getId() {
            return id;
        }

        public int getOrder() {
            return order;
        }

        public String getToWhom() {
            return toWhom;
        }

        @Override
        public String toString() {
            return "Move{" + "id=" + id + ", order=" + order + ", toWhom=" + toWhom + '}';
        }

    }
}
