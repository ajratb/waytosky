/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author BikchentaevAA
 */
public class MainController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private TextField txtClass;

    @FXML
    private TextField txtTitle;

    @FXML
    private CheckBox chBoxFindByTitle;

    @FXML
    private TableView<FoundWindow> tvResults;

    @FXML
    private TableColumn<FoundWindow, String> clmHwnd;

    @FXML
    private TableColumn<FoundWindow, Boolean> clmIsRoot;

    @FXML
    private TableColumn<FoundWindow, Boolean> clmIsVisible;

    private final String WND_CLASS = "MozillaWindowClass";
    private final String WND_TITLE = "";

    private final User32 user32 = User32.INSTANCE;
    
    @FXML
    void handleButtonAction(ActionEvent event) {
        // EnumWindow - смотрим совпадения по классу или заголовку (или по тому и другому!!!)

        
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            int count = 0;

            @Override
            public boolean callback(WinDef.HWND hWnd, Pointer arg1) {
//                

                byte[] windowClass = new byte[512];
                user32.GetClassNameA(hWnd, windowClass, 512);
                String wClass = Native.toString(windowClass);

                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wClass.isEmpty() || !wClass.equals(txtClass.getText())) {
                    return true;
                }

                FoundWindow found = new FoundWindow();
                found.setHwnd(hWnd);
//                found.setHwndString(hWnd.toString());

                found.setIsVisible(user32.IsWindowVisible(hWnd));

//                byte[] windowText = new byte[512];
////                char[] windowText = new char[512];
//                user32.GetWindowTextA(hWnd, windowText, 512);
////                 user32.GetWindowText(hWnd, windowText, 512);
//                String wText = Native.toString(windowText, "Windows-1251");
//
//                // get rid of this if block if you want all windows regardless of whether
//                // or not they have text
//                if (wText.isEmpty()) {
//                    return true;
//                }
                WinDef.HWND owner = user32.GetWindow(hWnd, WinUser.GW_OWNER);
                if (owner == null) {
                    found.setIsRoot(true);
                } else {
                    found.setIsRoot(false);
                }
//                System.out.println("Found window " + hWnd + " For proc: " + procPtr.getValue()
                tvResults.getItems().add(found);
                return true;
            }
        }, null);

        // создаем объект FoundWindow  и добавляем его в таблицу
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtClass.setText(WND_CLASS);
        txtTitle.setText(WND_TITLE);

        clmHwnd.setCellValueFactory((cell) -> new SimpleStringProperty(((FoundWindow) cell.getValue()).getHwnd().toString()));
        clmIsRoot.setCellValueFactory((cell) -> new SimpleBooleanProperty(((FoundWindow) cell.getValue()).isRoot()));
        clmIsVisible.setCellValueFactory((cell) -> new SimpleBooleanProperty(((FoundWindow) cell.getValue()).isVisible()));
        
        tvResults.setRowFactory(tv->{
            TableRow<FoundWindow> row = new TableRow<>();
            row.setOnMouseClicked(ev->{
                if(ev.getClickCount()==2&&(!row.isEmpty())){
                    user32.INSTANCE.ShowWindow(row.getItem().getHwnd(), WinUser.SW_RESTORE);
                    user32.INSTANCE.SetForegroundWindow(row.getItem().getHwnd());
                }
            });
            return row;
        });
        
    }

    private static class FoundWindow {

        WinDef.HWND hwnd; 
//        String hwndString;
        boolean isRoot;
        boolean isVisible;

//        public String getHwndString() {
//            return hwndString;
//        }
//
//        public void setHwndString(String hwnd) {
//            this.hwndString = hwnd;
//        }

        public boolean isRoot() {
            return isRoot;
        }

        public void setIsRoot(boolean isRoot) {
            this.isRoot = isRoot;
        }

        public boolean isVisible() {
            return isVisible;
        }

        public void setIsVisible(boolean isVisible) {
            this.isVisible = isVisible;
        }

        public WinDef.HWND getHwnd() {
            return hwnd;
        }

        public void setHwnd(WinDef.HWND hwnd) {
            this.hwnd = hwnd;
        }
        
        
        
    }

    public interface User32 extends StdCallLibrary {

        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);

        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);

        int GetWindowTextA(WinDef.HWND hWnd, byte[] lpString, int nMaxCount);
//        java.lang.UnsatisfiedLinkError: Error looking up function 'GetWindowText': Не найдена указанная процедура.
//        int GetWindowText(HWND hWnd, char[] lpString, int nMaxCount);

        int GetClassNameA(WinDef.HWND hWnd,
                byte[] lpClassName,
                int nMaxCount);

        boolean IsWindowVisible(WinDef.HWND hWnd);

        int GetWindowThreadProcessId(WinDef.HWND hWnd, IntByReference proc);

        WinDef.HWND GetWindow(WinDef.HWND hWnd, int uCmd);
        
        boolean ShowWindow(WinDef.HWND hWnd, int nCmdShow);
        
        boolean SetForegroundWindow(WinDef.HWND hWnd);
    }

}
