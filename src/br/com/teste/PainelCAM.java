
package br.com.teste;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_java;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author joao
 */
public class PainelCAM extends javax.swing.JPanel implements Runnable{

    private VideoCapture video;
    private Mat frame;
    private BufferedImage buff;
    //OpenCVFrameGrabber video;
    
    public PainelCAM() {
        initComponents();
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Loader.load(opencv_java.class);
        
        new Thread(this).start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(buff ==null){
            return;
        }
        g.drawImage(buff, 10, 20, buff.getWidth(), buff.getHeight(), null);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void run() {

        this.video = new VideoCapture(0);
        //this.video = new OpenCVFrameGrabber(0);
        this.frame = new Mat();
        if(video.isOpened()){

            while(true){
                //video.read(frame);
                video.read(frame);
                if(frame != null){
                    MatToBufferedImage(frame);
                    this.repaint();
                }
            }
        }
    }

    private void MatToBufferedImage(Mat mat) {
        int altura = mat.width();
        int largura = mat.height();
        int canais = mat.channels();
        
        byte[] source = new byte[altura * largura *canais];
        mat.get(0, 0, source);
        buff = new BufferedImage(altura, largura, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] saida = ((DataBufferByte) buff.getRaster().getDataBuffer()).getData();
        System.arraycopy(source, 0, saida, 0, source.length);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
