
package br.com.teste;

import java.text.DecimalFormat;
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
    
    public static void main(String[] args) throws FrameGrabber.Exception, InterruptedException, TesseractException {
        

OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
        //CAMERA
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        
       
         
        camera.start();
       
        CascadeClassifier objPlaca = new CascadeClassifier("src/br/com/teste/cascades/haarcascade_russian_plate_number.xml");
        
        
        Tesseract tess4j = new Tesseract();
        tess4j.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
        tess4j.setLanguage("por");


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
            

            //quando entra neste "for" ele ja conseguiu detectar uma face
            for(int i=0; i<placaDetectada.size(); i++){
                //Rect significa o retangulo ao redor da face 
                Rect dadosPlaca = placaDetectada.get(i);
                //vamos desenhar um retangulo na face colorida quando reconhecer uma face;   padrao R,G,B, naoUsado
                rectangle(imagemColorido, dadosPlaca, new Scalar(0, 255, 0, 0)); 
                
                Mat placaCapturada = new Mat(imagemCinza, dadosPlaca);
                //linha abaixo padroniza o tamanho das imagens para 160 x 160    aula 008
//resize(placaCapturada, placaCapturada, new Size(160, 160));
                
  
                //para indeicar qual rotulo é; se é rotulo 1 ou rotulo 2. numero 1 passado por padrao no metodo como parametro para evitar erros.  aula 013 3:52
                IntPointer rotulo = new IntPointer(1);
                DoublePointer confianca = new DoublePointer(1);

//reconhecedor.predict(placaCapturada, rotulo, confianca);
                int predicao = rotulo.get(0);

                //result = tess4j.doOCR(bi, rctngl);
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
                
                //para colocar o nome ao lado da foto que foi reconhecida
                int x = Math.max(dadosPlaca.tl().x() - 10, 0);
                int y = Math.max(dadosPlaca.tl().y() - 10, 0);
                //linha abaixo coloca o texto para aparecer na webcam
                putText(imagemColorido, result, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                
            }
            if(cFrame.isVisible()){
                    cFrame.showImage(frameCapturado);
                }    

        }
        
        
        cFrame.dispose();
        camera.stop();
        
    }
    
}
