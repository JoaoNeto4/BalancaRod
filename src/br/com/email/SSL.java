package br.com.email;

import br.com.teste.email.*;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSL {

    public static void main(String[] args) {
        final String remetente = "desenvolvimento2@ativusgestao.com.br"; ////email de quem envia(válido)
        final String senha = "myPassword"; // senha de quem envia
        final String destinatario = "desenvolvimento2@ativusgestao.com.br"; // quem recebe

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "email-ssl.com.br"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); // Porta SSL
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); // SMTP Autenticação ativa
        props.put("mail.smtp.port", "465"); //SMTP Porta

        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        };

        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Sessão criada");
        EnvioEmail.enviaEmail(session, destinatario, "SSLEmail Assunto", "SSLEmail Corpo Email");

    //    EnvioEmail.enviaEmailComAnexo(session, destinatario, "SSLEmail Assunto de teste com anexo", "SSLEmail Corpo do email com anexo");

    //    EnvioEmail.enviaEmailComAnexo(session, destinatario, "SSLEmail Assunto de teste com imagem", "SSLEmail Corpo do email com imagem");

    }

}
