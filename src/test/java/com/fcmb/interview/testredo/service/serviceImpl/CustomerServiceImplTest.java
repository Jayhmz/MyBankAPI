package com.fcmb.interview.testredo.service.serviceImpl;

import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.domain.Customer;
import com.fcmb.interview.testredo.model.dto.AccountDTO;
import com.fcmb.interview.testredo.model.dto.CustomerDTO;
import com.fcmb.interview.testredo.model.enums.AccountType;
import com.fcmb.interview.testredo.model.response.CustomerResponse;
import com.fcmb.interview.testredo.repository.CustomerRepository;
import com.fcmb.interview.testredo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private AccountService accountService;
    @Mock
    private CustomerRepository customerRepository;

    //Test Data
    Set<Account> accountSet = new HashSet<>();
    Customer customer = new Customer();
    Account entityBusinessAccount;
    CustomerDTO customerTest;
    AccountDTO businessAccount;
//    AccountDTO retailAccount;

    @BeforeEach
    void setUp() {
        //DTO OBJECTS
        businessAccount = new AccountDTO();
        businessAccount.setAccountType("BUSINESS");

//        retailAccount = new AccountDTO();
//        retailAccount.setAccountType("RETAIL");

//        accountDTOSet.add(retailAccount);

        customerTest = new CustomerDTO("James", "Damilare", "Jayhmz10@gmail.com", businessAccount);

        //ENTITY OBJECT
        customer.setEmail(customerTest.getEmail());
        customer.setFirstname(customer.getFirstname());
        customer.setLastname(customer.getLastname());

        entityBusinessAccount = new Account();
        entityBusinessAccount.setAccountType(AccountType.valueOf(businessAccount.getAccountType()));
//        Account EntityRetailAccount = new Account();
//        EntityRetailAccount.setAccountType(AccountType.valueOf(retailAccount.getAccountType()));

        customer.setAccount(accountSet);
    }

    @Test
    @DisplayName("Test for saving create customer function")
    void createCustomer() {
        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(accountService.createAccount(businessAccount)).thenReturn(entityBusinessAccount);
        CustomerResponse customer1 = customerService.createCustomer(customerTest);
        System.out.println(customer1);
        assertNotNull(customer1, "created successfully");
    }
}