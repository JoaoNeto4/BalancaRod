
package br.com.bean;

/**
 *
 * @author joao
 */
public class Configuracoes {
    
    private int id;
    private boolean enviaFotos;
    private boolean enviaEmail;
    private boolean pesaAuto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEnviaFotos() {
        return enviaFotos;
    }

    public void setEnviaFotos(boolean enviaFotos) {
        this.enviaFotos = enviaFotos;
    }

    public boolean isEnviaEmail() {
        return enviaEmail;
    }

    public void setEnviaEmail(boolean enviaEmail) {
        this.enviaEmail = enviaEmail;
    }

    public boolean isPesaAuto() {
        return pesaAuto;
    }

    public void setPesaAuto(boolean pesaAuto) {
        this.pesaAuto = pesaAuto;
    }

    
    
    
}
