
package br.com.view.pesquisa;

import br.com.bean.Produtos;
import br.com.dao.ProdutoDao;
import br.com.view.cadastro.CadAgendamento;
import br.com.view.cadastro.CadPesagem;
import br.com.view.cadastro.CadProdutos;
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
public class PesqProduto extends javax.swing.JDialog {

    private List<Produtos> listaProduto = new ArrayList<>();
   
    boolean editar = true;
    boolean pesqProduto=false;
    private CadPesagem parent;
    private CadAgendamento parentPesagem;
    private boolean produto=false;
    
    public PesqProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        this.setTitle("Pesquisa de Produto");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }

    public PesqProduto(java.awt.Frame parent, boolean modal, CadPesagem aThis) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        this.produto=true;
        this.parent=aThis;
        this.pesqProduto=true;
        this.editar=false;
        this.setTitle("Pesquisa de Seleção de Produto");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }
    
    public PesqProduto(java.awt.Frame parent, boolean modal, CadAgendamento aThis) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        this.produto=true;
        this.parentPesagem=aThis;
        this.pesqProduto=true;
        this.editar=false;
        this.setTitle("Pesquisa de Seleção de Produto");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }
    
    private Produtos retornaObjeto(){
        Produtos pn = new Produtos();
        pn.setProduto(txtPesquisa.getText());
    
        return pn;
    }
    
    public final void atualizarTabela(){
        try {
            listaProduto = ProdutoDao.pesquisar(retornaObjeto());
            DefaultTableModel modelo = (DefaultTableModel) tabelaTela.getModel();
            modelo.setNumRows(0);

            for (Produtos prod : listaProduto) {
                modelo.addRow(new Object[]{
                    prod.getId(),
                    prod.getProduto(),
                    prod.getUnidMed(),
                    prod.getObservacoes()
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
        jLabel1 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTela = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setText("Pesquisar");

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyPressed(evt);
            }
        });

        tabelaTela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Produto", "Unid. Medida", "Observações"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/lapis25x25.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditarMouseReleased(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/remove-25x25.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnExcluirMouseReleased(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/sair25x25.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesquisa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelar))
                .addGap(25, 25, 25))
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

    private void btnNovoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoMouseReleased
        CadProdutos prod = new CadProdutos((Frame) getParent(), true);
        prod.setVisible(true);
    }//GEN-LAST:event_btnNovoMouseReleased

    private void btnEditarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseReleased
        if(tabelaTela.getSelectedRow() != -1){
            Produtos pnSelecionado = listaProduto.get(tabelaTela.getSelectedRow());
            CadProdutos prod = new CadProdutos((Frame) getParent(), true, pnSelecionado);
            prod.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha para Editar");
        }
    }//GEN-LAST:event_btnEditarMouseReleased

    private void btnExcluirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseReleased
        if(tabelaTela.getSelectedRow() != -1){

            Produtos prod = new Produtos();
            ProdutoDao dao = new ProdutoDao();

            int aux = JOptionPane.showConfirmDialog(null, "Deseja Excluir?");
            if(aux==0){
                System.out.println(aux);
                prod.setId((int)tabelaTela.getValueAt(tabelaTela.getSelectedRow(), 0)); //0 faz referencia a primeira coluna da tabela,que é o que queremos
                try {
                    dao.excluir(prod);
                    atualizarTabela();
                } catch (SQLException ex) {
                    Logger.getLogger(PesqOperador.class.getName()).log(Level.SEVERE, null, ex);
                }

                atualizarTabela(); //para mostrar as informações na jtTabela
            }else{

            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir");
        }
    }//GEN-LAST:event_btnExcluirMouseReleased

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void tabelaTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaTelaMouseClicked
        if(evt.getClickCount() == 2){
            switch(evt.getButton()){
                case MouseEvent.BUTTON1:
                    if(editar){
                        Produtos pnSelecionado = listaProduto.get(tabelaTela.getSelectedRow());
                        CadProdutos cp = new CadProdutos((Frame) getParent(), true, pnSelecionado);
                        cp.setVisible(true);
                    }else{
                        Produtos prodSel = listaProduto.get(tabelaTela.getSelectedRow());
                        CadPesagem cad = (CadPesagem) parent;
                        cad.RecebeObjetoProduto(prodSel);
                        cad.setVisible(true);
                        this.dispose();
                    }
                
                break;
                default:
                    System.out.println("2 vezes outro botao");
            }
        }
    }//GEN-LAST:event_tabelaTelaMouseClicked

    private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyPressed
        atualizarTabela();
    }//GEN-LAST:event_txtPesquisaKeyPressed


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
            java.util.logging.Logger.getLogger(PesqProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesqProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesqProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesqProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesqProduto dialog = new PesqProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
