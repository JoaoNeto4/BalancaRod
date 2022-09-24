
package br.com.teste;

import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author joao
 */
public class TesteArray {
    
    
    
    public String[] sendEmailIds() {
        String[] emailIds = new String[4];
        emailIds[0] = "abc@mail.com";
        emailIds[1] = "deg@mail.com";
        emailIds[2] = "sgh@mail.com";
        emailIds[3] = "hht@mail.com";
        return emailIds;
    }
    public String[] sendEmailIds(List<String> lista) {
        int tam =lista.size();
        String[] email = new String[tam];
        for(int i=0; i<tam; i++){
            email[i]=lista.get(i).trim();
        }

    return email;
    }
    
    public static void main(String[] args) {
        TesteArray t = new TesteArray();
         List<String> lista = new ArrayList<>();
         lista.add("eita1");
         lista.add("eita2");
         lista.add("eita3");
         lista.add("eita4");
         String[] teste=t.sendEmailIds(lista);
         for(int i=0; i<teste.length; i++){
             System.out.println(teste[i]);
         }
    }
    
    
    
    //InternetAddress[] address = new InternetAddress[2];
     //   address[0] = new InternetAddress("sdf@com");
        //address [1] = new InternetAddress("t@com");
}
