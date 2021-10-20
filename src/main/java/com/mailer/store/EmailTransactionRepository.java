package com.mailer.store;

import com.mailer.model.EmailTransaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailTransactionRepository extends JpaRepository<EmailTransaction, Integer> {

  List<EmailTransaction> findBySender(String sender);

  List<EmailTransaction> findBySender(String sender, Sort sort);

  List<EmailTransaction> findByReceiver(String receiver);

  List<EmailTransaction> findByReceiver(String receiver, Sort sort);

  List<EmailTransaction> findBySenderAndReceiver(String sender, String receiver, Sort sort);

  List<EmailTransaction> findByScheduleTimeBetween(LocalDateTime scheduleTimeIni,
                                                   LocalDateTime scheduleTimeEnd);

  List<EmailTransaction> findBySenderAndScheduleTimeBetween(String sender,
                                                            LocalDateTime scheduleTimeIni,
                                                            LocalDateTime scheduleTimeEnd);

  List<EmailTransaction> findByReceiverAndScheduleTimeBetween(String receiver,
                                                              LocalDateTime scheduleTimeIni,
                                                              LocalDateTime scheduleTimeEnd);

  List<EmailTransaction> findBySenderAndReceiverAndScheduleTimeBetween(String sender,
                                                                       String receiver,
                                                                       LocalDateTime scheduleTimeIni,
                                                                       LocalDateTime scheduleTimeEnd);

}
