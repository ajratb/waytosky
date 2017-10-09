package ru.waytosky.jfx.tv;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/**
 *
 * @author Ayrat
 */
public class MagicClickCellFactory
        implements Callback<TableColumn<MagicThing, Boolean>, TableCell<MagicThing, Boolean>> {

    @Override
    public TableCell call(TableColumn p) {

        CheckBoxTableCell<MagicThing, Boolean> cell = new CheckBoxTableCell<MagicThing, Boolean>(){
            @Override
            public void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty); 
                if(!empty){
                System.out.println("inside updateItem: "+item+" index is: "+getIndex());
//                getTableView().getSelectionModel().getSelectedItem()
                }
            }
            
        };

        return cell;
    }

}
