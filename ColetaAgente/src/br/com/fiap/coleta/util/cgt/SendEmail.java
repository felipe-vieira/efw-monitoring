package br.com.fiap.coleta.util.cgt;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage; 


public class SendEmail {
	
	private String[] to = null;
   	private String from = "";
	private String subject = "";
	private String message = "";
	private String host = "";
	private String user = "";
    private String password = "";
    private int port = 25;

	private String mailSMTPServer;
	private int mailSMTPServerPort;
	

	public SendEmail() { //Para o GMAIL 
		mailSMTPServer = this.host;
		mailSMTPServerPort = this.port;
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	SendEmail(String mailSMTPServer, int mailSMTPServerPort) { 
		this.mailSMTPServer = mailSMTPServer;
		this.mailSMTPServerPort = mailSMTPServerPort;
	}
	
	public void send() {
		
		Properties props = new Properties();       

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.smtp.host", this.host); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.user", this.from);
		props.put("mail.debug", "false");
		props.put("mail.smtp.port", mailSMTPServerPort);
		
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false); 

		Message msg = new MimeMessage(session);

		try {
			
			for (int i=0; this.to.length > i; i++){
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to[i]));	
			}
			
			msg.setFrom(new InternetAddress(this.from));
			
			msg.setSubject(this.subject);
			
			msg.setContent(this.message,"text/plain");

		} catch (Exception e) {
			System.out.println(">> Erro: Completar Mensagem");
			e.printStackTrace();
		}
		
		Transport tr;
		try {
			tr = session.getTransport("smtp");
			
			tr.connect(this.host, this.user, this.password);
			
			
			tr.sendMessage(msg, msg.getAllRecipients());
			tr.close();
		} catch (Exception e) {
			
			System.out.println(">> Erro: Envio Mensagem");
			e.printStackTrace();
		}

	}
}
