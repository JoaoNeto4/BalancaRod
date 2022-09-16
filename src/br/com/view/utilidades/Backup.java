
package br.com.view.utilidades;

import br.com.config.Criptografia;
import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Backup extends javax.swing.JDialog {


    public Backup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Backup");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }

    public static String procuraEspaco(String s){
        String temp="";
       for(int i=0; i<s.length(); i++){
          temp=s.replace(" ", "%");
       }
        return temp;
    }
    
    public void exportar() throws FileNotFoundException, IOException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException{
  
        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("/home/joao/NetBeansProjects/Balanca/src/br/com/config/config.properties");
        prop.load(file);
        
        String st = txtCaminhoExport.getText();
        //String nome = "\\backup.sql";
        String nome = "/backup.sql";
        String backup = "";
        
        if(st.trim().length() != 0 ){
            try {
                backup = "mysqldump --opt -u "+prop.getProperty("prop.server.login")+" -p"+Criptografia.decrypt(prop.getProperty("prop.server.password"))+" -h "+prop.getProperty("prop.server.host")+" -P "+prop.getProperty("prop.server.port")+" -B "+prop.getProperty("prop.server.bd")+" -r "+st+nome;
                //backup = "mysqldump --opt -u "+Conectar.getUser()+" -p"+Conectar.getPass()+" -B "+Conectar.getBd()+" -r "+st+nome;
                //backup = "mysqldump -h "+Conectar.getEnde()+" -u root -p "+Conectar.getBd()+" > "+st+nome;
                //backup = "mysqldump --host="+Conectar.getEnde()+" --user="+Conectar.getUser()+" --password="+Conectar.getPass()+" "+Conectar.getBd()+" > "+st+nome;
                System.out.println(backup);
                Runtime rt = Runtime.getRuntime();
                rt.exec(backup);
               
                JOptionPane.showMessageDialog(null, "Exportado com sucesso!");
            } catch (HeadlessException | IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    public void importar() throws FileNotFoundException, IOException{
        
        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("/home/joao/NetBeansProjects/Balanca/src/br/com/config/config.properties");
        prop.load(file);
        
        String st = txtCaminhoImport.getText();
       
        
        if(st.trim().length() != 0 ){
            try {
                //backup = "cmd /c mysql -u"+Conectar.getUser()+" -p"+Conectar.getPass()+" "+Conectar.getBd()+" < "+st;
                //backup = "mysql -h "+Conectar.getEnde()+" -u "+Conectar.getUser()+" -p"+Conectar.getPass()+" "+Conectar.getBd()+" < "+st;
                //backup = "mysql -h "+Conectar.getEnde()+" -u "+Conectar.getUser()+" -p"+Conectar.getPass()+" --default_character_set utf8 "+Conectar.getBd()+" < "+st;
                //backup = "mysql -u "+Conectar.getUser()+" -p"+Conectar.getPass()+" -h "+Conectar.getEnde()+" "+Conectar.getBd()+" < "+st;
                //backup = "mysql -opt -u "+Conectar.getUser()+" -p"+Conectar.getPass()+" < "+st;
                //backup = "mysql -h localhost -u root -proot1234 BD_ClinicaMedica < /home/melena/Documentos/backup.sql";
                //Runtime.getRuntime().exec("mysql -h localhost -u root -proot1234 BD_ClinicaMedica < /home/melena/Documentos/backup.sql");
                //backup = "/bin/bash -c 'mysql -u root -proot1234 -f BD_ClinicaMedica < /home/melena/Documentos/backup.sql'";
                //   Runtime rt = Runtime.getRuntime();
                //   rt.exec(backup);
                //poderia ser o gnome-terminal
               String[] backup=new String[]{"xterm","-e","mysql -h"+prop.getProperty("prop.server.host")+" -P"+prop.getProperty("prop.server.port")+" -u"+prop.getProperty("prop.server.login")+" -p"+Criptografia.decrypt(prop.getProperty("prop.server.password"))+" "+prop.getProperty("prop.server.bd")+" < "+st};
               Process proc=Runtime.getRuntime().exec(backup);
               proc.waitFor();
               int res=proc.exitValue();
               if(res==0){
                   JOptionPane.showMessageDialog(null, "Importado com Sucesso");
               }else{
                   JOptionPane.showMessageDialog(null, "Erro ao importar");
               }

            // System.out.println(backup);
            //JOptionPane.showMessageDialog(null, "Importado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                ex.printStackTrace();
            }
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtCaminhoExport = new javax.swing.JTextField();
        btnSelecionaCaminhoExport = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtCaminhoImport = new javax.swing.JTextField();
        btnSelecionaArquivoImport = new javax.swing.JButton();
        btnImportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Exportar Backup"));

        btnSelecionaCaminhoExport.setText("Diretório");
        btnSelecionaCaminhoExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSelecionaCaminhoExportMouseReleased(evt);
            }
        });

        btnExportar.setText("Exportar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCaminhoExport, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnSelecionaCaminhoExport, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaminhoExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelecionaCaminhoExport))
                .addGap(18, 18, 18)
                .addComponent(btnExportar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar Backup"));

        btnSelecionaArquivoImport.setText("Arquivo");
        btnSelecionaArquivoImport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSelecionaArquivoImportMouseReleased(evt);
            }
        });

        btnImportar.setText("Importar");
        btnImportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnImportarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCaminhoImport, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnSelecionaArquivoImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCaminhoImport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSelecionaArquivoImport))
                .addGap(18, 18, 18)
                .addComponent(btnImportar)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionaArquivoImportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelecionaArquivoImportMouseReleased
        JFileChooser ch = new JFileChooser();
        FileNameExtensionFilter f = new FileNameExtensionFilter("SQL", "sql");//sql por que é a extensao do arquivo de backup
        ch.setFileFilter(f);

        int se = ch.showOpenDialog(null);
        if(se == JFileChooser.APPROVE_OPTION){
            String st = ch.getSelectedFile().getPath();
            txtCaminhoImport.setText(st);
        }
    }//GEN-LAST:event_btnSelecionaArquivoImportMouseReleased

    private void btnImportarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportarMouseReleased
        try {
            importar();
        } catch (IOException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnImportarMouseReleased

    private void btnSelecionaCaminhoExportMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSelecionaCaminhoExportMouseReleased
        JFileChooser ch = new JFileChooser();
        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int se = ch.showSaveDialog(null);
        if(se == JFileChooser.APPROVE_OPTION){
            String st = ch.getSelectedFile().getPath();

            //String caminho=procuraEspaco(st);
            txtCaminhoExport.setText(st);
        }
    }//GEN-LAST:event_btnSelecionaCaminhoExportMouseReleased


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
            java.util.logging.Logger.getLogger(Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Backup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Backup dialog = new Backup(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton btnSelecionaArquivoImport;
    private javax.swing.JButton btnSelecionaCaminhoExport;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtCaminhoExport;
    private javax.swing.JTextField txtCaminhoImport;
    // End of variables declaration//GEN-END:variables
}
