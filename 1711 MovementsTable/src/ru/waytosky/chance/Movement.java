/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.chance;

import java.util.Date;

/**
 *
 * @author Ayrat
 */
public class Movement {
    private String  dep;
    private Date startDate;
    private Date plannedDate;
    private Date endDate;
    private boolean scan;

    public Movement(String dep, Date sendingDate, Date receivingDate, boolean scan) {
        this.dep = dep;
        this.startDate = sendingDate;
        this.plannedDate = receivingDate;
        this.scan = scan;
    }

    public String getDep() {
        return dep;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public boolean isScan() {
        return scan;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
