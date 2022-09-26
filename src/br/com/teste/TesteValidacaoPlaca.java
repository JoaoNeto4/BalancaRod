
package br.com.teste;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author joao
 */




public class TesteValidacaoPlaca {
    
    
    public boolean validaPlaca(String placa) {
        boolean result = false;
        Pattern pattern = Pattern.compile("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}\\-[0-9]{4}");
        Matcher mat = pattern.matcher(placa);
        if (!mat.matches()) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        TesteValidacaoPlaca tp = new TesteValidacaoPlaca();
        
        String texto = "ABC1P34";
        
        System.out.println(tp.validaPlaca(texto));
        
    }
    
}
