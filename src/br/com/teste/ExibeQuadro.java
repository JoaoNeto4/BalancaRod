
package br.com.teste;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author joao
 */
public class ExibeQuadro implements Runnable {
    VideoCaptura webCam;
    JLabel jlbQuadro;
    int cont =1;
    public ExibeQuadro(VideoCaptura cam, JLabel label){
        this.webCam = cam;
        this.jlbQuadro = label;
    }
    @Override
    public void run() {
        while(webCam.video.isOpened()){
            Icon icon = new ImageIcon(webCam.capturaQuadroBufferedImage());
            this.jlbQuadro.setIcon(icon);
            this.jlbQuadro.repaint();
            try {
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                Logger.getLogger(ExibeQuadro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}