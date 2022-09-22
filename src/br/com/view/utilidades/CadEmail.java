
package br.com.view.utilidades;

import br.com.bean.Email;
import br.com.dao.EmailDao;
import br.com.email.EnvioEmail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.swing.JOptionPane;

/**
 *
 * @author joao
 */
public class CadEmail extends javax.swing.JDialog {

    private int id=0;
    private boolean salvar=true;
    private List<Email> listaEmail = new ArrayList<>();
    Email email = new Email();
    
    public CadEmail(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Email de disparo automático");
        this.setLocationRelativeTo(null);  // centraliza a tela
        rbSSL.setSelected(true);
        rbTLS.setEnabled(false);
        atualizarCampos();
    }
    
    public Email retornaObjeto(){
        Email em = new Email();
        em.setEmail(txtEmail.getText());
        em.setSenha(txtSenha.getText());
        em.setAtivo(cbAtivo.isSelected());
        em.setPorta(Integer.parseInt(txtPorta.getText()));
        em.setServidor(txtServidor.getText());
        if(rbSSL.isSelected() && !rbTLS.isSelected()){
            em.setSeguranca("SSL");
        }else{
            em.setSeguranca("TLS");
        }
        em.setId(id);
        return em;
    }
    
    public void setaCampos(Email em){
        txtEmail.setText(em.getEmail());
        txtSenha.setText(em.getSenha());
        cbAtivo.setSelected(em.isAtivo());
        txtPorta.setText(String.valueOf(em.getPorta()));
        txtServidor.setText(em.getServidor());
        if(em.getSeguranca().equals("SSL")){
            rbSSL.setSelected(true);
        }else{
            rbTLS.setSelected(true);
        }
        this.id=em.getId();
    }
    
    public final void atualizarCampos(){
        try {
            listaEmail = EmailDao.listar();
            if(listaEmail.isEmpty()){              
                JOptionPane.showMessageDialog(null, "Você ainda não possui Emails cadastradas!");
            }else{
                salvar=false;
                email = listaEmail.get(0);
                setaCampos(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean validaCampos(){
        if(txtEmail.getText().equals("") || txtSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo Email e Senha não podem ser vazios");
            return false;
        }
        if(txtServidor.getText().equals("") || txtPorta.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Campo Servidor e Porta não podem ser vazios");
            return false;
        }
        return true;
    }
    
    public void enviaEmail(String destinatario) throws SQLException{
        Email email = EmailDao.retornaInfoEmail(1);

        final String remetente = email.getEmail(); ////email de quem envia(válido)
        final String senha = email.getSenha(); // senha de quem envia
        final int porta = email.getPorta();
        final String servidor = email.getServidor();
        //final String seguranca = email.getSeguranca();
        //final String destinatario = "desenvolvimento2@ativusgestao.com.br"; // quem recebe

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", servidor); //SMTP Host
        props.put("mail.smtp.socketFactory.port", porta); // Porta SSL
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); // SMTP Autenticação ativa
        props.put("mail.smtp.port", porta); //SMTP Porta

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Sessão criada");
        EnvioEmail.enviaEmail(session, destinatario, remetente, "SSLEmail Assunto", "SSLEmail Corpo Email");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        txtSenha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbAtivo = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        rbTLS = new javax.swing.JRadioButton();
        rbSSL = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtServidor = new javax.swing.JTextField();
        txtPorta = new javax.swing.JTextField();
        btnTestar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setText("Email");

        jLabel2.setText("Senha");

        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSalvarMouseReleased(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });

        cbAtivo.setText("Ativo");

        jLabel3.setText("Servidor");

        jLabel4.setText("Porta");

        buttonGroup1.add(rbTLS);
        rbTLS.setText("TLS");

        buttonGroup1.add(rbSSL);
        rbSSL.setText("SSL");

        jLabel5.setText("Segurança");

        btnTestar.setText("Testar");
        btnTestar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTestarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSenha)
                    .addComponent(txtEmail)
                    .addComponent(txtServidor)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTestar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbAtivo)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(rbSSL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbTLS)))
                        .addGap(0, 107, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAtivo)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(rbSSL)
                        .addComponent(rbTLS)))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnTestar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void btnSalvarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseReleased
        EmailDao cam = new EmailDao();
        try {
            if(salvar){
                cam.inserir(retornaObjeto());
                this.dispose();
            }else{
                cam.alterar(retornaObjeto());
                this.dispose();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSalvarMouseReleased

    private void btnTestarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTestarMouseReleased
        
        if(validaCampos()){
            try {
                String destinatario = JOptionPane.showInputDialog( " Digite um email para teste de envio " );
                enviaEmail(destinatario);
            } catch (SQLException ex) {
                Logger.getLogger(CadEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_btnTestarMouseReleased

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(CadEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadEmail.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadEmail dialog = new CadEmail(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbAtivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbSSL;
    private javax.swing.JRadioButton rbTLS;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtPorta;
    private javax.swing.JTextField txtSenha;
    private javax.swing.JTextField txtServidor;
    // End of variables declaration//GEN-END:variables
}
