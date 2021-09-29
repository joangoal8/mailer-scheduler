package com.mailer.service;

import com.mailer.model.Email;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MailerSchedulerServiceImpl implements MailerSchedulerService {

  private final JobScheduler jobScheduler;
  private final MailerService mailerService;

  @Autowired
  public MailerSchedulerServiceImpl(JobScheduler jobScheduler,
                                    MailerService mailerService) {
    this.jobScheduler = jobScheduler;
    this.mailerService = mailerService;
  }

  @Override
  public String addEmailJob(LocalDateTime scheduleTime, Email email) {
    return jobScheduler.schedule(scheduleTime, () -> mailerService.sendEmail(email)).asUUID().toString();
  }
}
