/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Ayrat
 */
public class ScanAdditionForm  extends BorderPane{

    @FXML
    private Label label;
    

    public ScanAdditionForm(String scanFolder){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "scan.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        
        try (Stream<Path> paths = Files.walk(Paths.get(scanFolder))) {
           
            List<Path> ps=paths
                    .filter(Files::isRegularFile).sorted((p1,p2)->Long.compare(p1.toFile().lastModified(), p2.toFile().lastModified()))
//                    .sorted((f1,f2)->Long.compare(Files.getLastModifiedTime(f1).toMillis(),Files.getLastModifiedTime(f2).toMillis()))
                    .collect(Collectors.toList());
           
            System.out.println(ps.get(ps.size()-1));

//            paths.forEach(p -> {
//                try {
//                    System.out.println(Files.getLastModifiedTime(p));
//                } catch (IOException ex) {
//                    System.out.println();
//                }
//            });
        } catch (IOException e) {

        }
    }
    
     @FXML
    private void handleButtonAction(ActionEvent event) {
      
    }
    

}
