package com.fcmb.interview.testredo.model.domain;

import com.fcmb.interview.testredo.model.enums.NetworkProvider;
import com.fcmb.interview.testredo.model.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sourceAccount;
    private Long destinationAccount;
    private BigDecimal amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    private NetworkProvider networkProvider;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime transactionDate = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public NetworkProvider getNetworkProvider() {
        return networkProvider;
    }

    public void setNetworkProvider(NetworkProvider networkProvider) {
        this.networkProvider = networkProvider;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Long sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Long getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Long destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
