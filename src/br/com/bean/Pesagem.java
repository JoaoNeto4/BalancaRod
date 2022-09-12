
package br.com.bean;

import java.util.Date;

public class Pesagem {
    
    private int id;
    private ParceiroNegocio pn;
    private Veiculos veiculo;
    private Operador operador;
    private Produtos produto;
    private ParceiroNegocio transportador;
    private Date dataHoraEtrada;
    private Date dataHoraSaida;
    private String tipoPesagem;
    private Boolean andamento;
    private String nfe;
    private Double valorNfe;
    private Double pesoNfe;
    private String lote;
    private String origem;
    private String destino;
    private Double pesoEnt1;
    private Double pesoEnt2;
    private Double pesoSai1;
    private Double pesoSai2;
    private String fotoCarga1;
    private String fotoCarga2;
    private String fotoEntrada;
    private String FotoSaida;
    private String motorista;
    private String observacao;

    public Date getDataHoraEtrada() {
        return dataHoraEtrada;
    }

    public void setDataHoraEtrada(Date dataHoraEtrada) {
        this.dataHoraEtrada = dataHoraEtrada;
    }

    public Date getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Date dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Double getValorNfe() {
        return valorNfe;
    }

    public void setValorNfe(Double valorNfe) {
        this.valorNfe = valorNfe;
    }

    public Double getPesoNfe() {
        return pesoNfe;
    }

    public void setPesoNfe(Double pesoNfe) {
        this.pesoNfe = pesoNfe;
    }

    public String getFotoCarga1() {
        return fotoCarga1;
    }

    public void setFotoCarga1(String fotoCarga1) {
        this.fotoCarga1 = fotoCarga1;
    }

    public String getFotoCarga2() {
        return fotoCarga2;
    }

    public void setFotoCarga2(String fotoCarga2) {
        this.fotoCarga2 = fotoCarga2;
    }

    public String getFotoEntrada() {
        return fotoEntrada;
    }

    public void setFotoEntrada(String fotoEntrada) {
        this.fotoEntrada = fotoEntrada;
    }

    public String getFotoSaida() {
        return FotoSaida;
    }

    public void setFotoSaida(String FotoSaida) {
        this.FotoSaida = FotoSaida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public ParceiroNegocio getPn() {
        return pn;
    }

    public void setPn(ParceiroNegocio pn) {
        this.pn = pn;
    }

    public Veiculos getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculos veiculo) {
        this.veiculo = veiculo;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public String getTipoPesagem() {
        return tipoPesagem;
    }

    public void setTipoPesagem(String tipoPesagem) {
        this.tipoPesagem = tipoPesagem;
    }

    public Boolean getAndamento() {
        return andamento;
    }

    public void setAndamento(Boolean andamento) {
        this.andamento = andamento;
    }

    public String getNfe() {
        return nfe;
    }

    public void setNfe(String nfe) {
        this.nfe = nfe;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPesoEnt1() {
        return pesoEnt1;
    }

    public void setPesoEnt1(Double pesoEnt1) {
        this.pesoEnt1 = pesoEnt1;
    }

    public Double getPesoEnt2() {
        return pesoEnt2;
    }

    public void setPesoEnt2(Double pesoEnt2) {
        this.pesoEnt2 = pesoEnt2;
    }

    public Double getPesoSai1() {
        return pesoSai1;
    }

    public void setPesoSai1(Double pesoSai1) {
        this.pesoSai1 = pesoSai1;
    }

    public Double getPesoSai2() {
        return pesoSai2;
    }

    public void setPesoSai2(Double pesoSai2) {
        this.pesoSai2 = pesoSai2;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    public ParceiroNegocio getTransportador() {
        return transportador;
    }

    public void setTransportador(ParceiroNegocio transportador) {
        this.transportador = transportador;
    }
}
