package com.mailer.service;

import com.mailer.model.Email;

import java.time.LocalDateTime;

public interface MailerSchedulerService {

  String addEmailJob(LocalDateTime scheduleTime, Email email);
}
