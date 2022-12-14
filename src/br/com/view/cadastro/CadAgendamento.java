
package br.com.view.cadastro;

import br.com.bean.Operador;
import br.com.bean.OrigemFazenda;
import br.com.bean.ParceiroNegocio;
import br.com.bean.Pesagem;
import br.com.bean.Produtos;
import br.com.bean.Veiculos;
import br.com.dao.OrigemFazendaDao;
import br.com.dao.PesagemDao;
import br.com.view.pesquisa.PesqOrigem;
import br.com.view.pesquisa.PesqParceiroNegocio;
import br.com.view.pesquisa.PesqProduto;
import br.com.view.pesquisa.PesqVeiculo;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao
 */
public class CadAgendamento extends javax.swing.JDialog {

  
    private boolean salvar = true;
    private boolean andamento =true;
    private int id=0;
    private int IDparceiro=0;
    private int IDveiculo=0;
    private int IDproduto=0;
    private int IDoperador=0;
     private int IDtransport=0;
    private double pesoEntrada2=0;
    private double pesoSaida2=0;
    private String dataHoraEntrada = "";
    private String dataHoraSaida = "";
    
    
    
    public CadAgendamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Cadastro de Agendamento");
        this.setLocationRelativeTo(null);  // centraliza a tela
        
