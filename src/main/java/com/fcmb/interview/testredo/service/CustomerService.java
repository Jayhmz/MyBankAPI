package com.fcmb.interview.testredo.service;

import com.fcmb.interview.testredo.model.dto.CustomerDTO;
import com.fcmb.interview.testredo.model.response.AccountResponse;
import com.fcmb.interview.testredo.model.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerDTO customerDTO);

    AccountResponse findByAccountNumber(Long accountNumber);

}
