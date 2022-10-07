
package br.com.teste;

import com.googlecode.javacv.FrameGrabber;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.bytedeco.javacv.CanvasFrame;
//import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
//import org.bytedeco.javacv.Frame;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.javacv.Frame;
import org.bytedeco.opencv.opencv_core.IplImage;
//import com.googlecode.javacv.cpp.opencv_core.IplImage;


/**
 *
 * @author joao
 */
public class TesteViw extends javax.swing.JDialog {

//EX: https://answers.opencv.org/question/46638/java-how-capture-webcam-and-show-it-in-a-jpanel-like-imshow/

    public TesteViw(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    /*
    46:00
    OpenCVFrameGrabber cam = new OpenCVFrameGrabber(0);
    int largCamera =400;
    int altCamera = 300;
    Dimension dimension = new Dimension(largCamera,altCamera);
    Dimension dimension = cam.getSampleRate();
    */
    
    public void mostraVideo2() throws org.bytedeco.javacv.FrameGrabber.Exception {
        OpenCVFrameConverter.ToMat toMatConverter = new OpenCVFrameConverter.ToMat();
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        grabber.start();
        //Frame frame = grabber.grab();
        IplImage frame = grabber.grab();
        
        //Graphics graphics = jPanel1.getGraphics();
        //BufferedImage resizedImage = frame.g

        Mat mat = toMatConverter.convert(frame);
        Frame processedFrame = toMatConverter.convert(mat);

        Graphics graphics = jPanel1.getGraphics();
        BufferedImage resizedImage = processedFrame.getBufferedImage();
        SwingUtilities.invokeLater(() -> {
            graphics.drawImage(resizedImage, 0, 0, jPanel1);
        });
        
        
    }
    
    public void mostrar(){
        BufferedImage img1;
    JLabel label;
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
    JFrame frame = new JFrame();
    try {
        grabber.start();
        IplImage img = grabber.grab();

        if (img != null) {

       img1 = img.getBufferedImage();
        ImageIcon icon = new ImageIcon(img1);
        label = new JLabel(icon);
        //photo is the name of the JPanel
        jPanel1.add(label);
        jPanel1.setVisible(true);
        jPanel1.revalidate();
        jPanel1.repaint();
        grabber.stop();
        System.out.println("done");


            grabber.stop();
            System.out.println("done");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    /*
    public void mostraVideo() throws FrameGrabber.Exception{
        camera.setImageHeight(400);
        camera.setImageWidth(300);
        //camera.
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        camera.start();
        Frame frameCapturado = null;
        CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());

        while ((frameCapturado = camera.grab()) != null){
            //this.jPanel1.add(cFrame.showImage(frameCapturado));
            //.showImage(frameCapturado);
            cFrame.showImage(frameCapturado);
            
        }
        //cFrame.dispose();
        camera.stop();
    }
    */
    public void teste2(){
        OpenCVFrameGrabber grabberCV = new OpenCVFrameGrabber(0);
        grabberCV.setImageWidth(300);
        grabberCV.setImageHeight(400);
        grabberCV.setFrameRate(29);
        grabberCV.setImageMode(FrameGrabber.ImageMode.COLOR);

        try {
            grabberCV.start();
            //jPanel1.add(grabberCV);

        } catch (Exception e) {

        }
    }
    /*
    Java2DFrameConverter f = new Java2DFrameConverter();
    OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
    //camera.setImageWidth(width);
    //camera.setImageHeight(height);
    CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
    Frame frameCapturado = null;

    private void createScene(){
        new Thread() {
             @Override
             public void run() { 
                
                String texto= "teste";
                
                 try {
                     camera.start();
                     while((frameCapturado = camera.grab()) != null){
                        System.out.println("iniciando o video");
                        
                        //jPanel1.setLayout(new BorderLayout());
                        jLabel1.setIcon(new ImageIcon( frameCapturado));
                     }
                 } catch (FrameGrabber.Exception ex) {
                     Logger.getLogger(TesteViw.class.getName()).log(Level.SEVERE, null, ex);
                 }

             }
        }.start();

    }
  */
    public void teste3() throws FrameGrabber.Exception{
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        camera.start();
        Frame frameCapturado = null;
        CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(cFrame,BorderLayout.CENTER);
        /*
        while ((frameCapturado = camera.grab()) != null){
            cFrame.showImage(frameCapturado);
        }
        */
        camera.stop();
    }
    /*
    public void teste4(){
        BufferedImage img1;
        JLabel label;
        final OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
        JFrame frame = new JFrame();
        try {
            grabber.start();
            Frame img = grabber.grab();

            if (img != null) {

                //img1 = img.getBufferedImage();
                img1 = img.getTypes();
                ImageIcon icon = new ImageIcon(img1);
                label = new JLabel(icon);
                //photo is the name of the JPanel
                jPanel1.add(label);
                jPanel1.setVisible(true);
                grabber.stop();
                System.out.println("done");


                grabber.stop();
                System.out.println("done");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */
    
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnLigar = new javax.swing.JButton();
        btnDesligar = new javax.swing.JButton();
        Teste = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 300));

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(233, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(119, 119, 119))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel1)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        btnLigar.setText("Teste");
        btnLigar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLigarMouseReleased(evt);
            }
        });

        btnDesligar.setText("desligar");
        btnDesligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesligarActionPerformed(evt);
            }
        });

        Teste.setText("Teste");
        Teste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TesteActionPerformed(evt);
            }
        });

        jButton1.setText("ok, lento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnLigar)
                        .addGap(72, 72, 72)
                        .addComponent(btnDesligar)
                        .addGap(39, 39, 39)
                        .addComponent(Teste)
                        .addGap(0, 196, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLigar)
                    .addComponent(btnDesligar)
                    .addComponent(Teste))
                .addGap(36, 36, 36))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TesteActionPerformed
        //teste3();
        //createScene();
        //test();
        mostraVideo2();
    }//GEN-LAST:event_TesteActionPerformed

    VideoCaptura webCam;
    ExibeQuadro exibeQuadro;
    Thread executor;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Evento clique do botão iniciar:
        webCam = new VideoCaptura();
        exibeQuadro = new ExibeQuadro(webCam,jLabel1);
        executor = new Thread(exibeQuadro);
        executor.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnLigarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLigarMouseReleased
        BufferedImage img1;
        JLabel label;
        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
