/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.CustomTextField;

/**
 *
 * @author BikchentaevAA
 */
public class MainController implements Initializable {

    @FXML
    private CustomTextField txtProgram;

    @FXML
    private Label lbInfo;

    @FXML
    private TextArea txtArea_Processes;

    @FXML
    private CustomTextField txtWindowClass;

    @FXML
    private TextArea txtArea_Windows;

    private File procFile;

    @FXML
    void onAction_btnPlayProc(ActionEvent event) {
        if (procFile != null) {
            try {
//                Process p = Runtime.getRuntime().exec(procFile.getCanonicalPath());
                Process p = new ProcessBuilder(procFile.getCanonicalPath()).start();
                lbInfo.setText("isAlive: "+String.valueOf(p.isAlive()));//+" exitValue: "+String.valueOf(p.exitValue()));
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("File is NULL");
        }
    }

    @FXML
    void onAction_btnShowWindow(ActionEvent event) {
        HWND hwnd = User32.INSTANCE.FindWindow(txtWindowClass.getText(), null);
        PUser32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE);
    }

    @FXML
    void onMouseClicked_iconFolder(MouseEvent event) {
        FileChooser chooser = new FileChooser();//(Stage)lbInfo.getScene().getWindow());
        procFile = chooser.showOpenDialog(lbInfo.getScene().getWindow());
        if (procFile != null) {
            txtProgram.setText(procFile.getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String line;
        try {
            Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");

            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));) {
                while ((line = input.readLine()) != null) {
                    txtArea_Processes.appendText(line+"\n"); //<-- Parse data here.
                }
            }
        } catch (IOException ioEx) {

        }

        final PUser32 user32 = PUser32.INSTANCE;
        user32.EnumWindows(new WNDENUMPROC() {
            int count = 0;

            @Override
            public boolean callback(HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                user32.GetWindowTextA(hWnd, windowText, 512);
                String wText = Native.toString(windowText);

                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wText.isEmpty()) {
                    return true;
                }

                byte[] windowClass = new byte[512];
                user32.GetClassNameA(hWnd, windowClass, 512);
                String wClass = Native.toString(windowClass);

                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wClass.isEmpty()) {
                    return true;
                }

                txtArea_Windows.appendText(" " + hWnd + ", total " + ++count
                        + " Text: " + wText + " ClassName is:" + wClass + "\n");
                return true;
            }
        }, null);
    }

    public interface PUser32 extends StdCallLibrary {

        PUser32 INSTANCE = (PUser32) Native.loadLibrary("user32", PUser32.class);

        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);

        int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);

        int GetClassNameA(WinDef.HWND hWnd,
                byte[] lpClassName,
                int nMaxCount);

        boolean ShowWindow(HWND hWnd, int i);
//        HWND FindWindow(String string, String string1);
    }

}
