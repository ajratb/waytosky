/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author Ayrat
 */
public class FilesUtils {

    private static final String[] exts = {"JPG", "JPEG", "PDF", "PNG", "TXT",
        "DOC", "DOCX", "XLS", "XLSX", "PPT", "PPTX"};
    
       private static final String[] imgExts = {"JPG", "JPEG", "PNG"};

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

    public static boolean checkFileExt(String ext) {
        ext = ext.toUpperCase();
        Stream<String> stream = Arrays.stream(exts);
        return stream.anyMatch(ext::equals);
    }
    
     public static boolean checkImgFileExt(String ext) {
        ext = ext.toUpperCase();
        Stream<String> stream = Arrays.stream(imgExts);
        return stream.anyMatch(ext::equals);
    }

    public static String getApplicationFilePath() {
        return FilesUtils.class.getResource("/ru/waytosky/app.properties").toExternalForm();
    }

    public static boolean copyFileToDirectory(String sourceFile, String target) {
        File sfile = new File(sourceFile);
        File tfile = new File(target);
        if (tfile.getParentFile().exists() && sfile.exists()) {
            return copyFileToDirectory(sfile, tfile);
        } else {
            return false;
        }
    }

    public static boolean copyFileToDirectory(File source, File target) {
        try {
            Path result = Files.copy(source.toPath(), target.toPath(),  REPLACE_EXISTING);
            if(result!=null){
                return true;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
