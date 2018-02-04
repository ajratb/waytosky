/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author Ayrat
 */
public class MainController implements Initializable {

    @FXML
    private Label label;
    private List<File> files;
    private WatchService watcher;
    private Map<WatchKey, Path> keys;//final!
    private Worker<String> worker;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
//        System.out.println("You clicked me!");
        if (watcher != null) {
            watcher.close();
        }
        if (worker!=null&&worker.isRunning()) {
            ((Service) worker).cancel();
        }
        DirectoryChooser dirDlg = new DirectoryChooser();
        File dir = dirDlg.showDialog(//null);
                ((Button) event.getSource()).getScene().getWindow());
        if (dir != null) {
            label.setText(dir.getName());
        }
        Path dirPath = Paths.get(dir.toURI());
        watcher = FileSystems.getDefault().newWatchService();
        
        worker = new Service<String>() {
            @Override
            protected Task createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        try {
                            WatchKey key = dirPath.register(watcher, ENTRY_CREATE, ENTRY_DELETE);
                            keys.put(key, dirPath);
                            processEvents();
                        } catch (IOException ioEx) {

                        }
                        return "Completed";
                    }

                };
            }

        };
        
        ((Service) worker).restart();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        keys = new HashMap<WatchKey, Path>();
    }

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    private void processEvents() {
        System.out.println("processEvents");
        for (;;) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                // print out event
                System.out.format("%s: %s\n", kind.name(), child);
                Platform.runLater(() -> label.setText(kind.name()));

            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();

            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }

        }
    }

}
