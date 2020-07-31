
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

/*
 * Please feel free to use it
 */

/**
 *
 * @author Ayrat
 */
public class AuthorsPanel extends GridPane{
     public AuthorsPanel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("authors.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(StorePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
