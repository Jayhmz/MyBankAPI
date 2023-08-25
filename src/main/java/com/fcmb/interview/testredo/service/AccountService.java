package com.fcmb.interview.testredo.service;

import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountService {
    Account createAccount(AccountDTO accountDTO);
    Account findByAccountNumber(Long accountNumber);
}
