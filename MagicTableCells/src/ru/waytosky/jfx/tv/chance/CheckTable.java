/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.jfx.tv.chance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author Ayrat
 */
public class CheckTable extends AnchorPane {

    @FXML
    private TableView<PersonWrapper> tvCheck;

    @FXML
    private TableColumn<PersonWrapper, Boolean> clmFav;

    @FXML
    private TableColumn<PersonWrapper, String> clmId;

    private final ObservableList<Person> items = FXCollections.observableArrayList(
            new Person(0),
            new Person(99),
            new Person(2),
            new Person(7),
            new Person(4)
    );

    Properties prop = new Properties();
    ObservableList<String> favs = FXCollections.observableArrayList();

    public CheckTable() {
        try {
            FXMLLoader loaderFx = new FXMLLoader(getClass().getResource("check_table.fxml"));
            loaderFx.setRoot(this);
            loaderFx.setController(this);

            loaderFx.load();
        } catch (IOException ex) {
            Logger.getLogger(CheckTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (InputStream input = getClass().getResourceAsStream("app.properties")) {
            Pattern p = Pattern.compile("\\d+;");
            prop.load(input);
            String source = prop.getProperty("favs");
            Matcher m = p.matcher(source);
            int count = 0;
            while (m.find()) {
                count++;
                System.out.println("Match number " + count);
                System.out.println("start(): " + m.start());
                System.out.println("end(): " + m.end());
                favs.add(source.substring(m.start(), m.end()-1));
            }
        } catch (IOException ioEx) {

        }
        
        favs.forEach(System.out::println);

        ObservableList<PersonWrapper> wrappers = FXCollections.observableArrayList();
        items.forEach(ps -> {
            if (ps.getId() > 1) {
                wrappers.add(new PersonWrapper(new SimpleBooleanProperty(false), ps));
            } else {
                wrappers.add(new PersonWrapper(new SimpleBooleanProperty(true), ps));
            }
        });
        tvCheck.setItems(wrappers);

        clmId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getPerson().getId())));
//        clmFav.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(String.valueOf(cellData.getValue().getId())));
        clmFav.setCellValueFactory(cellData -> cellData.getValue().isFav());
        clmFav.setCellFactory(new MagicCellFactory());
    }

    private class PersonWrapper {

        BooleanProperty fav;
        Person person;

        public PersonWrapper(BooleanProperty fav, Person person) {
            this.fav = fav;
            this.person = person;
        }

        public Person getPerson() {
            return person;
        }

        public BooleanProperty isFav() {
            return fav;
        }

    }

    public class MagicCellFactory
            implements Callback<TableColumn<PersonWrapper, Boolean>, TableCell<PersonWrapper, Boolean>> {

        @Override
        public TableCell call(TableColumn p) {

            CheckBoxTableCell<PersonWrapper, Boolean> cell = new CheckBoxTableCell<PersonWrapper, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        System.out.println("inside updateItem: " + item + " index is: " + getIndex());
                        System.out.println(getTableView().getItems().get(getIndex()).getPerson().getId());
                    }
                }

            };

            return cell;
        }

    }

}
