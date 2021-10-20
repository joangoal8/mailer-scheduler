package com.mailer.model;

import java.time.LocalDateTime;

public class FilterParams {

  private final String sender;
  private final String receiver;
  private final LocalDateTime scheduledTime;

  public FilterParams(Builder builder) {
    this.sender = builder.sender;
    this.receiver = builder.receiver;
    this.scheduledTime = builder.scheduledTime;
  }

  public String getSender() {
    return sender;
  }

  public String getReceiver() {
    return receiver;
  }

  public LocalDateTime getScheduledTime() {
    return scheduledTime;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private String sender;
    private String receiver;
    private LocalDateTime scheduledTime;

    public Builder setSender(String sender) {
      this.sender = sender;
      return this;
    }

    public Builder setReceiver(String receiver) {
      this.receiver = receiver;
      return this;
    }

    public Builder setScheduledTime(LocalDateTime scheduledTime) {
      this.scheduledTime = scheduledTime;
      return this;
    }

    public FilterParams build() {
      return new FilterParams(this);
    }
  }
}