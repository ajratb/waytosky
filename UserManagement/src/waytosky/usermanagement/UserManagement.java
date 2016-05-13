/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.usermanagement;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author ayrat
 */
public class UserManagement extends Application {

    public static Connection conn;

    @Override
    public void start(Stage stage) throws Exception {

        //Создаем подключение
        //если conn получить не удастся то выход из приложения
        String url = "jdbc:sqlserver://saprtp\\smarteamdb;databaseName=javadb";
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            conn = DriverManager.getConnection(url, "javadb",
                    "superstar");

            Parent root = FXMLLoader.load(getClass()
                    .getResource("usermanagement.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (SQLException sqlEx) {
            System.out.println("ex");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Вход в БД не выполнен");
            alert.setHeaderText("Не удалось установить связь с базой данных");

            alert.setContentText(sqlEx.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
