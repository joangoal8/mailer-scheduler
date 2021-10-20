package com.mailer.web.mapper;

import com.mailer.model.Email;
import com.mailer.model.Email.Builder;
import com.mailer.web.contract.EmailRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-20T11:08:44+0200",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)"
)
@Component
public class MailerSchedulerMapperImpl implements MailerSchedulerMapper {

    @Override
    public Email map(EmailRequest emailRequest) {
        if ( emailRequest == null ) {
            return null;
        }

        Builder email = Email.builder();

        email.subject( emailRequest.getSubject() );
        email.sender( emailRequest.getSender() );
        email.password( emailRequest.getPassword() );
        email.receiver( emailRequest.getReceiver() );
        email.text( emailRequest.getText() );

        return email.build();
    }
}
