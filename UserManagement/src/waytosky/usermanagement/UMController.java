/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package waytosky.usermanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author ayrat
 */
public class UMController implements Initializable {
    
     @FXML
    private Button btnRemoveUser;

    @FXML
    private Button btnEditUser;

    @FXML
    private Button btnAddUser;

    @FXML
    private ListView<?> lstUsers;

    @FXML
    void handlBtnAddUserOnAction(ActionEvent event) {
        
    }

    @FXML
    void handlBtnRemoveUserOnAction(ActionEvent event) {

    }

    @FXML
    void handlBtnEditUserOnAction(ActionEvent event) {

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
