
package br.com.bean;

/**
 *
 * @author joao
 */
public class OrigemFazenda {
    
    private int id;
    private ParceiroNegocio parceiro;
    private String origem;
    private String localizacao;
    private String cidade;
    private String estado;
    private String observacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParceiroNegocio getParceiro() {
        return parceiro;
    }

    public void setParceiro(ParceiroNegocio parceiro) {
        this.parceiro = parceiro;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
}
