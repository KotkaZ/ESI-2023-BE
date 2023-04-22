package com.esi.payments.repository;

import com.esi.payments.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payment, Integer> {
}
