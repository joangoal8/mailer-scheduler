package com.mailer.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailerConfigurationProperties {

  @Value("${mailer.smtp.host}")
  private String host;

  @Value("${mailer.smtp.port}")
  private String port;

  @Value("${mailer.smtp.auth}")
  private String auth;

  @Value("${mailer.smtp.starttls.enable}")
  private String tlsEnable;

  public String getHost() {
    return host;
  }

  public String getPort() {
    return port;
  }

  public String getAuth() {
    return auth;
  }

  public String getTlsEnable() {
    return tlsEnable;
  }
}
