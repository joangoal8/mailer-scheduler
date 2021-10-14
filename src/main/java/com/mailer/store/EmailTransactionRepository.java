package com.mailer.store;

import com.mailer.model.EmailTransaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailTransactionRepository extends JpaRepository<EmailTransaction, Integer> {

  List<EmailTransaction> findBySender(String sender);

  List<EmailTransaction> findBySender(String sender, Sort sort);

  List<EmailTransaction> findByReceiver(String receiver);

  List<EmailTransaction> findByReceiver(String receiver, Sort sort);

}
