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

    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

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

    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    
}
