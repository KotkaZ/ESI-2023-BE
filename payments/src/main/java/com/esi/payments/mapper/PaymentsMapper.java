package com.esi.payments.mapper;

import com.esi.payments.dto.PaymentEvent;
import com.esi.payments.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "status", source = "status")
    PaymentEvent mapToEvent(Payment payment);
}
