
package br.com.bean;

/**
 *
 * @author joao
 */
public class Email {
    
    private int id;
    private ParceiroNegocio pn;
    private String email;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ParceiroNegocio getPn() {
        return pn;
    }

    public void setPn(ParceiroNegocio pn) {
        this.pn = pn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
