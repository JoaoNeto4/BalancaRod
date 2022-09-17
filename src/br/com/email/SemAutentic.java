package br.com.email;

import br.com.teste.email.*;
import java.util.Properties;

import javax.mail.Session;

public class SemAutentic {

    public static void main(String[] args) {

        System.out.println("SimpleEmail Start");

        String smtpHostServer = "smtp.example.com";
        String emailID = "email_me@example.com";

        Properties props = System.getProperties();

        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);

        Basic.enviaEmail(session, emailID, "Assunto Teste", "Teste de corpo de email");
    }

}
