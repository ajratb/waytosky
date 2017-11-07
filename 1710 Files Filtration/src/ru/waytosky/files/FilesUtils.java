/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.waytosky.files;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author Ayrat
 */
public class FilesUtils {

    private static final String[] exts = {"JPG", "JPEG", "PDF", "PNG", "TXT",
        "DOC", "DOCX", "XLS", "XLSX", "PPT", "PPTX"};

    public static String getFileExt(File file) {
        String ext = "";

        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            ext = file.getName().substring(i + 1);
        }
//int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
//
//if (i > p) {
//    extension = fileName.substring(i+1);
//}
        return ext;
    }
    
    public static boolean checkFileExt(String ext){
        ext=ext.toUpperCase();
         Stream<String> stream = Arrays.stream(exts);
         return stream.anyMatch(ext::equals);
    }
}
