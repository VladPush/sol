package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Payment;
import com.repository.PaymentRepository;
import com.service.validator.PaymentValidatorService;
import com.util.dataConverter.DataType;
import com.util.dataConverter.PaymentDataConverter;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentValidatorService paymentValidatorService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentValidatorService paymentValidatorService) {
        this.paymentRepository = paymentRepository;
        this.paymentValidatorService = paymentValidatorService;
    }

    public void savePaymentsFromObject(byte[] data, String type) throws IOException {
        ArrayList<Payment> payments = PaymentDataConverter.generate(data, type);
        ArrayList<Payment> validatePayments = paymentValidatorService.validate(payments);

        paymentRepository.saveAll(validatePayments);
    }

    public Collection<Payment> getPaymentsWithMaxAmount() {
        return paymentRepository.getPaymentsWithMaxAmount();
    }
}
