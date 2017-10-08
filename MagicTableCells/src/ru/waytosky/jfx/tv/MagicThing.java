/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.jfx.tv;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Ayrat
 */
public class MagicThing {
    private final BooleanProperty favorite;
    private int status;
    private Date sendDate;
    private Date backDate;

    public MagicThing(boolean favorite, int status, Date sendDate, Date backDate) {
        this.favorite = new SimpleBooleanProperty(this,"favorite",favorite);
        this.status = status;
        this.sendDate = sendDate;
        this.backDate = backDate;
    }

    public BooleanProperty getFavorite() {
        return favorite;
    }

    
    
    public boolean isFavorite() {
        return favorite.get();
    }

    public int getStatus() {
        return status;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setFavorite(boolean favorite) {
        this.favorite.set(favorite);
    }

//    public void setFavorite(BooleanProperty favorite) {
//        this.favorite = favorite;
//    }
    
    
    
}
