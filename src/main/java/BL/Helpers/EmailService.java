/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL.Helpers;

import BL.Entities.Oferta;
import BL.Entities.Persona;
import BL.Entities.Publicacion;
import java.util.ArrayList;
import java.util.List;
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
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ConnectionData.EMAIL, ConnectionData.EMAIL_PASSWORD);
            }
        });
        
        MimeMessage message;
        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ConnectionData.EMAIL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException mex) {
        }
    }
    
    public static void sendData(Persona p1, Persona p2, List<Publicacion> lista) {
        List<Publicacion> pub1 = new ArrayList<>();
        List<Publicacion> pub2 = new ArrayList<>();
        
        lista.forEach(pub -> {
            if (pub.getPublicante() == p1.getCi())
                pub1.add(pub);
            else
                pub2.add(pub);
        });
        
        String msgGenerico = "Se ha concretado un trueque con %s! \n"
                + "Vive en %s y su telefono de contacto es: %s \n"
                + "Tambien puedes comunicarte por mail a la direccion %s \n"
                + "Los articulos que debes entregar son los siguientes: \n %s";
        
        String pub1string = "";
        for (Publicacion pub : pub1) {
            pub1string += pub.getCantidad() + " " + pub.getNombreProducto() + "\n";
        }
        String msg1 = String.format(msgGenerico, p2.getNombre() + " " + p2.getApellido(), p2.getNombreDepartamento(), p2.getTelefono(), p2.getEmail(), pub1string);
        
        String pub2string = "";
        for (Publicacion pub : pub2) {
            pub2string += pub.getCantidad() + " " + pub.getNombreProducto() + "\n";
        }
        String msg2 = String.format(msgGenerico, p1.getNombre() + " " + p1.getApellido(), p1.getNombreDepartamento(), p1.getTelefono(), p1.getEmail(), pub2string);
        
        sendEmail(p1.getEmail(), "Trueque aceptado!", msg1);
        sendEmail(p2.getEmail(), "Trueque aceptado!", msg2);
    }
}
