package com.service.validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.DataBinder;

import com.entity.Payment;

@Service
public class PaymentValidatorService {

    private final PaymentValidator paymentValidator;

    @Autowired
    public PaymentValidatorService(PaymentValidator paymentValidator) {
        this.paymentValidator = paymentValidator;
    }

    public ArrayList<Payment> validate(ArrayList<Payment> payments) {
        Iterator<Payment> i = payments.iterator();
        while (i.hasNext()) {
            Payment p = i.next();
            DataBinder dataBinder = new DataBinder(p);
            dataBinder.addValidators(paymentValidator);
            dataBinder.validate();
            if (dataBinder.getBindingResult().hasErrors()) {
                i.remove();
                /* Тут нужно возвращать полную информацию о результате работы сервиса,
                 *  хотябы в случае ошибок - что НЕ сохранилось и почему.*/
                System.out.println("Payment with id " + p.getId() + " is not valid. Because of " +
                                   dataBinder.getBindingResult().getFieldErrors().stream()
                                             .map(err -> err.getField() + " is " + err.getCode())
                                             .collect(Collectors.joining( ", ","",".")));
            }
        }
        return payments;
    }
}
