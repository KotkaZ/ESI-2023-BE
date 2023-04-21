package com.esi.payments.mapper;

import com.esi.payments.model.Payment;
import com.esi.payments.models.PaymentDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {

    PaymentDto entityToDto(Payment Payment);

    Payment dtoToEntity(PaymentDto PaymentDto);

    List<PaymentDto> entitiesToDtoList(List<Payment> Payments);

    List<Payment> dtoListToEntities(List<PaymentDto> PaymentDtoList);

}
