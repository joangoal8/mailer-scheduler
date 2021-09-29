package com.mailer.web.mapper;

import com.mailer.model.Email;
import com.mailer.web.contract.EmailRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MailerSchedulerMapper {

  Email map(EmailRequest emailRequest);
}
