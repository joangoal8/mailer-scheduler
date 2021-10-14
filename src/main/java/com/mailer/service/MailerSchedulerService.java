package com.mailer.service;

import com.mailer.model.Email;
import com.mailer.model.EmailTransaction;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MailerSchedulerService {

  Optional<EmailTransaction> getEmail(Integer id);

  EmailTransaction addEmailJob(LocalDateTime scheduleTime, Email email);

}
