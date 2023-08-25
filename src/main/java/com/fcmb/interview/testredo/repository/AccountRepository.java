package com.fcmb.interview.testredo.repository;

import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(Long accountNumber);
}
