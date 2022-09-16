
package br.com.bean;

/**
 *
 * @author joao
 */
public class Cameras {
    
    private int id;
    private String cam01;
    private boolean ativoC1;
    private String cam02;
    private boolean ativoC2;
    private String camEntrada;
    private boolean ativoEntrada;
    private String camSaida;
    private boolean ativoSaida;

    public boolean isAtivoC1() {
        return ativoC1;
    }

    public void setAtivoC1(boolean ativoC1) {
        this.ativoC1 = ativoC1;
    }

    public boolean isAtivoC2() {
        return ativoC2;
    }

    public void setAtivoC2(boolean ativoC2) {
        this.ativoC2 = ativoC2;
    }

    public boolean isAtivoEntrada() {
        return ativoEntrada;
    }

    public void setAtivoEntrada(boolean ativoEntrada) {
        this.ativoEntrada = ativoEntrada;
    }

    public boolean isAtivoSaida() {
        return ativoSaida;
    }

    public void setAtivoSaida(boolean ativoSaida) {
        this.ativoSaida = ativoSaida;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCam01() {
        return cam01;
    }

    public void setCam01(String cam01) {
        this.cam01 = cam01;
    }

    public String getCam02() {
        return cam02;
    }

    public void setCam02(String cam02) {
        this.cam02 = cam02;
    }

    public String getCamEntrada() {
        return camEntrada;
    }

    public void setCamEntrada(String camEntrada) {
        this.camEntrada = camEntrada;
    }

    public String getCamSaida() {
        return camSaida;
    }

    public void setCamSaida(String camSaida) {
        this.camSaida = camSaida;
    }
    
    
}
