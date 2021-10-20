package com.mailer.service;

import com.mailer.model.Email;
import com.mailer.model.EmailTransaction;
import com.mailer.model.FilterParams;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MailerSchedulerService {

  Optional<EmailTransaction> getEmail(Integer id);

  EmailTransaction addEmailJob(LocalDateTime scheduleTime, Email email);

  void deleteEmailJob(Integer id);

  //TODO Add search/filter business logic
  //List<EmailTransaction> filterEmailTransactions(FilterParams filterParams);

  String filterEmailTransactions(FilterParams filterParams);

}
