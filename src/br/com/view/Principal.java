
package br.com.view;

import br.com.bean.Operador;
import br.com.conexao.Conexao;
import br.com.view.pesquisa.PesqOperador;
import br.com.view.pesquisa.PesqParceiroNegocio;
import br.com.view.pesquisa.PesqPesagem;
import br.com.view.pesquisa.PesqProduto;
import br.com.view.pesquisa.PesqVeiculo;
import br.com.view.utilidades.Backup;
import br.com.view.utilidades.Configuracoes;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
        try {
            conexao = Conexao.getConexao();
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setTitle("Principal");
        this.setLocationRelativeTo(null);  // centraliza a tela
        labelOperador.setText(operador.getNome());
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelOperador = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miSair = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        miOperador = new javax.swing.JMenuItem();
        miParceiro = new javax.swing.JMenuItem();
        miPesagem = new javax.swing.JMenuItem();
        miVeiculos = new javax.swing.JMenuItem();
        miProdutos = new javax.swing.JMenuItem();
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

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelOperador.setText("Root");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelOperador)
                .addContainerGap(1091, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelOperador)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 530, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuBar.setFont(new java.awt.Font("Nimbus Sans", 0, 18)); // NOI18N

        jMenu1.setText("Arquivo");
        jMenu1.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N

        miSair.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        miSair.setText("Sair");
        miSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miSairMouseReleased(evt);
            }
        });
        jMenu1.add(miSair);

        menuBar.add(jMenu1);

        jMenu5.setText("Pesquisa");
        jMenu5.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N

        miOperador.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        miOperador.setText("Operador");
        miOperador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miOperadorMouseReleased(evt);
            }
        });
        jMenu5.add(miOperador);

        miParceiro.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        miParceiro.setText("Parceiro de Negócio");
        miParceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miParceiroMouseReleased(evt);
            }
        });
        jMenu5.add(miParceiro);

        miPesagem.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        miPesagem.setText("Pesagens");
        miPesagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miPesagemMouseReleased(evt);
            }
        });
        jMenu5.add(miPesagem);

        miVeiculos.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        miVeiculos.setText("Veículos");
        miVeiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miVeiculosMouseReleased(evt);
            }
        });
        jMenu5.add(miVeiculos);

        miProdutos.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        miProdutos.setText("Produtos");
        miProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                miProdutosMouseReleased(evt);
            }
        });
        jMenu5.add(miProdutos);

        menuBar.add(jMenu5);

        jMenu4.setText("Relatórios");
        jMenu4.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        menuBar.add(jMenu4);

        jMenu3.setText("Ferramentas");
        jMenu3.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N

        jmBackup.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        jmBackup.setText("Backup");
        jmBackup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jmBackupMouseReleased(evt);
            }
        });
        jMenu3.add(jmBackup);

        jmConfiguracoes.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        jmConfiguracoes.setText("Configurações");
        jmConfiguracoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jmConfiguracoesMouseReleased(evt);
            }
        });
        jMenu3.add(jmConfiguracoes);
        jMenu3.add(jSeparator2);

        jMenuItem3.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        jMenuItem3.setText("Cameras");
        jMenu3.add(jMenuItem3);

        menuBar.add(jMenu3);

        jMenu2.setText("Help");
        jMenu2.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N

        jMenuItem2.setFont(new java.awt.Font("Liberation Sans", 0, 16)); // NOI18N
        jMenuItem2.setText("Sobre");
        jMenu2.add(jMenuItem2);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

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

    private void jmConfiguracoesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmConfiguracoesMouseReleased
        Configuracoes c = new Configuracoes(this, true);
        c.setVisible(true);
    }//GEN-LAST:event_jmConfiguracoesMouseReleased

    private void jmBackupMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jmBackupMouseReleased
        Backup bk = new Backup(this, true);
        bk.setVisible(true);
    }//GEN-LAST:event_jmBackupMouseReleased

    private void miOperadorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miOperadorMouseReleased
        PesqOperador p = new PesqOperador(this, true);
        p.setVisible(true);
    }//GEN-LAST:event_miOperadorMouseReleased

    private void miParceiroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miParceiroMouseReleased
        PesqParceiroNegocio p = new PesqParceiroNegocio(this, true);
        p.setVisible(true);
    }//GEN-LAST:event_miParceiroMouseReleased

    private void miPesagemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miPesagemMouseReleased
        PesqPesagem p = new PesqPesagem(this, true);
        p.setVisible(true);
    }//GEN-LAST:event_miPesagemMouseReleased

    private void miVeiculosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miVeiculosMouseReleased
        PesqVeiculo p = new PesqVeiculo(this, true);
        p.setVisible(true);
    }//GEN-LAST:event_miVeiculosMouseReleased

    private void miProdutosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miProdutosMouseReleased
        PesqProduto p = new PesqProduto(this, true);
        p.setVisible(true);
    }//GEN-LAST:event_miProdutosMouseReleased

    private void miSairMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_miSairMouseReleased
        System.exit(0);
    }//GEN-LAST:event_miSairMouseReleased

 
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
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem jmBackup;
    private javax.swing.JMenuItem jmConfiguracoes;
    private javax.swing.JLabel labelOperador;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miOperador;
    private javax.swing.JMenuItem miParceiro;
    private javax.swing.JMenuItem miPesagem;
    private javax.swing.JMenuItem miProdutos;
    private javax.swing.JMenuItem miSair;
    private javax.swing.JMenuItem miVeiculos;
    // End of variables declaration//GEN-END:variables
}
