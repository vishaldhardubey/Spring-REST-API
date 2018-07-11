package com.bridgelabz.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

/**
 * Description : Class is to send the mail to corresponding mail id.
 * @author 	: Vishal Dhar Dubey
 * @version : 1.0
 * @since 	: 10-07-2018
 */
@Component
public class Mailer {
	/**
	 * Function is used to send email to the emailID provided by user.
	 * @param email
	 * @param admin
	 * @param passwordAdmin
	 * @param userEmail
	 * @param password
	 */
	@SuppressWarnings("static-access")
	public void send(String email, String admin, String passwordAdmin,String userEmail,String password) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(admin, passwordAdmin);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(admin));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setText(password);
			System.out.println("################Inside try block for MimeMessage");
	
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", 587, admin, passwordAdmin);
			transport.send(message);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
