
package br.com.view.pesquisa;

import br.com.bean.ParceiroNegocio;
import br.com.bean.Pesagem;
import br.com.dao.ParceiroNegocioDao;
import br.com.view.cadastro.CadAgendamento;
import br.com.view.cadastro.CadOrigem;
import br.com.view.cadastro.CadParceiroNegocio;
import br.com.view.cadastro.CadPesagem;
import br.com.view.cadastro.CadVeiculo;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class PesqParceiroNegocio extends javax.swing.JDialog {

    private List<ParceiroNegocio> listaParceiro = new ArrayList<>();
    
    boolean editar = true;
    boolean pesqPN=false;
    private CadPesagem parent;
    private CadOrigem cadOrigemParent;
    private CadVeiculo cadVeiculoModal;
    private CadAgendamento parentAgenda;
    private boolean parceiro=false;
    private boolean origem=false;
    private boolean cadVeiculo=false;
    private boolean agenda=false;

    public PesqParceiroNegocio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        this.setTitle("Pesquisa de Parceiro de Negócio");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }

    public PesqParceiroNegocio(java.awt.Frame parent, boolean modal, CadPesagem aThis) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        this.parceiro=true;
        this.parent=aThis;
        this.pesqPN=true;
        this.editar=false;
        this.setTitle("Pesquisa de Seleção de Parceiro de Negócio");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }

    public PesqParceiroNegocio(java.awt.Frame parent, boolean modal, CadAgendamento aThis) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        //this.parceiro=true;
        this.parentAgenda=aThis;
        this.agenda=true;
        this.pesqPN=false;
        this.editar=false;
        this.setTitle("Pesquisa de Seleção de Parceiro de Negócio");
        this.setLocationRelativeTo(null);  // centraliza a tela
    }

    public PesqParceiroNegocio(java.awt.Frame parent, boolean modal, CadVeiculo aThis) {
        super(parent, modal);
        initComponents();
        cadVeiculoModal=aThis;
        atualizarTabela();
        this.setTitle("Pesquisa de Seleção de Parceiro de Negócio para Veículos");
        this.setLocationRelativeTo(null);  // centraliza a tela
        this.cadVeiculo=true;
        this.editar = false;
    }

    public PesqParceiroNegocio(java.awt.Frame parent, boolean modal, CadOrigem aThis) {
        super(parent, modal);
        initComponents();
        atualizarTabela();
        this.setTitle("Pesquisa de Seleção de Parceiro de Negócio de Fazenda");
        this.setLocationRelativeTo(null);  // centraliza a tela
        this.origem=true;
        this.editar = false;
        cadOrigemParent=aThis;
    }
    
    private ParceiroNegocio retornaObjeto(){
        ParceiroNegocio pn = new ParceiroNegocio();
        pn.setFantasia(txtPesquisa.getText());
    
        return pn;
    }

    public final void atualizarTabela(){
        try {
            listaParceiro = ParceiroNegocioDao.pesquisar(retornaObjeto());
            DefaultTableModel modelo = (DefaultTableModel) tabelaTela.getModel();
            modelo.setNumRows(0);

            for (ParceiroNegocio op : listaParceiro) {
                modelo.addRow(new Object[]{
                    op.getId(),
                    op.getFantasia(),
                    op.getCpf_cnpj(),
                    op.getCidade(),
                    op.getTelefone(),
                    op.getEmail()
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
        txtPesquisa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTela = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setText("Pesquisa");

        tabelaTela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Fantasia", "CPF/CNPJ", "Cidade", "Telefone", "Email"
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
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir)
                    .addComponent(btnCancelar)
                    .addComponent(btnEditar))
                .addGap(19, 19, 19))
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
        CadParceiroNegocio pn = new CadParceiroNegocio((Frame) getParent(), true);
        pn.setVisible(true);
    }//GEN-LAST:event_btnNovoMouseReleased

    private void btnEditarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseReleased
        if(tabelaTela.getSelectedRow() != -1){
            ParceiroNegocio pnSelecionado = listaParceiro.get(tabelaTela.getSelectedRow());
            CadParceiroNegocio pn = new CadParceiroNegocio((Frame) getParent(), true, pnSelecionado);
            pn.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha para Editar");
        }
    }//GEN-LAST:event_btnEditarMouseReleased

    private void btnExcluirMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExcluirMouseReleased
        if(tabelaTela.getSelectedRow() != -1){

            ParceiroNegocio op = new ParceiroNegocio();
            ParceiroNegocioDao dao = new ParceiroNegocioDao();

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
                        ParceiroNegocio pnSelecionado = listaParceiro.get(tabelaTela.getSelectedRow());
                        CadParceiroNegocio cp = new CadParceiroNegocio((Frame) getParent(), true, pnSelecionado);
                        cp.setVisible(true);
                    }else{
                        if(cadVeiculo){
                            ParceiroNegocio parSel = listaParceiro.get(tabelaTela.getSelectedRow());
                            CadVeiculo cad = (CadVeiculo) cadVeiculoModal;
                            
                            System.out.println(parSel.getId());
                            System.out.println(parSel.getFantasia());
                            cad.RecebeObjetoParceiro(parSel);
                            cad.setVisible(true);
                            this.dispose();
                        }else{
                            if(origem){
                                ParceiroNegocio parSel = listaParceiro.get(tabelaTela.getSelectedRow());
                                CadOrigem cad = (CadOrigem) cadOrigemParent;
                                cad.RecebeObjetoParceiro(parSel);
                                cad.setVisible(true);
                                this.dispose();
                            }else{
                                if(agenda){
                                    ParceiroNegocio parSel = listaParceiro.get(tabelaTela.getSelectedRow());
                                    CadAgendamento cad = (CadAgendamento) parentAgenda;
                                    cad.RecebeObjetoParceiro(parSel);
                                    cad.setVisible(true);
                                    this.dispose();
                                }else{
                                    ParceiroNegocio parSel = listaParceiro.get(tabelaTela.getSelectedRow());
                                    CadPesagem cad = (CadPesagem) parent;
                                    cad.RecebeObjetoParceiro(parSel);
                                    cad.setVisible(true);
                                    this.dispose();
                                }
                                
                            }
                        }
                    }
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
            java.util.logging.Logger.getLogger(PesqParceiroNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesqParceiroNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesqParceiroNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesqParceiroNegocio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesqParceiroNegocio dialog = new PesqParceiroNegocio(new javax.swing.JFrame(), true);
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
