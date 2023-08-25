package com.fcmb.interview.testredo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransferDTO {
    @JsonProperty("sourceAccount")
    private Long sourceAccountNumber;
    @JsonProperty("destinationAccount")
    private Long destinationAccountNumber;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("description")
    private String description;

    public Long getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(Long sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public Long getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(Long destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
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
}
