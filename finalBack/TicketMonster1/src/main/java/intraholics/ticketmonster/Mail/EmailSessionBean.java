/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraholics.ticketmonster.Mail;



import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

@Stateless

public class EmailSessionBean{
    @Resource(name ="java:jboss/mail/gmail")
    private Session session;

  
    
    public void sendEmail(String to,String subject,String body){
        MimeMessage message = new MimeMessage(session);
try {
    message.setRecipient(Message.RecipientType.TO, InternetAddress.parse(to)[0]);
    message.setSubject(subject);
    message.setText(body);
    Transport.send(message);
} catch (MessagingException ex) {
    ex.printStackTrace();
}
    }
}

    
