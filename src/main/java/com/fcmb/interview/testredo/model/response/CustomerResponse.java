package com.fcmb.interview.testredo.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.domain.Customer;
import com.fcmb.interview.testredo.model.enums.AccountType;
import com.fcmb.interview.testredo.model.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Set;
@JsonSerialize
public class CustomerResponse {

    private String name;
    private String email;
    private Long accountNumber;
    private BigDecimal avalaibleBalance;
    private AccountType accountType;

    public CustomerResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAvalaibleBalance() {
        return avalaibleBalance;
    }

    public void setAvalaibleBalance(BigDecimal avalaibleBalance) {
        this.avalaibleBalance = avalaibleBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
