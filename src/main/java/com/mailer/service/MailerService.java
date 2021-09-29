package com.mailer.service;

import com.mailer.model.Email;

public interface MailerService {

  void sendEmail(Email email);
}
