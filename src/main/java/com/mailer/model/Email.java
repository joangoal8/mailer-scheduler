package com.mailer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.StringJoiner;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Email implements Serializable {

  private final String subject;
  private final String sender;
  private final String password;
  private final String receiver;
  private final String text;

  private Email(Builder builder) {
    this.subject = builder.subject;
    this.sender = builder.sender;
    this.password = builder.password;
    this.receiver = builder.receiver;
    this.text = builder.text;
  }

  @JsonCreator
  private Email(@JsonProperty("subject") String subject,
                @JsonProperty("sender") String sender,
                @JsonProperty("password") String password,
                @JsonProperty("receiver") String receiver,
                @JsonProperty("text") String text) {
    this.subject = subject;
    this.sender = sender;
    this.password = password;
    this.receiver = receiver;
    this.text = text;
  }

  public String getSubject() {
    return subject;
  }

  public String getSender() {
    return sender;
  }

  public String getPassword() {
    return password;
  }

  public String getReceiver() {
    return receiver;
  }

  public String getText() {
    return text;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String subject;
    private String sender;
    private String password;
    private String receiver;
    private String text;

    public Builder subject(String subject) {
      this.subject = subject;
      return this;
    }

    public Builder sender(String sender) {
      this.sender = sender;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder receiver(String receiver) {
      this.receiver = receiver;
      return this;
    }

    public Builder text(String text) {
      this.text = text;
      return this;
    }

    public Email build() {
      return new Email(this);
    }
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Email.class.getSimpleName() + "[", "]")
      .add("subject='" + subject + "'")
      .add("sender='" + sender + "'")
      .add("password='" + password + "'")
      .add("receiver='" + receiver + "'")
      .add("text='" + text + "'")
      .toString();
  }
}
