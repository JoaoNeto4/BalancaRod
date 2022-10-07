
package br.com.teste;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import sun.security.pkcs11.wrapper.Functions;





/**
 *
 * @author joao
 */
public class HelloApp {
    private JFrame frame;
    private JPanel panel;
    final JLabel vidpanel1;
    ImageIcon image;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                HelloApp window = new HelloApp();
                window.frame.setVisible(true);
            }
        });
    }

    public void playVideo() throws InterruptedException{
        Mat inFrame = new Mat();
        VideoCapture camera = new VideoCapture(0);
        
        
        while (true) {
            if (!camera.read(inFrame))
                break;
            //Imgproc.resize(inFrame, inFrame, new Size(Config.FRAME_WIDTH, Config.FRAME_HEIGHT), 0., 0., Imgproc.INTER_LINEAR);

            //... processing frame

            //camera.grab();
            //ImageIcon image = new ImageIcon(Functions.Mat2bufferedImage(inFrame));
            vidpanel1.setIcon(image);
            vidpanel1.repaint();
        }
    }

    public HelloApp() {
        frame = new JFrame("MULTIPLE-TARGET TRACKING");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);//new FlowLayout() 
        frame.setResizable(false);
        frame.setBounds(50, 50, 800, 500);
        frame.setLocation(
            (3 / 4) * Toolkit.getDefaultToolkit().getScreenSize().width,
            (3 / 4) * Toolkit.getDefaultToolkit().getScreenSize().height
        );
        frame.setVisible(true);
        vidpanel1 = new JLabel();

        panel = new JPanel();
        panel.setBounds(11, 39, 593, 371);
        panel.add(vidpanel1, BorderLayout.CENTER);
        
        
        frame.getContentPane().add(panel);
        JButton btnStart = new JButton("START / REPLAY");
        panel.add(btnStart, BorderLayout.PAGE_START);
        
        btnStart.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            playVideo();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }
}
