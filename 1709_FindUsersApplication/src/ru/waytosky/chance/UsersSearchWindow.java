/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.chance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author Ayrat
 */
public class UsersSearchWindow extends BorderPane {

    @FXML
    private TableView<UserLoginInfo> tvEmployees;

    @FXML
    private TableColumn<UserLoginInfo, String> clmEmpName;

    @FXML
    private TableColumn<UserLoginInfo, String> clmEmpDep;

    @FXML
    private TableColumn<UserLoginInfo, String> clmEmpPos;

    @FXML
    private TableView<UserLoginInfo> tvSelected;

    @FXML
    private TableColumn<UserLoginInfo, String> clmSelName;

    @FXML
    private TableColumn<UserLoginInfo, String> clmSelPos;

    @FXML
    private TextField txtSearch;
//    private UserLoginInfo selectedUser;

    @FXML
    private Button btnUpSelected;

    @FXML
    private Button btnDownSelected;

    @FXML
    private Button btnRemoveSelected;

    public UsersSearchWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "users_search.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        clmEmpName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserLoginInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserLoginInfo, String> user) {

                return new ReadOnlyObjectWrapper(user.getValue().getFirstName());
            }
        });
        clmEmpDep.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserLoginInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserLoginInfo, String> user) {

                return new ReadOnlyObjectWrapper(user.getValue().getDepartment());
            }
        });
        clmEmpPos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserLoginInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserLoginInfo, String> user) {

                return new ReadOnlyObjectWrapper(user.getValue().getPosition());
            }
        });
        clmSelName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserLoginInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserLoginInfo, String> user) {

                return new ReadOnlyObjectWrapper(user.getValue().getFirstName());
            }
        });
        clmSelPos.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UserLoginInfo, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<UserLoginInfo, String> user) {

                return new ReadOnlyObjectWrapper(user.getValue().getPosition());
            }
        });

        tvEmployees.setPlaceholder(new Label("Введите значение в строку \"Поиск\""));
        tvSelected.setPlaceholder(new Label("(Пусто)"));

        tvEmployees.setRowFactory(tv -> {
            TableRow<UserLoginInfo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    UserLoginInfo rowData = row.getItem();
                    System.out.println(rowData);
                    if (tvSelected.getItems().stream().filter((p) -> rowData.getuId() == p.getuId()).count() == 0) {
                        tvSelected.getItems().add(rowData);
                    }
                }
            });
            return row;
        });

        tvSelected.setRowFactory(tv -> {
            TableRow<UserLoginInfo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    UserLoginInfo rowData = row.getItem();
                    tvSelected.getItems().remove(rowData);
                }
            });
            return row;
        });

        tvSelected.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserLoginInfo>() {
            @Override
            public void changed(ObservableValue<? extends UserLoginInfo> observable, UserLoginInfo oldValue, UserLoginInfo newValue) {
                if (newValue != null) {
                    btnDownSelected.setDisable(false);
                    btnUpSelected.setDisable(false);
                    btnRemoveSelected.setDisable(false);
                } else {
                    btnDownSelected.setDisable(true);
                    btnUpSelected.setDisable(true);
                    btnRemoveSelected.setDisable(true);
                }
            }
        });

        tvEmployees.setItems(getItemsPortion(0, 100));
    }

    private ObservableList<UserLoginInfo> getItemsPortion(int firstRow, int lastRow) {

        List<UserLoginInfo> result = new ArrayList<>();
        for (int i = firstRow; i < lastRow; i++) {
            result.add(new UserLoginInfo(i, "Имя" + i, "Фамилия" + i, "Отчество" + i, "Позиция" + i, "Отдел" + i));
        }
        return FXCollections.observableArrayList(result);
    }

    @FXML
    void onAction_btnSelect(ActionEvent event) {
        UserLoginInfo selectedUser = tvEmployees.getSelectionModel().getSelectedItem();
        if (tvSelected.getItems().stream().filter((p) -> selectedUser.getuId() == p.getuId()).count() == 0) {
            tvSelected.getItems().add(selectedUser);
        }
    }

    @FXML
    void onAction_btnRemoveSelected(ActionEvent event) {
        UserLoginInfo selectedUser = tvSelected.getSelectionModel().getSelectedItem();
        tvSelected.getItems().remove(selectedUser);
    }

    @FXML
    void onAction_btnDownSelected(ActionEvent event) {
        int current = tvSelected.getSelectionModel().getSelectedIndex();
        if (current < tvSelected.getItems().size()-1) {
            Collections.swap(tvSelected.getItems(), current, current + 1);
        }
    }

    @FXML
    void onAction_btnUpSelected(ActionEvent event) {
        int current = tvSelected.getSelectionModel().getSelectedIndex();
        if (current != 0) {
            Collections.swap(tvSelected.getItems(), current, current - 1);
        }
    }

    @FXML
    void onAction_btnOK(ActionEvent event) {
        tvSelected.getItems().forEach((p) -> {
            System.out.println(p.getuId());
        });
//        ((Stage) ((Hyperlink) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void onAction_EXIT(ActionEvent event) {
        ((Stage) ((Hyperlink) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void onKeyReleased_txtSearch(KeyEvent event) {

    }

    @FXML
    void onMouseClicked_tvEmployees(MouseEvent event) {

    }

    @FXML
    void onMouseClicked_tvSelected(MouseEvent event) {

    }

}
