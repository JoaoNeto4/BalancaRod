package br.com.email;

import br.com.teste.email.*;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TLS {

    public static void main(String[] args) {
        final String remetente = "desenvolvimento2@ativusgestao.com.br"; //email de quem envia(válido)
        final String senha = "mypassword"; // senha de quem envia
        final String destinatario = "desenvolvimento2@ativusgestao.com.br"; // quem recebe

        System.out.println("TLSEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "email-ssl.com.br"); //SMTP Host
        props.put("mail.smtp.port", "465"); //Porta TLS
        props.put("mail.smtp.auth", "true"); //autenticação ativa
        props.put("mail.smtp.starttls.enable", "true"); //STARTTLS ativo

        //cria objeto autenticador de argumento Session.getInstance 
        Authenticator auth = new Authenticator() {
            //override getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        };
        Session session = Session.getInstance(props, auth);

        EnvioEmail.enviaEmail(session, destinatario, "SSLEmail Assunto", "SSLEmail Corpo Email");
        //Basic.enviaEmail(session, destinatario, "TLSEmail Assunto", "Corpo do Email");

    }

}
