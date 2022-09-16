
package br.com.teste;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
//import static org.bytedeco.opencv.global.opencv_cudaimgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.opencv_core.Mat;


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
