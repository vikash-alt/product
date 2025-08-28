package com.product.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    private JavaMailSender javaMailSender;
    public EmailService (JavaMailSender mail){
        this.javaMailSender= mail;
    }
    public void sendEmail(String to, String subject, String message){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);
        javaMailSender.send(mail);
    }
}
