package com.mailer.web;

import com.mailer.model.EmailTransaction;
import com.mailer.service.MailerSchedulerService;
import com.mailer.web.contract.EmailRequest;
import com.mailer.web.mapper.MailerSchedulerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/mailer-scheduler/v1",
  produces = MailerSchedulerConstants.JSON_CONTENT_TYPE)
public class MailerSchedulerRestServiceController {

    private final MailerSchedulerMapper mailerSchedulerMapper;
    private final MailerSchedulerService mailerSchedulerService;

    @Autowired
    public MailerSchedulerRestServiceController(MailerSchedulerMapper mailerSchedulerMapper,
                                                MailerSchedulerService mailerSchedulerService) {
        this.mailerSchedulerMapper = mailerSchedulerMapper;
        this.mailerSchedulerService = mailerSchedulerService;
    }

    @GetMapping(value = "/ping")
    public String ping() {
        return MailerSchedulerConstants.PONG_MESSAGE;
    }

    @PostMapping(value = "/jobs/mails")
    public ResponseEntity<EmailTransaction> postJobMail(@RequestParam(required = false, name = "scheduleTo") String scheduleTo,
                                                        @RequestBody EmailRequest emailRequest) {
        try {
            var scheduleTime = Objects.nonNull(scheduleTo)
              ? LocalDateTime.parse(scheduleTo)
              : LocalDateTime.now().plusMinutes(1);
            return new ResponseEntity<>(mailerSchedulerService.addEmailJob(scheduleTime,
              mailerSchedulerMapper.map(emailRequest)), HttpStatus.CREATED);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/jobs/mails/{id}")
    public ResponseEntity<Void> getMailJob(@PathVariable("id") Integer id) {
        mailerSchedulerService.deleteEmailJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/mails/{id}")
    public ResponseEntity<EmailTransaction> getMail(@PathVariable("id") Integer id) {
        Optional<EmailTransaction> email = mailerSchedulerService.getEmail(id);
        return email.isPresent()
          ? ResponseEntity.of(email)
          : ResponseEntity.notFound().build();
    }

}
