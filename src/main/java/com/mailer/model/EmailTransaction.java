package com.mailer.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = EmailTransaction.TABLE_NAME)
public class EmailTransaction {

  public static final String TABLE_NAME = "EMAIL_TRANSACTIONS";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String jobId;

  private String subject;
  private String sender;
  private String receiver;

  private String email;
  private LocalDateTime scheduleTime;

  @CreationTimestamp
  private LocalDateTime timestamp;

  protected EmailTransaction() { }

  private EmailTransaction(Integer id, String jobId, String subject, String sender, String receiver,
                           String email, LocalDateTime scheduleTime, LocalDateTime timestamp) {
    this.id = id;
    this.jobId = jobId;
    this.subject = subject;
    this.sender = sender;
    this.receiver = receiver;
    this.email = email;
    this.scheduleTime = scheduleTime;
    this.timestamp = timestamp;
  }

  private EmailTransaction(String jobId, String subject, String sender,
                           String receiver, String email, LocalDateTime scheduleTime) {
    this.jobId = jobId;
    this.subject = subject;
    this.sender = sender;
    this.receiver = receiver;
    this.email = email;
    this.scheduleTime = scheduleTime;
  }

  public Integer getId() {
    return id;
  }

  public String getJobId() {
    return jobId;
  }

  public String getSubject() {
    return subject;
  }

  public String getSender() {
    return sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public String getEmail() {
    return email;
  }

  public LocalDateTime getScheduleTime() {
    return scheduleTime;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private String jobId;
    private String subject;
    private String sender;
    private String receiver;
    private String email;
    private LocalDateTime scheduleTime;

    public Builder setJobId(String jobId) {
      this.jobId = jobId;
      return this;
    }

    public Builder setSubject(String subject) {
      this.subject = subject;
      return this;
    }

    public Builder setSender(String sender) {
      this.sender = sender;
      return this;
    }

    public Builder setReceiver(String receiver) {
      this.receiver = receiver;
      return this;
    }

    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    public Builder setScheduleTime(LocalDateTime scheduleTime) {
      this.scheduleTime = scheduleTime;
      return this;
    }

    public EmailTransaction build() {
      return new EmailTransaction(this.jobId, this.subject,
        this.sender, this.receiver, this.email, this.scheduleTime);
    }
  }
}
