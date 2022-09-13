
package br.com.view.pesquisa;

import br.com.bean.Operador;
import br.com.dao.OperadorDao;
import br.com.view.cadastro.CadOperador;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joao
 */
public class PesqOperador extends javax.swing.JDialog {

   private List<Operador> listaOperador = new ArrayList<>();
    
    public PesqOperador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        this.setTitle("Pesquisa de Operador");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }
    
    private Operador retornaObjeto(){
        Operador p = new Operador();
        p.setNome(txtPesqOperador.getText());
    
        return p;
    }

    public final void atualizarTabela(){
        try {
            listaOperador = OperadorDao.pesquisar(retornaObjeto());
            DefaultTableModel modelo = (DefaultTableModel) tabelaTela.getModel();
            modelo.setNumRows(0);

            for (Operador op : listaOperador) {
                modelo.addRow(new Object[]{
                    op.getId(),
                    op.getNome(),
                    op.isAtivo(),
                    op.getFuncao(),
                    op.getPermissao().getNivel(),
                    op.getObservacao()
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtPesqOperador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTela = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        txtPesqOperador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesqOperadorKeyPressed(evt);
            }
        });

        tabelaTela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Login", "Ativo", "Função", "Permissão", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaTela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaTelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaTela);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/addVerde25x25.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNovoMouseReleased(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/remove-25x25.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnExcluirMouseReleased(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/lapis25x25.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditarMouseReleased(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/sair25x25.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });

        jLabel1.setText("Pesquisa Login");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesqOperador)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesqOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir)
                    .addComponent(btnEditar)
                    .addComponent(btnCancelar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesqOperadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqOperadorKeyPressed
        atualizarTabela();
    }//GEN-LAST:event_txtPesqOperadorKeyPressed

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void btnNovoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoMouseReleased
        CadOperador op = new CadOperador((Frame) getParent(), true);
        op.setVisible(true);
    }//GEN-LAST:event_btnNovoMouseReleased

    private void btnExcluirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseReleased
        if(tabelaTela.getSelectedRow() != -1){

            Operador op = new Operador();
            OperadorDao dao = new OperadorDao();

            int aux = JOptionPane.showConfirmDialog(null, "Deseja Excluir?");
            if(aux==0){
                System.out.println(aux);
                op.setId((int)tabelaTela.getValueAt(tabelaTela.getSelectedRow(), 0)); //0 faz referencia a primeira coluna da tabela,que é o que queremos
                try {
                    dao.excluir(op);
                    atualizarTabela();
                } catch (SQLException ex) {
                    Logger.getLogger(PesqOperador.class.getName()).log(Level.SEVERE, null, ex);
                }

                atualizarTabela(); //para mostrar as informações na jtTabela
            }else{

            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um produto para excluir");
        }
    }//GEN-LAST:event_btnExcluirMouseReleased

    private void btnEditarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseReleased
        Operador opSelecionado = listaOperador.get(tabelaTela.getSelectedRow());
        CadOperador co = new CadOperador((Frame) getParent(), true, opSelecionado);
        co.setVisible(true);
    }//GEN-LAST:event_btnEditarMouseReleased

    private void tabelaTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaTelaMouseClicked
        if(evt.getClickCount() == 2){
            switch(evt.getButton()){
                case MouseEvent.BUTTON1:

                    Operador opSelecionado = listaOperador.get(tabelaTela.getSelectedRow());
                    CadOperador cp = new CadOperador((Frame) getParent(), true, opSelecionado);
                    cp.setVisible(true);
                
                break;
                default:
                    System.out.println("2 vezes outro botao");
            }
        }
    }//GEN-LAST:event_tabelaTelaMouseClicked

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
            java.util.logging.Logger.getLogger(PesqOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesqOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesqOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesqOperador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesqOperador dialog = new PesqOperador(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaTela;
    private javax.swing.JTextField txtPesqOperador;
    // End of variables declaration//GEN-END:variables
}
