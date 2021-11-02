/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Administrador
 */
public class EmailService {
    public static void sendEmail(String email, String subject, String content){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";  
        
        Pattern pattern = Pattern.compile(regex);  
        
        Matcher matcher = pattern.matcher(email);  
        
        if(!matcher.matches()){
            return;
        }
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ucutrueque@gmail.com", "basededatos2021");
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ucutrueque@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException mex) {
        }
    }
}