//Image imeResize = ImageIO.read(new File("C:\\SalVentri\\PrifilePicture\\"+lbl_StudnetLogin.getText()+".jpeg"));
//1st ---> width _______2sn ---> height
//lbl_Profile.setIcon(new ImageIcon(imeResize.getScaledInstance(155, 100, 100)));

        try {
            grabber.start();
            Frame img = grabber.grab();
            while (img != null) {

                //img1 = img.getBufferedImage();
                //ImageIcon icon = new ImageIcon(img1);
                //label = new JLabel(icon);
                //photo is the name of the JPanel
            //jPanel1.add(img, BorderLayout.CENTER);
            //jPanel1.add(p1); // aqui p1 é adicionado a jPanel1
                jPanel1.validate();
                jPanel1.repaint();
                
                //jLabel1.setIcon((Icon) img);
                
                //jLabel1.setVisible(true);
                //grabber.stop();
                System.out.println("done");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnLigarMouseReleased

    private void btnDesligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesligarActionPerformed
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        Frame frameCapturado = null;
        //CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
        //JFrame jframe=new JFrame();
        //jframe.setSize(300,300);
        
        
        try {
            camera.start();
            
            while ((frameCapturado = camera.grab()) != null){
                jLabel1.setIcon((Icon) frameCapturado);
                jLabel1.repaint();
                //jPanel1.setVisible(true);
                
            }
        //cFrame.dispose();
        camera.stop();
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(TesteViw.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
        
    }//GEN-LAST:event_btnDesligarActionPerformed

    public void test(){
        Runnable r = new Runnable() {
            OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
            @Override
            public void run() {
                
                try {
                    Frame frameCapturado = null;
                    jPanel1 = new JPanel(new GridBagLayout());
                    while ((frameCapturado = camera.grab()) != null){
                        
                        //jPanel1.add(frameCapturado);

                        JFrame f = new JFrame("SquareBoard");
                        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        f.setLocationByPlatform(true);
                        f.add(jPanel1);
                        f.setMinimumSize(new Dimension(400,100));
                        f.pack();
                        f.setVisible(true);
                    }
                
                    
                } catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(TesteViw.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        SwingUtilities.invokeLater(r);
    }
    

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TesteViw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesteViw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesteViw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesteViw.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TesteViw dialog = new TesteViw(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Teste;
    private javax.swing.JButton btnDesligar;
    private javax.swing.JButton btnLigar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
class SquarePanel extends JPanel {
    @Override
    public Dimension getPreferredSize() {
        Container c = this.getParent();
        int size = Math.min(c.getHeight(), c.getWidth());
        Dimension d = new Dimension(size,size);
        return d;
    }

}