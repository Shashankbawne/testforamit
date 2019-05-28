package com.gst.reco.KAAKS;


import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Domain.DataCheck.DataCheck;

public class SendAttachmentInEmail {
	DataCheck datachk=new DataCheck();
	
	 public static void sendEmailWithAttachments(String host, String port,
	            final String userName, final String password, String toAddress,
	            String subject, String message, String[] attachFiles)
	            throws AddressException, MessagingException {
	        // sets SMTP server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.user", userName);
	        properties.put("mail.password", password);
	 
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
	 
	        // creates message part
	        MimeBodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html");
	 
	        // creates multi-part
	        Multipart multipart = new MimeMultipart();
	        multipart.addBodyPart(messageBodyPart);
	 
	        // adds attachments
	        if (attachFiles != null && attachFiles.length > 0) {
	            for (String filePath : attachFiles) {
	                MimeBodyPart attachPart = new MimeBodyPart();
	 
	                try {
	                    attachPart.attachFile(filePath);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        }
	 
	        // sets the multi-part as e-mail's content
	        msg.setContent(multipart);
	 
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }
	 
	    /**
	     * Test sending e-mail with attachments
	     */
	    public boolean sendEmail(String fileName,String targetemail) {
	        // SMTP info
	        String host = "smtp.gmail.com";
	        String port = "587";
	        String mailFrom = "shashankbawne@gmail.com";
	        String password = "shivani786";
	 
	        // message info
	        String mailTo = targetemail;
	        String subject = "Instantreco Report";
	        String message = "<!DOCTYPE html><html lang=\"en\"><head><title>CSS Template</title><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>* {  box-sizing: border-box;}body {  font-family: Arial, Helvetica, sans-serif;} header {  background-color: #4682b4;  text-align: center;  font-size: 35px;  color: white;}nav {  float: left;  width: 30%;  height: 300px; background: #ccc;  padding: 20px;} nav ul {  list-style-type: none;  padding: 0;}article {  float: left;  padding: 20px;  width: 70%;  background-color: #f1f1f1;  height: 300px; } section:after {  content: \"\";  display: table;  clear: both;} footer {  background-color:  #c5d4e6;  padding: 10px;  text-align: center;  color: white;} @media (max-width: 600px) {  nav, article {    width: 100%;    height: auto;  }}</style></head><body><header>  <h2>Aceone Automation</h2></header><h2>Dear sir</h2><p>Please find attached here with the report generated based on your inputs.</p><p>We are thankful to you for using our proprietory algorithm to generate the report.</p><br><br><p>Thank you for choosing us.</p><p>Team AceOne Automation</><br><span style=\"font-size:medium\"><a href=\"http://www.instantreco.com\" target=\"_blank\">www.instantreco.com</a></span><footer>  <p><a href=\"http://www.instantreco.com\" target=\"_blank\">visit our website</a> | <span style=\"font-size:medium\"><a href=\"http://www.instantreco.com\" target=\"_blank\">login to your account</a></span> |  <span style=\"font-size:medium\"><a href=\"http://www.instantreco.com/instantreco/about.php\" target=\"_blank\">get support</a></span</p></footer></body></html>";
	 
	        // attachments
	        String[] attachFiles = new String[1];
	        attachFiles[0] = "c:/instantreco/"+fileName+".xlsx";
	      
	        try {
	            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
	                subject, message, attachFiles);
	           // datachk.setDetail(fileName,); 
	            System.out.println("Email sent.");
	            return true;
	        } catch (Exception ex) {
	          	//datachk.setDetail(fileName,"ERRORINEMAIL"); 
	            System.out.println("Could not send email.");
	            ex.printStackTrace();
	            return false;
	        }
	    }
}