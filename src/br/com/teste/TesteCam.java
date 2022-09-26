
package br.com.teste;

import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.tess4j.ITessAPI.TessOcrEngineMode;
import net.sourceforge.tess4j.ITessAPI.TessPageSegMode;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;

import static org.bytedeco.opencv.global.opencv_imgproc.*;
import org.bytedeco.opencv.opencv_core.Point;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.bytedeco.javacv.Java2DFrameConverter;

public class TesteCam {
    
    public boolean validaPlaca(String placa) {
	boolean result = false;
	Pattern pattern = Pattern.compile("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}\\-[0-9]{4}");
	Matcher mat = pattern.matcher(placa);
	if (!mat.matches()) {
            result = false;
	} else {
            result = true;
	}
	return result;
    }
    public boolean validaPlaca2(String placa) {
	Pattern pattern = Pattern.compile("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}\\-[0-9]{4}");
	Matcher mat = pattern.matcher(placa);
	return mat.matches();
    }
    
    
    
    public static void main(String[] args) throws FrameGrabber.Exception, InterruptedException, TesseractException {
        TesteCam t = new TesteCam();

OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
        //CAMERA
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        
        //REGEX: ^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$
        
        camera.start();
       
        CascadeClassifier objPlaca = new CascadeClassifier("src/br/com/teste/cascades/haarcascade_russian_plate_number.xml");
        
        
        Tesseract tess4j = new Tesseract();
        tess4j.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
        tess4j.setLanguage("eng");
        tess4j.setOcrEngineMode(3);//VERRR

        //https://stackoverflow.com/questions/43966201/tesseract-tess4j-increasing-accuracy
        tess4j.setHocr(false);
        tess4j.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVXYWZ0123456789");
        tess4j.setTessVariable("load_system_dawg", "false");
        tess4j.setTessVariable("load_freq_dawg", "false");
        tess4j.setOcrEngineMode(TessOcrEngineMode.OEM_DEFAULT);
        tess4j.setPageSegMode(TessPageSegMode.PSM_SINGLE_WORD); //
        //tess4j.setPageSegMode(7);
        //https://tesseract.patagames.com/help/html/T_Patagames_Ocr_Enums_PageSegMode.htm
        //https://tesseract.patagames.com/help/html/T_Patagames_Ocr_Enums_OcrEngineMode.htm

Java2DFrameConverter c2 = new Java2DFrameConverter();
String result=""; 

OpenCVFrameConverter.ToMat openCVConverter = new OpenCVFrameConverter.ToMat();
Java2DFrameConverter java2DConverter = new Java2DFrameConverter();


        //os parametros sao: ("titulo janela", tamanho da janela / tamanho janela)
        // poderia ser tbm: ("titulo", 1);
        CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
        Frame frameCapturado = null;

        Mat imagemColorido  = new Mat();
    
        while ((frameCapturado = camera.grab()) != null){
            
           
            
            imagemColorido = convertemat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorido, imagemCinza, COLOR_BGRA2GRAY);
            //na linha abaixo, ficarão armazenadas todas as faces detectadas
            RectVector placaDetectada = new RectVector();
            //(imagem, facesDetectadas, ScaleFactory-EscalaDaImagem, numeroDeVisinhos, Flag-usadoEmVersoesAntigas, tamanhoMinimoImagem, TamanhoMaximoImagem)
            objPlaca.detectMultiScale(imagemCinza, placaDetectada, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));
            

            //ao entrar ja reconheceu a placa
            for(int i=0; i<placaDetectada.size(); i++){
                //Rect retangulo 
                Rect dadosPlaca = placaDetectada.get(i);
                //desenha retungulo
                rectangle(imagemColorido, dadosPlaca, new Scalar(0, 255, 0, 0)); 
                
                Mat placaCapturada = new Mat(imagemCinza, dadosPlaca);

//resize(placaCapturada, placaCapturada, new Size(160, 160));
                
  
                //para identificar qual rotulo é; se é rotulo 1 ou rotulo 2. numero 1 passado por padrao no metodo como parametro para evitar erros.
                IntPointer rotulo = new IntPointer(1);
                DoublePointer confianca = new DoublePointer(1);

//reconhecedor.predict(placaCapturada, rotulo, confianca);
                int predicao = rotulo.get(0);

                //result = tess4j.doOCR(bi, rctngl);
                //result = tess4j.doOCR(java2DConverter.convert(openCVConverter.convert(placaCapturada)));
                result = tess4j.doOCR(java2DConverter.convert(openCVConverter.convert(placaCapturada)));

                //abaixo se verificacao retornar -1 é porque nao encontrou nenhuma classe
                if(predicao == -1){
                    result = "Desconhecido";
                }else{
    /**********************************************************************************/  
                /**    TESTE     **/
                    String teste = String.valueOf(confianca.get());
                    double teste2 = Double.parseDouble(teste);
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.format(teste2); 
                    //System.out.println(df);
                    //System.out.println(String.format("%.1f", teste2));

                    int aa = Math.max(20, 0);
                    int bb = Math.max(20, 0);
                    String texto = String.format("%.1f", teste2);
                    String eita = texto.replace(",", ".");
                    Double num = Double.parseDouble(eita);
    /**********************************************************************************/
                    
                    //result =  " - " + confianca.get(0);
                }
                
                //para colocar otexto ao redor
                int x = Math.max(dadosPlaca.tl().x() - 10, 0);
                int y = Math.max(dadosPlaca.tl().y() - 10, 0);
                //linha abaixo coloca o texto para aparecer na webcam
                
               // putText(imagemColorido, result, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                if(t.validaPlaca(result)){
                    putText(imagemColorido, result, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                    System.out.println("resultado: "+result);
                }
                System.out.println(t.validaPlaca2(result));
                System.out.println("resultado sem validacao: "+result);
            }
            if(cFrame.isVisible()){
                cFrame.showImage(frameCapturado);
            }    

        }
        
        
        cFrame.dispose();
        camera.stop();
        
    }
    
}
