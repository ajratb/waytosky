package ru.waytosky.jfx.tv;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.BooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author Ayrat
 */
public class ImageClickCellFactory
        implements Callback<TableColumn, TableCell> {

    private final EventHandler<Event> click;

    public ImageClickCellFactory(EventHandler click) {
        this.click = click;
    }

    @Override
    public TableCell call(TableColumn p) {
        TableCell<Object, BooleanProperty> cell
                = new TableCell<Object, BooleanProperty>() {

            private final VBox vbox;
//            private final ImageView imageview;
            private final  FontAwesomeIconView icon =  new FontAwesomeIconView();
            // Constructor
            {
                vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                 vbox.getChildren().addAll(icon);
//                vbox.getChildren().addAll(imageview);
//                setGraphic(icon);
            }

            @Override
            protected void updateItem(BooleanProperty item,
                    boolean empty) {

                // calling super here is very important - don't skip this!
                super.updateItem(item, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);

                } else {
//                            System.out.println("item.getFavoriteImage(): "+ item.getFavoriteImage());
                   
                    if (item != null) {
                        if(item.get()){
                            icon.setIcon(FontAwesomeIcon.STAR);
                           setGraphic(icon);
                        }else{
                            setGraphic(null);
                        }
                    } else {
                      setGraphic(null);
                      
                    }
                    
                    
                }
            }
        };

        // Simple click
        if (click != null) {
            cell.setOnMouseClicked(click);
        }

        return cell;
    }

}
