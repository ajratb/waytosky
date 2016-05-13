/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.usermanagement;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ayrat
 */
public class UserManagement extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //Создаем подключение
        DriverManager.registerDriver(new SQLServerDriver());
            Connection conn = DriverManager.getConnection(url, "", "");
        
        Parent root = FXMLLoader.load(getClass().getResource("usermanagement.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
