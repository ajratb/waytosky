
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Ayrat
 */
public class MainForm extends GridPane
        implements Initializable {

    @FXML
    private ToggleButton tBtnShop;

    @FXML
    private ToggleButton tBtnAuthors;

    @FXML
    private ToggleButton tBtnBooks;

    @FXML
    private ToggleButton tBtnCatalog;
    
    
    @FXML
    private StackPane stackPane;

    
    private GridPane storePanel;
    private GridPane authorsPanel;
    
    @FXML
    void onAction_tBtnShop(ActionEvent event) {
        System.out.println("Inside onAction_tBtnShop");
        if (storePanel == null) {
            System.out.println("storePane == null");
            storePanel = new StorePanel();
        }
        stackPane.getChildren().clear();
        stackPane.getChildren().add(storePanel);
    }

    @FXML
    void onAction_tBtnCatalog(ActionEvent event) {

    }

    @FXML
    void onAction_tBtnAuthors(ActionEvent event) {
         System.out.println("Inside onAction_tBtnAuthors");
        if (authorsPanel == null) {
            System.out.println("authorsPanel == null");
            authorsPanel = new AuthorsPanel();
        }
         stackPane.getChildren().clear();
        stackPane.getChildren().add(authorsPanel);
        
    }

    @FXML
    void onAction_tBtnBooks(ActionEvent event) {
//        add(new StorePanel(),0,2);
          
    }
//    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("inside initialize");
//        if(tBtnShop==null)
//            System.out.println("tBtnShop is NULL");
//        else
//            System.out.println("tBtnShop is NOT null");
         if (storePanel == null) {
            System.out.println("storePane == null");
            storePanel = new StorePanel();
             
        }

        tBtnShop.setSelected(true);
       stackPane.getChildren().add(storePanel);
       
       
    }

    public MainForm() {
//        storePanel = new StorePanel();
//        stackPane.getChildren().add(storePanel);
       
        
    }
}
