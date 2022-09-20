
package br.com.view.cadastro;

import br.com.bean.Cameras;
import br.com.bean.Operador;
import br.com.bean.ParceiroNegocio;
import br.com.bean.Pesagem;
import br.com.bean.Produtos;
import br.com.bean.Veiculos;
import br.com.dao.CamerasDao;
import br.com.dao.PesagemDao;
import br.com.view.pesquisa.PesqParceiroNegocio;
import br.com.view.pesquisa.PesqPesagemParaSaida;
import br.com.view.pesquisa.PesqProduto;
import br.com.view.pesquisa.PesqVeiculo;
import br.com.view.utilidades.ConfigCameras;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import org.bytedeco.opencv.opencv_core.Mat;

/**
 *
 * @author joao
 */
public class CadPesagem extends javax.swing.JDialog {

  
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
    private String dataHoraEntrada = null;
    private String dataHoraSaida = "";
    private List<Cameras> listaCam = new ArrayList<>();
    Cameras cameras = new Cameras();
    private String dirCam01="NotEnabled";
    private String dirCam02="NotEnabled";
    private String DIRETORIOFOTOS="/home/joao/Downloads/Fotos/";
    
    
    public CadPesagem(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Pesagem Manual");
        this.setLocationRelativeTo(null);  // centraliza a tela
        this.desabilitaEdicao();
        this.carregaCameras();
        txtPesoBal.setText("44.000");
    }

    private void desabilitaEdicao(){
        this.rbComposta.setSelected(true);
        this.rbEntrada.setSelected(true);
        txtPlaca.setEditable(false);
        txtTranpVeiculo.setEditable(false);
        txtParceiro.setEditable(false);
        txtProduto.setEditable(false);
        txtPesoEntrada.setEditable(false);
        txtPesoSaida.setEditable(false);
        txtPesoLiq.setEditable(false);
        txtPesoBal.setEditable(false);
        
    }
    
    private Boolean validaCampos(){
        
        return true;
    }
    
    private Boolean limparCampos(){
        
        return true;
    }
    
