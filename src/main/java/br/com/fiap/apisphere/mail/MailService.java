package br.com.fiap.apisphere.mail;

import br.com.fiap.apisphere.user.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSenderImpl mailSender;

    public MailService(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(User user) {

        var email = new SimpleMailMessage();
        email.setFrom("sphere@sphere.com");
        email.setTo(user.getEmail());
        email.setSubject("Boas vindas");
        email.setText("""
                    Olá, %s
                    
                    Ficamos felizes em receber você aqui.
                    Conte com a gente.
                    
                    Att
                    Noix
                """.formatted(user.getName()));

        mailSender.send(email);

    }
}
