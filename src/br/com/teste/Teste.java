
package br.com.teste;

import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author joao
 */
public class Teste {
    
    
    public static void main(String[] args) {
        /*
        //para o java.util.Date:
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //01/02/2019 14:08:43
        */
        
        /*
        //java.time.LocalDateTime:
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); //01/02/2019 14:08:43
        System.out.println(now);
        */
        
        /*
        //java.time.LocalDate:
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        System.out.println(dtf.format(localDate)); //01/02/2019
        */
        
        
        /*
        String dataFormatadaDeHoje = "23/02/2018 08:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime formatDateTime = LocalDateTime.parse(dataFormatadaDeHoje, formatter);

        System.out.println("Data formatada em modo texto            : " + dataFormatadaDeHoje);
        System.out.println("Data convertida para LocalDateTime      : " + formatDateTime);
        System.out.println("Data formadata a partir do LocalDateTime: " + formatDateTime.format(formatter));
        System.out.println(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));//usar no log
        */
        
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        System.out.println(currentTime);
    }
}