    /*
    LocalDate data = LocalDate.now();
        DateTimeFormatter saida_formatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String hoje = data.format(saida_formatada);
        String[] dataSeparada = hoje.split("/");
        LocalDate dat = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
        ag.setDataHoje(java.sql.Date.valueOf(dat));
        
        java.util.Date pega = dataAgend.getDate();
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        String dataAG = form.format(pega);
        String[] dataSepAG = dataAG.split("/");
        LocalDate datAG = LocalDate.of(Integer.parseInt(dataSepAG[2]), Integer.parseInt(dataSepAG[1]), Integer.parseInt(dataSepAG[0]));
        
    */
    private String dataHoraEntrada(){
        if(this.rbEntrada.isSelected() ){
            salvar = true;
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
        String dataHora="";
        if( this.rbSaida.isSelected() ){
            salvar = false;
            java.util.Date dt = new java.util.Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dataHora = sdf.format(dt);
            //System.out.println(datahora);
            return dataHora;
        }else{
            dataHora=null;
            return dataHora;
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
        txtTranpVeiculo.setText(vei.getPn().getFantasia());
        this.IDveiculo=vei.getId();
        this.IDtransport=vei.getPn().getId();
    }
    
    public void RecebeObjetoParceiro(ParceiroNegocio par){
        txtParceiro.setText(par.getFantasia());
        this.IDparceiro=par.getId();
    }
    
    public void RecebeObjetoProduto(Produtos prod){
        txtProduto.setText(prod.getProduto());
        this.IDproduto=prod.getId();
    }
    
   
    public void RecebeObjetoAgendamento(Pesagem pes){

            salvar=false;
            /*
            ParceiroNegocio pn = new ParceiroNegocio();
            ParceiroNegocio transp = new ParceiroNegocio();
            Veiculos v = new Veiculos();
            Operador op = new Operador();
            Produtos prod = new Produtos();
            this.id=pes.getId();
            */
            
            this.id=pes.getId();
            
            this.IDoperador=pes.getOperador().getId();//*************************************************************pegar do sistema********************* 
            
            IDveiculo=pes.getVeiculo().getId();
            txtTranpVeiculo.setText(pes.getTransportador().getFantasia());
            IDtransport=pes.getTransportador().getId();
            txtPlaca.setText(pes.getVeiculo().getPlaca());
            
            txtParceiro.setText(pes.getPn().getFantasia());
            IDparceiro=pes.getPn().getId();
            
            txtNfe.setText(pes.getNfe());
            txtPesoNfe.setText(String.valueOf(pes.getPesoNfe()));
            txtValorNfe.setText(String.valueOf(pes.getValorNfe()));
            
            txtProduto.setText(pes.getProduto().getProduto());
            IDproduto=pes.getProduto().getId();
            
            txtDestino.setText(pes.getDestino());
            
            txtLote.setText(pes.getLote());
            
            txtMotorista.setText(pes.getMotorista());
            
            txtObservacao.setText(pes.getObservacao());
            
            dataHoraEntrada=pes.getDataHoraEtrada();
            
            if(pes.getTipoPesagem().equals("composta")){
                rbComposta.setSelected(true);
                rbSaida.setSelected(true);
            }
            Double peso1=pes.getPesoEnt1();
            Double peso2=pes.getPesoEnt2();
            Double soma=peso1+peso2;
            txtPesoEntrada.setText(Double.toString(soma));
            
        
   
    }
    
    public String retornaDataHoraFoto(){
        String dataCorrente = "";
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd-HH:mm");
        dataCorrente = sdf.format(dt);
        return dataCorrente;
    }
    
    public final void carregaCameras(){
        try {
            listaCam = CamerasDao.listar();
            if(listaCam.isEmpty()){              
                JOptionPane.showMessageDialog(null, "Você ainda não possui Cameras cadastradas!");
            }else{
                cameras = listaCam.get(0);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String retornaTipoPesagem(){
        if(rbComposta.isSelected() && !rbSimples.isSelected()){
            return "composta";
        }else{
            return "simples";
        }
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
        
        pe.setTipoPesagem(retornaTipoPesagem());
        pe.setAndamento(emAndamento());
        pe.setNfe(txtNfe.getText());
        pe.setValorNfe(Double.valueOf(txtValorNfe.getText()));
        pe.setPesoNfe(Double.valueOf(txtPesoBal.getText()));
        pe.setLote(txtLote.getText());
        pe.setOrigem(txtOrigem.getText());
        pe.setDestino(txtDestino.getText());
        pe.setPesoEnt1(Double.valueOf(txtPesoEntrada.getText()));
        pe.setPesoEnt2(pesoEntrada2);
        pe.setPesoSai1(Double.valueOf(txtPesoBal.getText()));
        pe.setPesoSai2(pesoSaida2);
        pe.setMotorista(txtMotorista.getText());
        pe.setFotoCarga1(capturaFoto1());
        pe.setFotoCarga2(capturaFoto2());
        pe.setFotoEntrada("/home/local/foto");
        pe.setFotoSaida("/home/local/foto");
        pe.setObservacao(txtObservacao.getText());
        pe.setPn(pn);
        pe.setVeiculo(v);
        pe.setOperador(op);
        pe.setProduto(prod);
        pe.setTransportador(pn);
        pe.setId(id);
        
        return pe;
    }
    
    public boolean validaCam1_Ativa(){
        if(cameras.isAtivoC1()){
            return true;
        }else{
            return false;
        }
    }
    public boolean validaCam2_Ativa(){
        if(cameras.isAtivoC2()){
            return true;
        }else{
            return false;
        }
    }
    
    
    public String capturaFoto1(){
        String diretorio="NotEnabled";
        if(validaCam1_Ativa()){
            OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
            FFmpegFrameGrabber cam1 = new FFmpegFrameGrabber(cameras.getCam01());

            try {
                cam1.start();
                //CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
                Mat imgColorida1  = new Mat();
                //Mat imagemColorido  = new Mat();
                org.bytedeco.javacv.Frame frameCam1 = cam1.grab();
                imgColorida1 = convertemat.convert(frameCam1);
                diretorio=DIRETORIOFOTOS+this.retornaDataHoraFoto()+"cam1.jpg";
                imwrite(diretorio, imgColorida1);
                cam1.stop();
            } catch (FrameGrabber.Exception ex) {
                //JOptionPane.showMessageDialog(null, ex);
                Logger.getLogger(ConfigCameras.class.getName()).log(Level.SEVERE, null, ex);
            }
            return diretorio;
        }else{
            return diretorio;
        }
    }
    public String capturaFoto2(){
        String diretorio="NotEnabled";
        if(validaCam2_Ativa()){
            OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
            FFmpegFrameGrabber cam2 = new FFmpegFrameGrabber(cameras.getCam02());

            try {
                cam2.start();
                //CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
                Mat imgColorida1  = new Mat();
                //Mat imagemColorido  = new Mat();
                org.bytedeco.javacv.Frame frameCam1 = cam2.grab();
                imgColorida1 = convertemat.convert(frameCam1);
                diretorio=DIRETORIOFOTOS+this.retornaDataHoraFoto()+"cam2.jpg";
                imwrite(diretorio, imgColorida1);
                cam2.stop();
            } catch (FrameGrabber.Exception ex) {
                //JOptionPane.showMessageDialog(null, ex);
                Logger.getLogger(ConfigCameras.class.getName()).log(Level.SEVERE, null, ex);
            }
            return diretorio;
        }else{
            return diretorio;
        }
    }
    /*
    private Pesagem tESTE(){
        Pesagem pe = new Pesagem();
        ParceiroNegocio pn = new ParceiroNegocio();
        ParceiroNegocio transp = new ParceiroNegocio();
        Veiculos v = new Veiculos();
        Operador op = new Operador();
        Produtos prod = new Produtos();
        
        
        pn.setId(2);
        transp.setId(1);
        v.setId(1);
        op.setId(1);
        prod.setId(1);

        
        //inserir data entrada apenas na insercao, alterar nao
        
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dataHora = sdf.format(dt);
        //System.out.println("eitaa-->"+dataHora);
        
        pe.setDataHoraEtrada(dataHoraEntrada());
        pe.setDataHoraSaida(dataHoraSaida());
        System.out.println(pe.getDataHoraEtrada());
        System.out.println(pe.getDataHoraSaida());
        
        pe.setTipoPesagem("composta");
        pe.setAndamento(true);
        pe.setNfe("555.0");
        pe.setValorNfe(55.500);
        pe.setPesoNfe(20.0);
        pe.setLote("teste");
        pe.setOrigem("fazenda 1");
        pe.setDestino("tableros");
        pe.setPesoEnt1(25.000);
        pe.setPesoEnt2(10.000);
        pe.setPesoSai1(10.000);
        pe.setPesoSai2(10.000);
        pe.setMotorista("antonio");
        pe.setFotoCarga1(capturaFoto1());
        pe.setFotoCarga2(capturaFoto2());
        pe.setFotoEntrada("local foto");
        pe.setFotoSaida("local foto");
        pe.setObservacao("obs teste");
        pe.setPn(pn);
        pe.setVeiculo(v);
        pe.setOperador(op);
        pe.setProduto(prod);
        pe.setTransportador(pn);
    
        return pe;
    }
    */
  
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
        txtTranpVeiculo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtParceiro = new javax.swing.JTextField();
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
        btnPesqParceiro = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNfe = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPesoNfe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValorNfe = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtPesoBal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPesoEntrada = new javax.swing.JTextField();
        txtPesoSaida = new javax.swing.JTextField();
        txtPesoLiq = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnPesqAgendamento = new javax.swing.JButton();
        btnLocalOrigem = new javax.swing.JButton();
        txtOrigem = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

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
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""))), "Operação", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

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

        jLabel4.setText("Placa Veículo");

        jLabel5.setText("Parceiro Origem");

        jLabel7.setText("Destino");

        jLabel8.setText("Produto");

        jLabel9.setText("Observação");

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

        btnPesqParceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/icones/lupa20x20.png"))); // NOI18N
        btnPesqParceiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPesqParceiroMouseReleased(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(245, 245, 245));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "NFe", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("Núm.");

        jLabel2.setText("Peso");

        jLabel3.setText("Valor");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNfe))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesoNfe, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorNfe, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel5.setBackground(new java.awt.Color(245, 245, 245));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Liberation Sans", 2, 14)); // NOI18N
        jLabel12.setText("Peso Kg");

