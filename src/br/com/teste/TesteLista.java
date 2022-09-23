
package br.com.teste;

import java.util.ArrayList;
import java.util.List;

public class TesteLista {
    
    
    public List<String> retornLista(){
        List<String> lista = new ArrayList();
        
        lista.add("teste1");
        lista.add("teste2");
        lista.add("teste3");
        return lista;
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
