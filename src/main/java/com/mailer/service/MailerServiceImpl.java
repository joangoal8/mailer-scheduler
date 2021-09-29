package com.mailer.service;

import com.mailer.model.Email;
import com.mailer.model.MailerAuthenticator;
import com.mailer.model.MailerConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailerServiceImpl implements MailerService {

  private final MailerConfigurationProperties mailerConfigurationProperties;

  @Autowired
  public MailerServiceImpl(MailerConfigurationProperties mailerConfigurationProperties) {
    this.mailerConfigurationProperties = mailerConfigurationProperties;
  }

  @Override
  public void sendEmail(Email email) {
    Properties properties = System.getProperties();
    properties.setProperty("mail.smtp.host", mailerConfigurationProperties.getHost());
    properties.put("mail.smtp.port", mailerConfigurationProperties.getPort());
    properties.put("mail.smtp.auth", mailerConfigurationProperties.getAuth());
    properties.put("mail.smtp.starttls.enable", mailerConfigurationProperties.getTlsEnable());

    Session session = Session.getInstance(properties,
      new MailerAuthenticator(email.getSender(), email.getPassword()));

    try {
      sendEmail(session, email);
    } catch (MessagingException ex) {
      ex.printStackTrace();
    }
  }

  private void sendEmail(Session session, Email email) throws MessagingException {
    MimeMessage message = new MimeMessage(session);
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getReceiver()));
    message.setSubject(email.getSubject());
    message.setText(email.getText());

    Transport.send(message);
    System.out.println("Mail successfully sent");
  }
}
