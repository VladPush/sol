package com.service.validator;

import com.entity.Payment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PaymentValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Payment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Payment p = (Payment) o;
        if (p.getAmount() < 0) {
            errors.rejectValue("amount", "below zero");
        }
        if (p.getPayerAccount() == p.getReceiverAccount()) {
            errors.rejectValue("payerAccount", "equals to receiverAccount");
        }
    }
}
