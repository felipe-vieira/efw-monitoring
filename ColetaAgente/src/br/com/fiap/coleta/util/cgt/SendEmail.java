package br.com.fiap.coleta.util.cgt;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail
{
	
   private String[] to = null;
   private String from = "";
   private String subject = "";
   private String message = "";
   private String host = "";
   private String user = "";
   private String password = "";
   private int port = 25;
	
   public void send()
   {
      Properties properties = System.getProperties();

      properties.setProperty("mail.smtp.host", this.host);
      properties.setProperty("mail.user", this.user);
      properties.setProperty("mail.password", this.password);
      properties.setProperty("mail.port", "" + this.port);

      Session session = Session.getDefaultInstance(properties);

      try{
         MimeMessage message = new MimeMessage(session);

         message.setFrom(new InternetAddress(from));
    
         for (int i=0; to.length > i; i++)
         {
        	 message.addRecipient(Message.RecipientType.TO,
                     new InternetAddress(to[i]));
         }       

         message.setSubject(this.subject);

         message.setText(this.message);

         Transport.send(message);
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }

public String[] getTo() {
	return to;
}

public void setTo(String[] to) {
	this.to = to;
}

public String getFrom() {
	return from;
}

public void setFrom(String from) {
	this.from = from;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String getHost() {
	return host;
}

public void setHost(String host) {
	this.host = host;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Integer getPort() {
	return port;
}

public void setPort(int port) {
	this.port = port;
}
}