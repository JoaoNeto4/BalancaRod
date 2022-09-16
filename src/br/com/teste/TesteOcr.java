
package br.com.teste;

import java.io.File;
import java.io.IOException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 *
 * @author joao
 */
public class TesteOcr {

    public static void main(String[] args) throws IOException {
    //sudo apt-get install tesseract-ocr-por
    //sudo apt-get install tesseract-ocr
    //
    //add ao projeto lib Tess4j_5.4 & javacv_1.5
    
    /*
    botao direito no projeto -> propriedades --> build --> compiling --> desmarca "Compile on salve"
    so funciona java 8
    */

     File imageFile = new File("/home/joao/Downloads/TESTE/teste.png");
     //File imageFile = new File("./src/main/resources/teste.png");
     Tesseract tess4j = new Tesseract();
     tess4j.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
     tess4j.setLanguage("por");
     try {
         String result = tess4j.doOCR(imageFile);
         System.out.println(result);
     } catch (TesseractException e) {
         System.err.println(e.getMessage());
     }
    }  
    
    
}
