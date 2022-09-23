package br.com.email;

import br.com.bean.Email;
import br.com.dao.EmailDao;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSL {

    public static void main(String[] args) throws SQLException {
        
        Email email = EmailDao.retornaInfoEmail(1);

        
        
        final String remetente = email.getEmail(); ////email de quem envia(válido)
        final String senha = email.getSenha(); // senha de quem envia
        final int porta = email.getPorta();
        final String servidor = email.getServidor();
        //final String seguranca = email.getSeguranca();
        final String destinatario = "desenvolvimento2@ativusgestao.com.br"; // quem recebe

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", servidor); //SMTP Host
        props.put("mail.smtp.socketFactory.port", porta); // Porta SSL
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); // SMTP Autenticação ativa
        props.put("mail.smtp.port", porta); //SMTP Porta

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Sessão criada");
     //   EnvioEmail.enviaEmail(session, destinatario, remetente, "SSLEmail Assunto", "SSLEmail Corpo Email");
     
     
     
        String filename = "/home/joao/NetBeansProjects/Balanca/src/br/com/teste/2022-09-16-17:31:19.jpg";
        String filename2 = "/home/joao/NetBeansProjects/Balanca/src/br/com/teste/fotoTeste.jpg";

     //   EnvioEmail.enviaEmailComAnexo(session, destinatario, remetente, "SSLEmail Assunto de teste com anexo", "SSLEmail Corpo do email com anexo", filename, filename2);

    //    EnvioEmail.enviaEmailComAnexo(session, destinatario, remetente, "SSLEmail Assunto de teste com imagem", "SSLEmail Corpo do email com imagem");

    }

}
