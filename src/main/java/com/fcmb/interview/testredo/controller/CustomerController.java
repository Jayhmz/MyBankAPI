package com.fcmb.interview.testredo.controller;

import com.fcmb.interview.testredo.model.dto.AccountNumberDTO;
import com.fcmb.interview.testredo.model.dto.CustomerDTO;
import com.fcmb.interview.testredo.model.response.AccountResponse;
import com.fcmb.interview.testredo.model.response.CustomerResponse;
import com.fcmb.interview.testredo.model.response.ResponseDTO;
import com.fcmb.interview.testredo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<CustomerResponse>> createCustomer(@RequestBody CustomerDTO customerDTO){

        ResponseDTO<CustomerResponse> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Customer created successfully");
        response.setData(customerService.createCustomer(customerDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/view")
    public ResponseEntity<ResponseDTO<AccountResponse>> viewCustomer(@RequestBody AccountNumberDTO accountNumberDTO){
        ResponseDTO<AccountResponse> response = new ResponseDTO<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Customer created successfully");
        response.setData(customerService.findByAccountNumber(accountNumberDTO.getAccountNumber()));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
