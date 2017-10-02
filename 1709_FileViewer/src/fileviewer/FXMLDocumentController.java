/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileviewer;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author Ayrat
 */
public class FXMLDocumentController implements Initializable {

     @FXML
    private Label label;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        showScanForm(stage);

    }

    private void showScanForm(Stage parentStage) {
        //проверяем файл пропертиз
        //вытаскиваем из него свойство scan_dir
       
        String home = System.getProperty("user.home");
        String scan = home + "/Pictures/cam";

        if (Files.exists(Paths.get(scan))) {
            ScanAdditionForm form = new ScanAdditionForm(scan);
            Scene scene = new Scene(form, 540, 700);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        }else{
             //если нет, предлагаем выбрать новую
            DirectoryChooser directoryChooser = new DirectoryChooser();
            
            File selectedDirectory
                    = directoryChooser.showDialog(parentStage);
            //перезаписываем пропертиз
            ScanAdditionForm form = new ScanAdditionForm(selectedDirectory.getAbsolutePath());
            Scene scene = new Scene(form, 400, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            
        }

    }

    public void setScanSourceFolder() {
        //по умолчанию будет скорее всего папка камеры (вроде не меняется)
        //если указывает другую, то сохраняем это значение в файле пропертиз
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
