package com.mailer.web.mapper;

import com.mailer.model.Email;
import com.mailer.model.FilterParams;
import com.mailer.web.contract.EmailRequest;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface MailerSchedulerMapper {

  Email map(EmailRequest emailRequest);

  default FilterParams mapFilterParams(String sender, String receiver, LocalDate scheduledAt) {
    FilterParams filterParams = null;
    if (Objects.nonNull(sender) || Objects.nonNull(receiver) || Objects.nonNull(scheduledAt)) {
      filterParams = FilterParams.builder()
        .setSender(sender)
        .setReceiver(receiver)
        .setScheduledTime(getNullableLocalDateTime(scheduledAt))
        .build();
    }
    return filterParams;
  }

  default LocalDateTime getNullableLocalDateTime(LocalDate localDate) {
    return Objects.nonNull(localDate) ? localDate.atStartOfDay() : null;
  }
}
