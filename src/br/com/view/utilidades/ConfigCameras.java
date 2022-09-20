
package br.com.view.utilidades;

import br.com.bean.Cameras;
import br.com.dao.CamerasDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.opencv_core.Mat;

public class ConfigCameras extends javax.swing.JDialog {


    private int id;
    private boolean salvar=true;
    private List<Cameras> listaCam = new ArrayList<>();
    Cameras cameras = new Cameras();

    public ConfigCameras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Configuração de Cameras");
        this.setLocationRelativeTo(null);  // centraliza a tela
        atualizarCampos();
    }
    
    public Cameras retornaObjeto(){
        Cameras cam = new Cameras();
        cam.setCam01(txtCam01.getText());
        cam.setCam02(txtCam02.getText());
        cam.setCamEntrada(txtCamEntrada.getText());
        cam.setCamSaida(txtCamSaida.getText());
        cam.setAtivoC1(cbCam01.isSelected());
        cam.setAtivoC1(cbCam02.isSelected());
        cam.setAtivoEntrada(cbCamEntrada.isSelected());
        cam.setAtivoSaida(cbCamSaida.isSelected());
        cam.setId(id);
        return cam;
    }
    
    public void setaCampos(Cameras cam){
        txtCam01.setText(cam.getCam01());
        txtCam02.setText(cam.getCam02());
        txtCamEntrada.setText(cam.getCamEntrada());
        txtCamSaida.setText(cam.getCamSaida());
        cbCam01.setSelected(cam.isAtivoC1());
        cbCam02.setSelected(cam.isAtivoC2());
        cbCamEntrada.setSelected(cam.isAtivoEntrada());
        cbCamSaida.setSelected(cam.isAtivoSaida());
        this.id=cam.getId();
    }
    
    public final void atualizarCampos(){
        try {
            listaCam = CamerasDao.listar();
            if(listaCam.isEmpty()){              
                JOptionPane.showMessageDialog(null, "Você ainda não possui Cameras cadastradas!");
            }else{
                salvar=false;
                cameras = listaCam.get(0);
                setaCampos(cameras);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean validaTeste(){
        int i=0;
        if(cbCam01.isSelected() && !cbCam02.isSelected() && !cbCamEntrada.isSelected() && !cbCamSaida.isSelected()){
            return true;
        }
        if(!cbCam01.isSelected() && cbCam02.isSelected() && !cbCamEntrada.isSelected() && !cbCamSaida.isSelected()){
            return true;
        }
        if(!cbCam01.isSelected() && !cbCam02.isSelected() && cbCamEntrada.isSelected() && !cbCamSaida.isSelected()){
            return true;
        }
        if(!cbCam01.isSelected() && !cbCam02.isSelected() && !cbCamEntrada.isSelected() && cbCamSaida.isSelected()){
            return true;
        }
        JOptionPane.showMessageDialog(null, "Para testar as cameras selecione apenas uma por vez!");
        return false;
    }
    
    public String testaCamera(){
        if(cbCam01.isSelected() && !cbCam02.isSelected() && !cbCamEntrada.isSelected() && !cbCamSaida.isSelected()){
            if(cameras.getCam01().equals("")){
                JOptionPane.showMessageDialog(null, "Não possui camera configurada");
            }else{
                return cameras.getCam01();
            }
        }
        if(!cbCam01.isSelected() && cbCam02.isSelected() && !cbCamEntrada.isSelected() && !cbCamSaida.isSelected()){
            if(cameras.getCam02().equals("")){
                JOptionPane.showMessageDialog(null, "Não possui camera configurada");
            }else{
                return cameras.getCam01();
            }
        }
        if(!cbCam01.isSelected() && !cbCam02.isSelected() && cbCamEntrada.isSelected() && !cbCamSaida.isSelected()){
            if(cameras.getCamEntrada().equals("")){
                JOptionPane.showMessageDialog(null, "Não possui camera configurada");
            }else{
                return cameras.getCam01();
            }
        }
        if(!cbCam01.isSelected() && !cbCam02.isSelected() && !cbCamEntrada.isSelected() && cbCamSaida.isSelected()){
            if(cameras.getCamSaida().equals("")){
                JOptionPane.showMessageDialog(null, "Não possui camera configurada");
            }else{
                return cameras.getCam01();
            }
        }
        return null;
    }
    
    public String retornaDataHora(){
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String dataHora = sdf.format(dt);
        return dataHora;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCam01 = new javax.swing.JTextField();
        txtCam02 = new javax.swing.JTextField();
        txtCamEntrada = new javax.swing.JTextField();
        txtCamSaida = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnTestar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbCam01 = new javax.swing.JCheckBox();
        cbCam02 = new javax.swing.JCheckBox();
        cbCamEntrada = new javax.swing.JCheckBox();
        cbCamSaida = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSalvarMouseReleased(evt);
            }
        });

        btnTestar.setText("Testar");
        btnTestar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTestarMouseReleased(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });

        cbCam01.setText("Camera 01");

        cbCam02.setText("Camera 02");

        cbCamEntrada.setText("Camera Entrada");

        cbCamSaida.setText("Camera Saida");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCam02)
                    .addComponent(cbCam01)
                    .addComponent(cbCamEntrada)
                    .addComponent(cbCamSaida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCam02)
                            .addComponent(txtCam01, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCamSaida)
                            .addComponent(txtCamEntrada, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTestar)
                        .addGap(50, 50, 50)
                        .addComponent(btnSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(313, 313, 313))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCam01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCam01))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCam02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCam02))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCamEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCamEntrada))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCamSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCamSaida))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnTestar)
                    .addComponent(btnCancelar))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseReleased
   
        CamerasDao cam = new CamerasDao();
        try {
            if(salvar){
                cam.inserir(retornaObjeto());
                this.dispose();
            }else{
                cam.alterar(retornaObjeto());
                this.dispose();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConfigCameras.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }//GEN-LAST:event_btnSalvarMouseReleased

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void btnTestarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTestarMouseReleased

        if(validaTeste()){
            
            //java.util.Date dt = new java.util.Date();
            //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String dataHora = sdf.format(dt);
            OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();

            //OpenCVFrameGrabber camera = new OpenCVFrameGrabber("rtsp://admin:campos159@200.150.127.211:37715/cam/realmonitor?channel=1&subtype=0");
            FFmpegFrameGrabber camera = new FFmpegFrameGrabber(testaCamera());
            System.out.println(cameras.getCam01());

            try {
                camera.start();
                CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
                Mat imagemColorido  = new Mat();
                Frame frameCapturado = camera.grab();

                imagemColorido = convertemat.convert(frameCapturado);


                imwrite("src/br/com/teste/"+this.retornaDataHora()+".jpg", imagemColorido);
                System.out.println("foto capturada\n");
                 if (cFrame.isVisible()){
                    cFrame.showImage(frameCapturado);

                    //cFrame.showImage(camera.grabSamples());

                }
                cFrame.waitKey(2000);
                cFrame.dispose();
                camera.stop();
            } catch (FrameGrabber.Exception ex) {
                JOptionPane.showMessageDialog(null, "");
                //Logger.getLogger(ConfigCameras.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConfigCameras.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        

        
        
    }//GEN-LAST:event_btnTestarMouseReleased


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
            java.util.logging.Logger.getLogger(ConfigCameras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfigCameras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfigCameras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfigCameras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConfigCameras dialog = new ConfigCameras(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnTestar;
    private javax.swing.JCheckBox cbCam01;
    private javax.swing.JCheckBox cbCam02;
    private javax.swing.JCheckBox cbCamEntrada;
    private javax.swing.JCheckBox cbCamSaida;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCam01;
    private javax.swing.JTextField txtCam02;
    private javax.swing.JTextField txtCamEntrada;
    private javax.swing.JTextField txtCamSaida;
    // End of variables declaration//GEN-END:variables
}
