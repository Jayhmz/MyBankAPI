package com.fcmb.interview.testredo.repository;

import com.fcmb.interview.testredo.model.domain.Customer;
import com.fcmb.interview.testredo.model.domain.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    @Query(value = "SELECT COUNT(*) FROM transaction_history t " +
            "WHERE MONTH(t.created_at) = MONTH(CURRENT_DATE) " +
            "AND t.source_account = :accountNumber", nativeQuery = true)
    int countByMonth(@Param("accountNumber") Long accountNumber);

}
