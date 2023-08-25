package com.fcmb.interview.testredo.service.serviceImpl;

import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.domain.Customer;
import com.fcmb.interview.testredo.model.dto.CustomerDTO;
import com.fcmb.interview.testredo.model.response.AccountResponse;
import com.fcmb.interview.testredo.model.response.CustomerResponse;
import com.fcmb.interview.testredo.repository.CustomerRepository;
import com.fcmb.interview.testredo.service.AccountService;
import com.fcmb.interview.testredo.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AccountService accountService) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());

        Account account = accountService.createAccount(customerDTO.getAccountDTO());
        account.setAccountNumber(AccountServiceImpl.generateAccountNumber());
        account.setCustomer(customer);
        System.out.println(account.getAccountNumber());
        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        customer.setAccount(accounts);

        customerRepository.save(customer);
        CustomerResponse response = new CustomerResponse();
        response.setAccountNumber(account.getAccountNumber());
        response.setAccountType(account.getAccountType());
        response.setName(customer.getFirstname() + " "+ customer.getLastname());
        response.setEmail(customer.getEmail());
        return response;
    }

    @Override
    public AccountResponse findByAccountNumber(Long accountNumber) {
        Account account = accountService.findByAccountNumber(accountNumber);
        AccountResponse response = new AccountResponse(account);
        response.getAccount().getCustomer().setAccount(null);
        return response;
    }


}
