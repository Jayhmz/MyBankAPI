package com.fcmb.interview.testredo.service.serviceImpl;

import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.dto.AccountDTO;
import com.fcmb.interview.testredo.model.enums.AccountType;
import com.fcmb.interview.testredo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;
    @Mock
    private AccountRepository accountRepository;

    //Test Data
    AccountDTO account = new AccountDTO();

    @BeforeEach
    void setUp(){
        account.setAccountType("BUSINESS");
        account.setAccountNumber((int) AccountServiceImpl.generateAccountNumber());
    }

    @Test
    void createAccount() {
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
        System.out.println(account.getAccountNumber());

        Account account1 = new Account();
        account1.setAccountType(AccountType.valueOf(account.getAccountType()));
        account1.setAccountNumber((long) account.getAccountNumber());
        System.out.println(account1);

        assertNotNull(accountService.createAccount(account), "created successfully");
        assertEquals(account.getAccountNumber(), account1.getAccountNumber(), "Account number generated successfully");
    }
}