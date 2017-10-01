/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileviewer;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Ayrat
 */
public class TestExtractImgFromPdf {
    public static void main(String[] args) throws IOException {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
//        InputStream fis = TestExtractImgFromPdf.class.getResourceAsStream("hello.pdf");
        
        File f = new File("hello.pdf");
//        OutputStream fos = new FileOutputStream(f);
        
        
         List<RenderedImage> list = getImagesFromPDF(f);
         File outputfile = new File("image.png");
         ImageIO.write(list.get(0), "png", outputfile);
        
    }
    
    public static List<RenderedImage> getImagesFromPDF(File file) throws IOException {
        
        PDDocument doc = PDDocument.load(file);
        List<RenderedImage> images = new ArrayList<>();
    for (PDPage page : doc.getPages()) {
        images.addAll(getImagesFromResources(page.getResources()));
    }

    return images;
}

private static List<RenderedImage> getImagesFromResources(PDResources resources) throws IOException {
    List<RenderedImage> images = new ArrayList<>();

    for (COSName xObjectName : resources.getXObjectNames()) {
        PDXObject xObject = resources.getXObject(xObjectName);

        if (xObject instanceof PDFormXObject) {
            images.addAll(getImagesFromResources(((PDFormXObject) xObject).getResources()));
        } else if (xObject instanceof PDImageXObject) {
            images.add(((PDImageXObject) xObject).getImage());
        }
    }

    return images;
}
}
