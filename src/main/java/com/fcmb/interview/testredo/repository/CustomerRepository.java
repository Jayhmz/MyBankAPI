package com.fcmb.interview.testredo.repository;

import com.fcmb.interview.testredo.model.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
