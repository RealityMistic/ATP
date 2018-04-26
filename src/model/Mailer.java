package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	
	
    public void sendHtmlEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException {
 
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
 
    }
    private void recordaMail(String host, String port, String mailFrom, String password, String mailTo){
    	String subject="It's been a while that you do not play AudioTrainerPlay!";
    	String message="<i>Greetings!</i><br>";
    message += "<font color=purple><b>It's been a while that you don't play, remember that you have"
    		+ "to be constant to achieve results!</b><br>";
    message += "</font>";
    	try {
            sendHtmlEmail(host, port, mailFrom, password, mailTo,
                    subject, message);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Failed to sent email.");
            ex.printStackTrace();
        }
    }
    
    private boolean neededMail(String username){
    	return false;
    }
    public void Mailer(){
// Comprobar si le toca mandar correos y mandarlos
        
        // SMTP server information
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "audiotrainerplay@gmail.com";
        String password = "*9audiotrainerplay";
        Scanner scan = new Scanner("ATP-users.txt");
        String username, usermail;
        // outgoing message information
        while (scan.hasNextLine()){
	        String line = scan.nextLine();
	        ArrayList<String> aList= new ArrayList(Arrays.asList(line.split(";")));
	        username=aList.get(1);
	        usermail=aList.get(4);
        	if (neededMail(username)){
        		recordaMail(host, port, mailFrom, password, usermail);
        	}
        }

    }
    	
    }


