package utilities;

import java.util.Properties;

import model.Utente;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	public static void sendMail(String testo, String username, String password, Utente utente)
			throws MessagingException {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "false");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utente.getEmail()));
		message.setSubject("Recupera password di Autonoleggio");
		message.setText("Dear " + utente.getNome() + " " + utente.getCognome() + ", + \n\n La sua nuova password è:"
				+ testo + "\n\n"
				+ "è consigliato modificarla una volta effettuato l'accesso http://localhost:8080/AutonoleggioJPA/welcome.jsp");

		Transport.send(message);

		System.out.println("Done");

	}

	public static String generatePassword() {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(8);

		for (int i = 0; i < 8; i++) {

// generate a random number between 
// 0 to AlphaNumericString variable length 
			int index = (int) (AlphaNumericString.length() * Math.random());

// add Character one by one in end of sb 
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}
}
