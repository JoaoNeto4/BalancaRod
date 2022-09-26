
package br.com.teste;

import static com.mysql.cj.conf.PropertyKey.logger;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class TesteLista {
    
    
    public List<String> retornLista(){
        List<String> lista = new ArrayList();
        
        lista.add("teste1");
        lista.add("teste2");
        lista.add("teste3");
        return lista;
    }
    
    public InternetAddress[] converteListaArrayEmail(List<String> lista) throws AddressException { 
        String item ="";
        int tam =lista.size();
        InternetAddress[] email = new InternetAddress[tam];

        for(int i=0; i<tam; i++){
            item= lista.get(i);
            email[i]=new InternetAddress(lista.get(i));
            //converter para InternetAddress
        }
        return email;
    }
    
    private InternetAddress[] getInternetAddress(List<String> lista) {
	int size = 0;
	if (lista != null) {
		size = lista.size();
	}
	InternetAddress[] address = new InternetAddress[size];
	for (int i = 0; i < size; i++) {
		try {
                    address[i] = new InternetAddress(lista.get(i));
		} catch (Exception e) {
                    e.printStackTrace();
		}
	}
	return address;
    }
    
    
    
    public static void main(String[] args) {
        
        TesteLista t = new TesteLista();
        
        int i = t.retornLista().size();
        
        System.out.println("tamanho lista: "+i);
        
        for(int j=0; j<i; j++){
            System.out.println("lista index: "+j);
        }
        
        List<String> lista2 =  t.retornLista();
        
        for(int k=0; k<lista2.size(); k++){
            System.out.println("eitaaaaa: "+lista2.get(k));
        }
        
    }
    
}
