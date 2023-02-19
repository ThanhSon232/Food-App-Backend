package com.thanhson.food_app.utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailUtils {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}") private String sender;
    public String sendMail(String description, String email, String title) throws Exception {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setText(description);
            mailMessage.setTo(email);
            mailMessage.setSubject(title);
            mailSender.send(mailMessage);
            return "Send Invite "+email+" Successfully...";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
