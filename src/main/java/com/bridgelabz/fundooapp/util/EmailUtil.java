package com.bridgelabz.fundooapp.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.bridgelabz.fundooapp.exception.UserException;

public class EmailUtil {

	private EmailUtil() {

	}

	public static void sendEmail(String to, String subject, String token) throws Exception {
		String from = "aasthashree30@gmail.com";
		String password = "punamaastha@";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		};
		Session session = Session.getInstance(props, authenticator);
		send(session, from, to, subject, token);
	}

	private static void send(Session session, String from, String to, String subject, String token) throws Exception {
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(token);
			Transport.send(message);
		} catch (Exception e) {
			throw new UserException(404,e.getMessage());
		}
	}

}
