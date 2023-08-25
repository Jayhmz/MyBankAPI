package com.fcmb.interview.testredo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fcmb.interview.testredo.model.enums.AccountType;

import java.math.BigDecimal;

public class AccountDTO {
    private int accountNumber;
//    private BigDecimal openingBalance ;
//    private BigDecimal closingBalance;
//    private BigDecimal availableBalance;
    @JsonProperty("accountType")
    private String accountType;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
