package br.com.itau.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

@Service
public class EmailSendService {

    @Autowired
    public EmailSendService emailSendService;

    public String sendEmail() throws Exception {
        // O ID do e-mail do destinatário precisa ser mencionado.
        String para = " fromaddress@gmail.com ";

        // O ID do e-mail do remetente precisa ser mencionado
        String from = " toaddress@gmail.com ";

        // Supondo que você está enviando e-mail por gmails smtp
        String host = "smtp.gmail.com";

        // Obter propriedades do sistema
        Properties properties = System.getProperties();

        // Configurar servidor de e-mail
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Obtenha o objeto Session.// e passe o nome de usuário e a senha
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("alexandre.marques.teixeira@gmail.com ", "435525@Cami");
            }
        });

        // Usado para depurar problemas de SMTP
        session.setDebug(true);

        try {
            // Cria um objeto MimeMessage padrão.
            // Mensagem  MimeMessage = new MimeMessage (session);
            Message msg = new MimeMessage(session);

            // Definir De: campo de cabeçalho do cabeçalho.
            msg.setFrom(new InternetAddress("alexandre.marqrques.teixeira@gmail.com"));

            // Definir como: campo de cabeçalho do cabeçalho.
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("alexandre.marques.teixeira@gmail.com"));

            // Definir assunto: campo de cabeçalho
            msg.setSubject("Esta é a linha de assunto!");

            // Agora defina a mensagem real
            msg.setText("Esta é a mensagem real");

            System.out.println("enviando ...");
            // Enviar mensagem
            Transport.send(msg);
            System.out.println("Mensagem enviada com sucesso ....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

        return para;
    }
}

