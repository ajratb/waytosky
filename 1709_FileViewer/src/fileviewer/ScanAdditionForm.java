/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileviewer;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author Ayrat
 */
public class ScanAdditionForm extends BorderPane {

    private File target;
    private String scans;
    @FXML
    private ImageView imgView;

    public ScanAdditionForm(String scanFolder) {
        scans = scanFolder;
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

            List<Path> ps = paths
                    .filter(Files::isRegularFile).sorted((p1, p2) -> Long.compare(p1.toFile().lastModified(), p2.toFile().lastModified()))
                    //                    .sorted((f1,f2)->Long.compare(Files.getLastModifiedTime(f1).toMillis(),Files.getLastModifiedTime(f2).toMillis()))
                    .collect(Collectors.toList());

            System.out.println(ps.get(ps.size() - 1));
            File newFile = ps.get(ps.size()-1).toFile();
            BufferedImage bimg = ImageIO.read(newFile);
                    Image image = SwingFXUtils.toFXImage(bimg, null);
                    imgView.setImage(image);
            
        } catch (IOException e) {

        }
    }

    @FXML
    private void onAction_btnSave(ActionEvent event) {
        //закрываем форму
    }

    @FXML
    void onAction_btnOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать скан документа");
        fileChooser.setInitialDirectory(new File(scans));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Файлы скана", "*.png", "*.jpg", "*.pdf"));
        Stage thisStage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(thisStage);
        if (selectedFile != null) {
            //смотрим расширение
            String ext = "";

            int i = selectedFile.getName().lastIndexOf('.');
            int p = Math.max(selectedFile.getName().lastIndexOf('/'), selectedFile.getName().lastIndexOf('\\'));

            if (i > p) {
                ext = selectedFile.getName().substring(i + 1);
            }
            System.out.println("extension: " + ext);
            if (ext.equals("pdf")) {
                try {
                    List<RenderedImage> list = PDF_Utils.getImagesFromPDF(selectedFile);
                    File outputfile = new File("image.png");
                    ImageIO.write(list.get(0), "png", outputfile);
                    Image image = SwingFXUtils.toFXImage((BufferedImage) list.get(0), null);
                    imgView.setImage(image);

                } catch (IOException ex) {
//                    Logger.getLogger(ScanAdditionForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    BufferedImage bimg = ImageIO.read(selectedFile);
                    Image image = SwingFXUtils.toFXImage(bimg, null);
                    imgView.setImage(image);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @FXML
    void onAction_btnRefresh(ActionEvent event) {
        try (Stream<Path> paths = Files.walk(Paths.get(scans))) {

            List<Path> ps = paths
                    .filter(Files::isRegularFile).sorted((p1, p2) -> Long.compare(p1.toFile().lastModified(), p2.toFile().lastModified()))
                    //                    .sorted((f1,f2)->Long.compare(Files.getLastModifiedTime(f1).toMillis(),Files.getLastModifiedTime(f2).toMillis()))
                    .collect(Collectors.toList());

            System.out.println(ps.get(ps.size() - 1));
            File newFile = ps.get(ps.size()-1).toFile();
            BufferedImage bimg = ImageIO.read(newFile);
                    Image image = SwingFXUtils.toFXImage(bimg, null);
                    imgView.setImage(image);
            
        } catch (IOException e) {

        }
    }

    @FXML
    void onAction_linkExit(ActionEvent event) {

    }

    @FXML
    void onAction_linkSettings(ActionEvent event) {

    }

}
