
package br.com.view;

import br.com.bean.Operador;
import br.com.conexao.Conexao;
import br.com.view.utilidades.Backup;
import br.com.view.utilidades.Configuracoes;
import com.sun.xml.internal.bind.v2.TODO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author joao
 */
public class Principal extends javax.swing.JFrame {

    // ----->>   TODO: Remover no final <<--------
    public Principal() {
        initComponents();
        this.setTitle("Principal");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }

    Connection conexao = null;
    
    public Principal(Operador operador) throws SQLException {
        initComponents();
        conexao = Conexao.getConexao();
        this.setTitle("Principal");
        this.setLocationRelativeTo(null);  // centraliza a tela
        labelOperador.setText(operador.getNome());
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        labelOperador = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jmBackup = new javax.swing.JMenuItem();
        jmConfiguracoes = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(245, 245, 245));

        labelOperador.setText("Root");

        jMenu1.setText("Arquivo");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu5.setText("Cadastros");
        jMenuBar1.add(jMenu5);

        jMenu4.setText("Relatórios");
        jMenuBar1.add(jMenu4);

        jMenu3.setText("Ferramentas");

        jmBackup.setText("Backup");
        jmBackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jmBackupMouseReleased(evt);
            }
        });
        jMenu3.add(jmBackup);

        jmConfiguracoes.setText("Configurações");
        jmConfiguracoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jmConfiguracoesMouseReleased(evt);
            }
        });
        jMenu3.add(jmConfiguracoes);
        jMenu3.add(jSeparator2);

        jMenuItem3.setText("Cameras");
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Help");

        jMenuItem2.setText("Sobre");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelOperador)
                .addContainerGap(1093, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(541, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(labelOperador)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmConfiguracoesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmConfiguracoesMouseReleased
        Configuracoes c = new Configuracoes(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jmConfiguracoesMouseReleased

    private void jmBackupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmBackupMouseReleased
        Backup bk = new Backup(this, true);
        bk.setVisible(true);
    }//GEN-LAST:event_jmBackupMouseReleased

 
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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem jmBackup;
    private javax.swing.JMenuItem jmConfiguracoes;
    private javax.swing.JLabel labelOperador;
    // End of variables declaration//GEN-END:variables
}