        this.desabilitaEdicao();
    }

    private void desabilitaEdicao(){
        this.rbComposta.setSelected(true);
        this.rbEntrada.setSelected(true);
        txtPlaca.setEditable(false);
        txtPnVeiculo.setEditable(false);
        txtTransportador.setEditable(false);
        txtProduto.setEditable(false);
        
    }
    
    private Boolean validaCampos(){
        
        return true;
    }
    
    private Boolean limparCampos(){
        
        return true;
    }

    private String dataHoraEntrada(){
        if(dataHoraEntrada.equals("") && this.rbEntrada.isSelected() ){
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataHora = sdf.format(dt);
            //System.out.println(datahora);
            return dataHora;
        }else{
            return dataHoraEntrada;
        }  
    }
    
    private String dataHoraSaida(){
        if(dataHoraSaida.equals("") && this.rbSaida.isSelected() ){
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dataHora = sdf.format(dt);
            //System.out.println(datahora);
            return dataHora;
        }else{
            return dataHoraSaida;
        }  
    }
    
    private Boolean emAndamento(){
        if(rbComposta.isSelected() && rbEntrada.isSelected()){
            return true;
        }else{
            return false;
        }
    }
    
    public void RecebeObjetoVeiculo(Veiculos vei){
        txtPlaca.setText(vei.getPlaca());
        txtPnVeiculo.setText(vei.getPn().getFantasia());
        this.IDveiculo=vei.getId();
    }
    
    public void RecebeObjetoParceiro(ParceiroNegocio par){
        txtTransportador.setText(par.getFantasia());
        this.IDparceiro=par.getId();
        populaCombobox(par);
        
        //*****
    }
    
    private void populaCombobox(ParceiroNegocio pn) {
        try {
            OrigemFazendaDao of = new OrigemFazendaDao();
            List<OrigemFazenda> lista =of.listar(pn);
            if(lista.isEmpty()){
                //cbBox.addItem("vazio");
            }else{
                for(OrigemFazenda item: lista){
                    cbBox.addItem(item.getOrigem());
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadOperador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void RecebeObjetoProduto(Produtos prod){
        txtProduto.setText(prod.getProduto());
        this.IDproduto=prod.getId();
    }
    
    private Pesagem retornaObjeto(){
        Pesagem pe = new Pesagem();
        ParceiroNegocio pn = new ParceiroNegocio();
        ParceiroNegocio transp = new ParceiroNegocio();
        Veiculos v = new Veiculos();
        Operador op = new Operador();
        Produtos prod = new Produtos();
        
        pn.setId(IDparceiro);
        transp.setId(IDtransport);
        v.setId(IDveiculo);
        op.setId(IDoperador);
        prod.setId(IDproduto);

        /*
        inserir data entrada apenas na insercao, alterar nao
        */
        pe.setDataHoraEtrada(dataHoraEntrada());
        pe.setDataHoraSaida(dataHoraSaida());
        
        pe.setTipoPesagem(grupoBotoes_TipoPesagem.getSelection().toString());
        pe.setAndamento(emAndamento());
        pe.setNfe(txtNfe.getText());
        pe.setValorNfe(Double.valueOf(txtValorNfe.getText()));
        pe.setPesoNfe(Double.valueOf(txtPesoNfe.getText()));
        pe.setLote(txtLote.getText());
        if(cbBox.getSelectedIndex()==0){
            pe.setOrigem("");
        }else{
            pe.setOrigem(cbBox.getSelectedItem().toString());
        }
        pe.setDestino(txtDestino.getText());
        pe.setMotorista(txtMotorista.getText());
        pe.setFotoCarga1("NoData");
        pe.setFotoCarga2("NoData");
        pe.setFotoEntrada("NoData");
        pe.setFotoSaida("NoData");
        pe.setObservacao(txtObservacao.getText());
        pe.setPn(pn);
        pe.setVeiculo(v);
        pe.setOperador(op);
        pe.setProduto(prod);
        pe.setTransportador(pn);
        
        return pe;
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotoes_TipoPesagem = new javax.swing.ButtonGroup();
        grupoBotoes_Operacao = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbSimples = new javax.swing.JRadioButton();
        rbComposta = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        rbEntrada = new javax.swing.JRadioButton();
        rbSaida = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        txtPnVeiculo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTransportador = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDestino = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtProduto = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtLote = new javax.swing.JTextField();
        btnPesqPlaca = new javax.swing.JButton();
        btnPesqProduto = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtMotorista = new javax.swing.JTextField();
        btnPesqTransp = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNfe = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPesoNfe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValorNfe = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Tipo de Pesagem", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        grupoBotoes_TipoPesagem.add(rbSimples);
        rbSimples.setText("Simples");
        rbSimples.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbSimplesMouseReleased(evt);
            }
        });

        grupoBotoes_TipoPesagem.add(rbComposta);
        rbComposta.setText("Composta");
        rbComposta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbCompostaMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbComposta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSimples)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbSimples)
                    .addComponent(rbComposta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(245, 245, 245));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""))), "Opera????o", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        grupoBotoes_Operacao.add(rbEntrada);
        rbEntrada.setText("Entrada");

        grupoBotoes_Operacao.add(rbSaida);
        rbSaida.setText("Saida");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbEntrada)
                .addGap(18, 18, 18)
                .addComponent(rbSaida)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbEntrada)
                    .addComponent(rbSaida))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Placa Ve??culo");

        jLabel5.setText("Parceiro Origem");

        jLabel6.setText("Local Origem");

        jLabel7.setText("Destino");

        jLabel8.setText("Produto");

        jLabel9.setText("Observa????o");

        txtObservacao.setColumns(20);
        txtObservacao.setRows(5);
        jScrollPane1.setViewportView(txtObservacao);

        jLabel10.setText("Lote");

        btnPesqPlaca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/lupa20x20.png"))); // NOI18N
        btnPesqPlaca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPesqPlacaMouseReleased(evt);
            }
        });

        btnPesqProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/lupa20x20.png"))); // NOI18N
        btnPesqProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPesqProdutoMouseReleased(evt);
            }
        });

        jLabel11.setText("Motorista");

        btnPesqTransp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/lupa20x20.png"))); // NOI18N
        btnPesqTransp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPesqTranspMouseReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(245, 245, 245));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "NFe", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("N??m.");

        jLabel2.setText("Peso");

        jLabel3.setText("Valor");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNfe))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesoNfe, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorNfe, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNfe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPesoNfe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtValorNfe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/salvar25x25.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSalvarMouseReleased(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/sair25x25.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });

        cbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "**** Selecione ****" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDestino)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnPesqTransp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPesqPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtPnVeiculo))
                                    .addComponent(txtTransportador)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPesqProduto)
                                .addGap(18, 18, 18)
                                .addComponent(txtProduto))
                            .addComponent(txtLote)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtMotorista)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                            .addComponent(cbBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPnVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesqPlaca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTransportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesqTransp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnPesqProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        if(validaCampos()){
            PesagemDao dao = new PesagemDao();
            try {
                if(salvar){
                    dao.inserir(retornaObjeto());
                    limparCampos();
                    this.dispose();
                }else{
                    dao.alterar(retornaObjeto());
                    limparCampos();
                    this.dispose();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadProdutos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSalvarMouseReleased

    private void rbSimplesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSimplesMouseReleased
            if(rbSimples.isSelected()){
                rbSaida.setSelected(false);
                rbEntrada.setSelected(false);
                rbEntrada.setEnabled(false);
                rbSaida.setEnabled(false);
            }
    }//GEN-LAST:event_rbSimplesMouseReleased

    private void rbCompostaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCompostaMouseReleased
        if(rbComposta.isSelected()){
            rbEntrada.setEnabled(true);
            rbSaida.setEnabled(true);
            rbEntrada.setSelected(true);
        }
    }//GEN-LAST:event_rbCompostaMouseReleased

    private void btnPesqPlacaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesqPlacaMouseReleased
        PesqVeiculo pes = new PesqVeiculo(null, true, this);
        pes.setVisible(true);
    }//GEN-LAST:event_btnPesqPlacaMouseReleased

    private void btnPesqTranspMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesqTranspMouseReleased
        PesqParceiroNegocio par = new PesqParceiroNegocio(null, true, this);
        par.setVisible(true);
    }//GEN-LAST:event_btnPesqTranspMouseReleased

    private void btnPesqProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesqProdutoMouseReleased
        PesqProduto par = new PesqProduto(null, true, this);
        par.setVisible(true);
    }//GEN-LAST:event_btnPesqProdutoMouseReleased

  
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
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadAgendamento dialog = new CadAgendamento(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnPesqPlaca;
    private javax.swing.JButton btnPesqProduto;
    private javax.swing.JButton btnPesqTransp;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbBox;
    private javax.swing.ButtonGroup grupoBotoes_Operacao;
    private javax.swing.ButtonGroup grupoBotoes_TipoPesagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbComposta;
    private javax.swing.JRadioButton rbEntrada;
    private javax.swing.JRadioButton rbSaida;
    private javax.swing.JRadioButton rbSimples;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtLote;
    private javax.swing.JTextField txtMotorista;
    private javax.swing.JTextField txtNfe;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtPesoNfe;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPnVeiculo;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtTransportador;
    private javax.swing.JTextField txtValorNfe;
    // End of variables declaration//GEN-END:variables
}
