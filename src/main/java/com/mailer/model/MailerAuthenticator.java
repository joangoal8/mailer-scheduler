package com.mailer.model;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailerAuthenticator extends Authenticator {

  private final String userName;
  private final String password;

  public MailerAuthenticator(String userName,
                             String password) {
    this.userName = userName;
    this.password = password;
  }

  protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(userName, password);
  }
}
