package com.mailer.service;

import com.mailer.model.Email;
import com.mailer.model.EmailTransaction;
import com.mailer.model.FilterParams;
import com.mailer.store.EmailTransactionRepository;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

  @Override
  public void deleteEmailJob(Integer id) {
    emailTransactionRepository.findById(id).ifPresent(emailTransaction -> {
      emailTransactionRepository.deleteById(id);
      jobScheduler.delete(emailTransaction.getJobId());
    });
  }

  //TODO Add search/filter business logic
  @Override
  public String filterEmailTransactions(FilterParams filterParams) {
    String query;
    try {
      query = getQueryMethod(new StringBuilder("findBy"), filterParams, 0, false);
      getEmailTransactions(query, filterParams);
    } catch (NoSuchMethodException e) {
      query = "No method";
    } catch (InvocationTargetException e) {
      query = "Error invocation";
    } catch (IllegalAccessException e) {
      query = "Illegar Access";
    }
    return query;
  }

  private String getQueryMethod(StringBuilder query,
                                FilterParams filterParams,
                                int index, boolean requiresAnd)
    throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Field[] fields = filterParams.getClass().getDeclaredFields();
    if (index == fields.length) {
      return query.toString();
    }
    String fieldName = fields[index].getName().substring(0, 1).toUpperCase()
      + fields[index].getName().substring(1);
    if (Objects.nonNull(filterParams.getClass()
      .getDeclaredMethod("get" + fieldName).invoke(filterParams))) {
      if (requiresAnd) {
        query.append("And").append(fieldName);
      } else {
        query.append(fieldName);
        requiresAnd = true;
      }
    }
    return getQueryMethod(query, filterParams, index + 1, requiresAnd);
  }

  private List<EmailTransaction> getEmailTransactions(String query, FilterParams filterParams)
    throws NoSuchMethodException {
    Method method = emailTransactionRepository.getClass().getDeclaredMethod(query);
    System.out.println(method.getName());
    return Collections.emptyList();
  }

}
