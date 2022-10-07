
package br.com.teste;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.Mat;

public class TesteOKOKOK extends javax.swing.JDialog {



    //JavaCV
    //opencv_videoio.VideoCapture webSource = null;
    org.bytedeco.javacv.OpenCVFrameGrabber webSource;
    Mat cameraImage = new Mat();
    
    //opencv_face.FaceRecognizer recognizer = opencv_face.LBPHFaceRecognizer.create();   
    Java2DFrameConverter paintConverter = new Java2DFrameConverter();       
    OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();
    BytePointer mem = new BytePointer();


    private TesteOKOKOK.DaemonThread myThread = null;

    private javax.swing.JLabel label_photo;


    public TesteOKOKOK(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //recognizer.read("C:\\photos\\classifierLBPH.yml");
        //recognizer.setThreshold(280);
        startCamera();
    }


    public void stopCamera() {
            if (myThread.runnable) {
           myThread.runnable = false;
           //webSource.release();
           try {
                       webSource.close();
               } catch (Exception e) {
                       // TODO Auto-generated catch block
                       e.printStackTrace();
               }
        }
        dispose();
    }

    public void startCamera() {
        new Thread() {
            @Override
            public void run() {
                //webSource = new opencv_videoio.VideoCapture(0);
                webSource = new OpenCVFrameGrabber(0);
                myThread = new TesteOKOKOK.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panelMain = new JPanel();
        label_photo = new javax.swing.JLabel();
       // JPanel panel1 = new JPanel();

        //panelMain.setBackground(Color.BLUE);
        panelMain.setBounds(0, 0, 430, 420);
        getContentPane().add(panelMain);
        panelMain.setLayout(null);

        label_photo.setBounds(10, 10, 410, 400);
        label_photo.setBorder(new LineBorder(new Color(255, 255, 255)));
        panelMain.add(label_photo);
/*
        //painel da direita
        panel1.setBorder(new LineBorder(new Color(255, 255, 255)));
        panel1.setBounds(330, 0, 430, 420);
        panel1.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(panel1);
        panel1.setLayout(null);
*/
        setSize(new java.awt.Dimension(780, 620));
        setLocationRelativeTo(null);
    }


    public static void main(String arg[]) {
            /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TesteOKOKOK dialog = new TesteOKOKOK(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });		
    }

class DaemonThread implements Runnable {

    protected volatile boolean runnable = false;

    @Override
    public void run() {
        synchronized (this) {
            while (runnable) {
                try {
                	
                    webSource.start();
                    org.bytedeco.javacv.Frame frameCapturado = null;
                	
                    long startTime = System.currentTimeMillis();
                    long frame = 0;
                    while ((frameCapturado = webSource.grab()) != null && runnable) {
                        //if ( (frameCapturado = webSource.grab()) != null) {
                        //BufferedImage buff = paintConverter.getBufferedImage(frameCapturado, 1);
                        //Graphics g = canvas.getGraphics();
                        //g.drawImage(buff, 0, 0, CAPTUREWIDTH, CAPTUREHRIGHT, 0, 0, buff.getWidth(), buff.getHeight(), null);                    		                    		

                        //webSource.retrieve(cameraImage);
                        cameraImage = converterToMat.convert(frameCapturado);
                        Graphics g = label_photo.getGraphics();

                        imencode(".bmp", cameraImage, mem);
                        Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                        BufferedImage buff = (BufferedImage) im;

                        try {
                            if (g.drawImage(buff, 0, 0, 410, 400, 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                if (runnable == false) {
                                    this.wait();
                                }
                            }
                        } catch (NullPointerException e) {
                                e.printStackTrace();
                        }                            
                    }
                } catch (IOException | InterruptedException ex) {
                	ex.printStackTrace();
                }
            }
        }
    }
}
}	