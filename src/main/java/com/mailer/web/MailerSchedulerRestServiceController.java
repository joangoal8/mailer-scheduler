package com.mailer.web;

import com.mailer.service.MailerSchedulerService;
import com.mailer.web.contract.EmailRequest;
import com.mailer.web.mapper.MailerSchedulerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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

    @GetMapping(value = "/jobs/mails")
    public String getJobsMails() {
        return MailerSchedulerConstants.PONG_MESSAGE;
    }

    @PostMapping(value = "/jobs/mails")
    public ResponseEntity<String> postJobMail(@RequestParam(required = false, name = "scheduleTo") String scheduleTo,
                                              @RequestBody EmailRequest emailRequest) {
        try {
            var scheduleTime = Objects.nonNull(scheduleTo)
              ? LocalDateTime.parse(scheduleTo)
              : LocalDateTime.now().plusMinutes(1);
            return ResponseEntity.ok(mailerSchedulerService.addEmailJob(scheduleTime,
              mailerSchedulerMapper.map(emailRequest)));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
