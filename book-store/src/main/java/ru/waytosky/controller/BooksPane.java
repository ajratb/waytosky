package ru.waytosky.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.control.textfield.CustomTextField;
import ru.waytosky.entity.Book;
import ru.waytosky.model.BooksService;

import java.net.URL;
import java.util.ResourceBundle;

public class BooksPane implements Initializable {

    @FXML
    private CustomTextField txtSearchBook;

    @FXML
    private TableView<Book> tvBooks;

    @FXML
    private TableColumn<Book, String> clmISBN;

    @FXML
    void onActionBtnColorScheme(ActionEvent event) {
        System.out.println("hello world");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clmISBN.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().isbn()));

        BooksService service  = new BooksService();
        tvBooks.itemsProperty().bind(service.valueProperty());
        service.start();

        handleOnKeyReleaseTxtSearchBook();
    }

    private void handleOnKeyReleaseTxtSearchBook(){
        txtSearchBook.setOnKeyReleased(event -> {
            if(event.getCode().isDigitKey()) {
                System.out.println("Digit: " + event.getCode());
            }
            else if(event.getCode().isLetterKey()) {
                System.out.println("Letter: "+event.getCode());
            }

            ((FilteredList<Book>)tvBooks.getItems()).setPredicate(
                    book -> book.isbn().contains(txtSearchBook.getText())
            );

        });
    }
}
