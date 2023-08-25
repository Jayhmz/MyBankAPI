package com.fcmb.interview.testredo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AirtimePurchaseDTO {
    @JsonProperty("sourceAccount")
    private Long sourceAccountNumber;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("description")
    private String description;
    @JsonProperty("transactionType")
    private String transactionType;
    @JsonProperty("networkProvider")
    private String networkProvider;

    public Long getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(Long sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getNetworkProvider() {
        return networkProvider;
    }

    public void setNetworkProvider(String networkProvider) {
        this.networkProvider = networkProvider;
    }
}
