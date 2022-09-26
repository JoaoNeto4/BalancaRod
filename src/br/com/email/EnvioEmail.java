package br.com.email;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnvioEmail {
    

    public static void enviaEmail(Session session, String destinatario, String remetente, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //setando cabeçalhos da menssagem
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(remetente, "Não Responda-JD"));

            msg.setReplyTo(InternetAddress.parse(remetente, false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));
            System.out.println("A menssagem está pronta");
            Transport.send(msg);

            System.out.println("EMail Enviado com Sucesso!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enviaEmailComImagem(Session session, String destinatario, String remetente, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(remetente, "Não Respond-JD"));

            msg.setReplyTo(InternetAddress.parse(remetente, false));

            msg.setSubject(subject, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));

            // cria a parte do corpo da menssagem
            BodyPart messageBodyPart = new MimeBodyPart();

            messageBodyPart.setText(body);

            // Criar uma mensagem de várias partes para anexo
            Multipart multipart = new MimeMultipart();

            // Define parte da menssagem
            multipart.addBodyPart(messageBodyPart);

            // A segunda parte é o anexo da menssagem
            messageBodyPart = new MimeBodyPart();
            String filename = "image.png";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            //O truque é adicionar o cabeçalho content-id aqui
            messageBodyPart.setHeader("Content-ID", "image_id");
            multipart.addBodyPart(messageBodyPart);

            //terceira parte para exibir a imagem no corpo do e-mail
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("<h1>Attached Image</h1>"
                    + "<img src='cid:image_id'>", "text/html");
            multipart.addBodyPart(messageBodyPart);

            //Defina a mensagem de várias partes para a mensagem de e-mail
            msg.setContent(multipart);

            // Envia a menssagem
            Transport.send(msg);
            System.out.println("EMail Sent Successfully with image!!");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void enviaEmailComAnexo(Session session, InternetAddress[] destinatarios, String remetente, String subject, String body, String anexo1, String anexo2) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(remetente, "Não Responda-JD"));
            msg.setReplyTo(InternetAddress.parse(remetente, false));
            msg.setSubject(subject, "UTF-8");
            msg.setSentDate(new Date());
            //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario, false));
            msg.setRecipients(Message.RecipientType.TO, destinatarios);
            // cria a parte do corpo da menssagem
            BodyPart mb = new MimeBodyPart();
            // Preenche a menssagem
            mb.setText(body);
            // Cria uma menssagem de varias partes para a menssagem
            Multipart multipart = new MimeMultipart();

            // Define o texto de parte da menssagem
            multipart.addBodyPart(mb);
            // A segunda parte é o anexo
            mb = new MimeBodyPart();

            String filename = "/home/joao/NetBeansProjects/Balanca/src/br/com/teste/2022-09-16-17:31:19.jpg";
            String filename2 = "/home/joao/NetBeansProjects/Balanca/src/br/com/teste/fotoTeste.jpg";
            
           
            /*
            multiplos emails
                    https://www.guj.com.br/t/javamail-enviar-com-mais-de-um-destinatario/38842/9
                    
                    https://www.mballem.com/post/enviando-email-com-a-api-javamail/
                    
                    https://stackoverflow.com/questions/26867055/how-to-send-an-email-to-multiple-recipients-in-spring
            */
            DataSource source = new FileDataSource(filename);
            DataSource source2 = new FileDataSource(filename2);
            
            mb.setDataHandler(new DataHandler(source));
            mb.setFileName(filename);

            multipart.addBodyPart(mb);
            
            BodyPart mb2 = new MimeBodyPart();
            mb2.setDataHandler(new DataHandler(source2));
            mb2.setFileName(filename2);
            
            multipart.addBodyPart(mb2);

            //Envia a partes completas da menssagem
            msg.setContent(multipart);

            // Envia a menssagem
            Transport.send(msg);
            System.out.println("EMail e Anexo Enviado comSucesso!!");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
