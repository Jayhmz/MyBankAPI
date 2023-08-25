package com.fcmb.interview.testredo.service.serviceImpl;

import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.dto.AccountDTO;
import com.fcmb.interview.testredo.model.enums.AccountType;
import com.fcmb.interview.testredo.repository.AccountRepository;
import com.fcmb.interview.testredo.service.AccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountType(AccountType.valueOf(accountDTO.getAccountType()));
        accountRepository.save(account);
        return account;
    }

    @Override
    public Account findByAccountNumber(Long accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public static long generateAccountNumber(){
        Random random = new Random();
        long min = 1000000000L; // 10-digit minimum value
        long max = 9999999999L; // 10-digit maximum value
        return min + ((long) (random.nextDouble() * (max - min + 1)));
    }
}
