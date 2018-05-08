/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Ayrat
 */
public class BookStoreApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader  = new FXMLLoader(getClass().getResource("main.fxml"));
        
        GridPane root = new MainForm();
        
        loader.setRoot(root);
        loader.load();
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show(); 
//        ((MainForm)root).init();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
