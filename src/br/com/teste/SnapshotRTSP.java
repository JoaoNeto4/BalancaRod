
package br.com.teste;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
//import static org.bytedeco.opencv.global.opencv_cudaimgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import static org.bytedeco.opencv.global.opencv_imgproc.*;


/**
 *
 * @author joao
 * rtsp://admin:campos159@10.42.42.133:554/cam/realmonitor?channel=1&subtype=0
 */
public class SnapshotRTSP {
    public static void main(String[] args) throws FrameGrabber.Exception, InterruptedException {
        

        OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();

        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        camera.start();

        

        CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
        Mat imagemColorido  = new Mat();
        Frame frameCapturado = camera.grab();

        imagemColorido = convertemat.convert(frameCapturado);

        imwrite("src/br/com/teste/fotoTeste.jpg", imagemColorido);
        System.out.println("foto capturada\n");
         if (cFrame.isVisible()){
            cFrame.showImage(frameCapturado);
        }
        cFrame.dispose();
        camera.stop();
        
        //resize(faceCapturada, faceCapturada, new Size(160, 160));


    }

}
