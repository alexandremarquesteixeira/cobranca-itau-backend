package br.com.itau.service;

import br.com.itau.models.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;

@Service
public class EmailSendService {

    @Autowired
    public EmailSendService emailSendService;

    public String sendEmail(Optional<Contrato> contrato) throws Exception {

        Locale localeBR = new Locale("pt","BR");
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

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
                return new PasswordAuthentication("alexandre.marques.teixeira@gmail.com ", "xxxxxx");
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
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(contrato.get().getCliente().getEmail()));

            // Definir assunto: campo de cabeçalho
            msg.setSubject("Contratos de Renegociação de Crédito");

            // Agora defina a mensagem real
            msg.setText("Ilmo Sr.(a) " + contrato.get().getCliente().getNome() + "\n" +
                    "Conforme solicitado, segue abaixo os contratos pré-aprovados para uma possivel renegociação"+"\n" +
                    "Produto: " + contrato.get().getCd_Produto() + "\n" +
                    "Contrato: " + contrato.get().getId_Contrato() + "\n" +
                    "Valor: " + dinheiro.format(contrato.get().getVlr_Contrato()) + "\n" +
                    "Para os contratos tipo APR ou RGN, por favor, dirija-se a uma agência mais proxima ou entre em"+"\n"+
                    "contato pelo 0800."+"\n\n"+
                    "Estamos a disposição para quaisquer dúvida ou esclarecimentos."+"\n\n"+
                    "Banco Itaú"
                    );

            System.out.println("enviando ...");
            // Enviar mensagem
            Transport.send(msg);
            System.out.println("Mensagem enviada com sucesso ....");
            return "200";
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return "400";
        }

    }

}

