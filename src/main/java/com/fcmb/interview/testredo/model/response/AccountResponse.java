package com.fcmb.interview.testredo.model.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fcmb.interview.testredo.model.domain.Account;
import com.fcmb.interview.testredo.model.domain.Customer;

@JsonSerialize
public class AccountResponse {

    private Account account;

    public AccountResponse() {
    }

    public AccountResponse(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