        txtPesoBal.setBackground(new java.awt.Color(204, 255, 204));
        txtPesoBal.setFont(new java.awt.Font("Liberation Sans", 0, 22)); // NOI18N
        txtPesoBal.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtPesoBal, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesoBal)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(245, 245, 245));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel13.setText("Peso Entrada");

        jLabel14.setText("Peso Saída");

        jLabel15.setText("Peso Líq.");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPesoEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(txtPesoSaida)
                    .addComponent(txtPesoLiq))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPesoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(txtPesoSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesoLiq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnPesqAgendamento.setText("Agendamentos");
        btnPesqAgendamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPesqAgendamentoMouseReleased(evt);
            }
        });

        jLabel16.setText("Local Origem");

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
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCancelar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPesqAgendamento)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMotorista)
                                    .addComponent(txtLote)
                                    .addComponent(txtDestino)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnPesqParceiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnPesqPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtTranpVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtParceiro)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnPesqProduto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnLocalOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTranpVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPesqPlaca))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtParceiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPesqParceiro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(txtOrigem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnLocalOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPesqProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtProduto)
                            .addComponent(jLabel8))))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMotorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)
                    .addComponent(btnPesqAgendamento))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void btnSalvarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseReleased
        if(validaCampos()){
            try {
                if(salvar){
                    //dao.inserir(retornaObjeto());
                    //dao.inserir(tESTE());
                    PesagemDao.inserir(retornaObjeto());
                    limparCampos();
                    this.dispose();
                }else{
                    PesagemDao.alterar(retornaObjeto());
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

    private void btnPesqParceiroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesqParceiroMouseReleased
        PesqParceiroNegocio par = new PesqParceiroNegocio(null, true, this);
        par.setVisible(true);
    }//GEN-LAST:event_btnPesqParceiroMouseReleased

    private void btnPesqProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesqProdutoMouseReleased
        PesqProduto par = new PesqProduto(null, true, this);
        par.setVisible(true);
    }//GEN-LAST:event_btnPesqProdutoMouseReleased

    private void btnPesqAgendamentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesqAgendamentoMouseReleased
        PesqPesagemParaSaida ps = new PesqPesagemParaSaida(null, true, this);
        ps.setVisible(true);
    }//GEN-LAST:event_btnPesqAgendamentoMouseReleased

  
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
            java.util.logging.Logger.getLogger(CadPesagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadPesagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadPesagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadPesagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadPesagem dialog = new CadPesagem(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnLocalOrigem;
    private javax.swing.JButton btnPesqAgendamento;
    private javax.swing.JButton btnPesqParceiro;
    private javax.swing.JButton btnPesqPlaca;
    private javax.swing.JButton btnPesqProduto;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup grupoBotoes_Operacao;
    private javax.swing.ButtonGroup grupoBotoes_TipoPesagem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
    private javax.swing.JTextField txtOrigem;
    private javax.swing.JTextField txtParceiro;
    private javax.swing.JTextField txtPesoBal;
    private javax.swing.JTextField txtPesoEntrada;
    private javax.swing.JTextField txtPesoLiq;
    private javax.swing.JTextField txtPesoNfe;
    private javax.swing.JTextField txtPesoSaida;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtProduto;
    private javax.swing.JTextField txtTranpVeiculo;
    private javax.swing.JTextField txtValorNfe;
    // End of variables declaration//GEN-END:variables
}
