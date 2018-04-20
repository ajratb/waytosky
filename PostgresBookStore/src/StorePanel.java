
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/*
 * Please feel free to use it
 */
/**
 *
 * @author Ayrat
 */
public class StorePanel extends GridPane {

    public StorePanel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("store.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(StorePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
