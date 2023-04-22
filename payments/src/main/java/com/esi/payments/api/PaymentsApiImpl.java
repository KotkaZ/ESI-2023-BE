package com.esi.payments.api;


import com.esi.payments.PaymentsApi;
import com.esi.payments.mapper.PaymentsMapper;
import com.esi.payments.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentsApiImpl implements PaymentsApi {

    private final PaymentsMapper PaymentMapper;
    private final PaymentsRepository PaymentRepository;



}
