package com.esi.payments.api;


import com.esi.payments.PaymentsApi;
import com.esi.payments.mapper.PaymentsMapper;
import com.esi.payments.models.PaymentDto;
import com.esi.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentsApiImpl implements PaymentsApi {

    private final PaymentsMapper PaymentMapper;
    private final PaymentsRepository PaymentRepository;

    @Override
    public ResponseEntity<Void> createPayment(PaymentDto PaymentDto) {
        // TODO

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updatePayment(Integer roomId, PaymentDto PaymentDto) {
        // TODO

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
