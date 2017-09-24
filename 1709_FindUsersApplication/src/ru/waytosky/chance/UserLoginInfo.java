/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.chance;

import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ayrat
 */
public class UserLoginInfo {
    
    private long uId;
    private String firstName;
    private String lastName;
    private String patronimicName;
    private String position;
    private String department;
    
 
    /**
     * Default constructor.
     */
    public UserLoginInfo() {
        
    }
    
    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public UserLoginInfo(long uId, String firstName, String lastName, 
            String patronimicName, String position, String department) {
        this.uId=uId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronimicName = patronimicName;
        this.position = position;
        this.department = department;
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronimicName() {
        return patronimicName;
    }

    public void setPatronimicName(String patronimicName) {
        this.patronimicName = patronimicName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
 
    
        
}
