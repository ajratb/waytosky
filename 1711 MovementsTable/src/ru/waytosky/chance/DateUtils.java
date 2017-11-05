/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.chance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Ayrat
 */
public class DateUtils {

    public static XMLGregorianCalendar dateToXmlCalendar(Date date) throws DatatypeConfigurationException {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar result = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        return result;
    }

    public static Date xmlCalendarToDate(XMLGregorianCalendar cal) {
        return cal.toGregorianCalendar().getTime();
    }

    public static LocalDateTime dateTolocalDateTime(Date date) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date
                .toInstant(), ZoneId.systemDefault());
        return ldt;
    }

    public static LocalDate dateToLocalDate(Date date) {
        LocalDate result = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return result;
    }

    public static Date localDateTimeToDate(LocalDateTime ldt) {
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        return out;
    }

    public static XMLGregorianCalendar localDateTimeToXmlCalendar(LocalDateTime ldt)
            throws DatatypeConfigurationException {
        Date date = localDateTimeToDate(ldt);
        return dateToXmlCalendar(date);
    }

    public static LocalDateTime xmlCalendarToLocalDateTime(XMLGregorianCalendar cal) {
        Date date = xmlCalendarToDate(cal);
        return dateTolocalDateTime(date);
    }

    public static String xmlCalendarToString(XMLGregorianCalendar cal) {
        String format = "dd.MM.yyyy";
        DateFormat df = new SimpleDateFormat(format);
        return df.format(cal.toGregorianCalendar().getTime());
    }

    public static void main(String[] args) {
        try {
            XMLGregorianCalendar calFromLocalDate = localDateTimeToXmlCalendar(LocalDateTime.now());
            System.out.println(calFromLocalDate);
            XMLGregorianCalendar calFromDate = dateToXmlCalendar(new Date());
            System.out.println(calFromDate);
            System.out.println(xmlCalendarToString(calFromDate));
            System.out.println(xmlCalendarToString(calFromLocalDate));
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Date getDateFromString(String dateInString){
         SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
         Date date;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException ex) {
            date=new Date();
        }
         return date;
    }
}
