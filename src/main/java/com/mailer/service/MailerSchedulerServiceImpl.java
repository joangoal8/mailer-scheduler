package com.mailer.service;

import com.mailer.model.Email;
import com.mailer.model.EmailTransaction;
import com.mailer.store.EmailTransactionRepository;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MailerSchedulerServiceImpl implements MailerSchedulerService {

  private final JobScheduler jobScheduler;
  private final MailerService mailerService;
  private final EmailTransactionRepository emailTransactionRepository;

  @Autowired
  public MailerSchedulerServiceImpl(JobScheduler jobScheduler,
                                    MailerService mailerService,
                                    EmailTransactionRepository emailTransactionRepository) {
    this.jobScheduler = jobScheduler;
    this.mailerService = mailerService;
    this.emailTransactionRepository = emailTransactionRepository;
  }

  @Override
  public Optional<EmailTransaction> getEmail(Integer id) {
    return emailTransactionRepository.findById(id);
  }

  @Override
  public EmailTransaction addEmailJob(LocalDateTime scheduleTime, Email email) {
    String jobId = jobScheduler.schedule(scheduleTime, () -> mailerService.sendEmail(email)).asUUID().toString();
    return emailTransactionRepository.save(EmailTransaction.builder()
      .setJobId(jobId).setSubject(email.getSubject()).setSender(email.getSender())
      .setReceiver(email.getReceiver()).setEmail(email.toString())
      .setScheduleTime(scheduleTime).build());
  }
}
