
package br.com.bean;


public class ProdutosCarga {
    
    private int id;
    private Produtos produto;
    private Pesagem pesagem;
    private String observacao;

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

    public Pesagem getPesagem() {
        return pesagem;
    }

    public void setPesagem(Pesagem pesagem) {
        this.pesagem = pesagem;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
    
}
