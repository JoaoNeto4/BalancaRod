
package br.com.view.pesquisa;

import br.com.bean.Pesagem;
import br.com.bean.Veiculos;
import br.com.dao.PesagemDao;
import br.com.view.cadastro.CadPesagem;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joao
 */
public class PesqPesagemParaSaida extends javax.swing.JDialog {

    private List<Pesagem> listaPesagem = new ArrayList<>();
    private CadPesagem parent;
    boolean selecionar = false;
  
    public PesqPesagemParaSaida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Pesquisa de Pesagens");
        this.setLocationRelativeTo(null);  // centraliza a tela
        atualizarTabela();
        rbPlaca.setSelected(true);
    }

    public PesqPesagemParaSaida(java.awt.Frame parent, boolean modal, CadPesagem aThis) {
        super(parent, modal);
        initComponents();
        this.setTitle("Pesquisa de Pesagens");
        this.parent=aThis;
        this.setLocationRelativeTo(null);  // centraliza a tela
        atualizarTabela();
        rbPlaca.setSelected(true);
        selecionar = true;
    }

    private Pesagem retornaObjeto(){
        Veiculos vei = new Veiculos();
        vei.setPlaca(txtPesquisa.getText());
        Pesagem pe = new Pesagem();
        pe.setVeiculo(vei);
    
        return pe;
    }
    
    public final void atualizarTabela(){
        try {
            listaPesagem = PesagemDao.pesquisarSaida(retornaObjeto());
            DefaultTableModel modelo = (DefaultTableModel) tabelaTela.getModel();
            modelo.setNumRows(0);

            for (Pesagem pe : listaPesagem) {
                modelo.addRow(new Object[]{
                    pe.getId(),
                    pe.getVeiculo().getPlaca(),
                    pe.getTransportador().getFantasia(),
                    pe.getDataHoraEtrada(),
                    pe.getNfe()
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
        rbPlaca = new javax.swing.JRadioButton();
        rbTrasnportador = new javax.swing.JRadioButton();
        txtPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTela = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        rbPlaca.setText("Placa");

        rbTrasnportador.setText("Transportador");

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyPressed(evt);
            }
        });

        tabelaTela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Placa", "Transportador", "Hora Entrada", "NFe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbPlaca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbTrasnportador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbPlaca)
                    .addComponent(rbTrasnportador)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyPressed
        atualizarTabela();
    }//GEN-LAST:event_txtPesquisaKeyPressed

    private void tabelaTelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaTelaMouseClicked
        if(evt.getClickCount() == 2){
            switch(evt.getButton()){
                case MouseEvent.BUTTON1:
                    if(selecionar){
                        Pesagem pesSel = listaPesagem.get(tabelaTela.getSelectedRow());
                        CadPesagem cad = (CadPesagem) parent;
                        cad.RecebeObjetoAgendamento(pesSel);
                        cad.setVisible(true);
                        this.dispose();
                        /*
                            int pesSelec = (int) tabelaTela.getValueAt(tabelaTela.getSelectedRow(), 0);
                            CadPesagem cad = (CadPesagem) parent;
                            cad.RecebeObjetoAgendamento(pesSelec);
                            cad.setVisible(true);
                            this.dispose();
                        */
                    }
                break;
                default:
                    System.out.println("2 vezes outro botao");
            }
        }
    }//GEN-LAST:event_tabelaTelaMouseClicked

  
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
            java.util.logging.Logger.getLogger(PesqPesagemParaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesqPesagemParaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesqPesagemParaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesqPesagemParaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesqPesagemParaSaida dialog = new PesqPesagemParaSaida(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbPlaca;
    private javax.swing.JRadioButton rbTrasnportador;
    private javax.swing.JTable tabelaTela;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
