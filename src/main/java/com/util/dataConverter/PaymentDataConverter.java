package com.util.dataConverter;

import com.entity.Payment;

import java.io.IOException;
import java.util.ArrayList;

public interface PaymentDataConverter {

    static ArrayList<Payment> generate(byte[] data, String type) throws IOException {
        PaymentDataConverter paymentDataConverter = DataType.valueOf(type.toUpperCase()).getPaymentDataConverter();
        return paymentDataConverter.convert(data);
    }

    ArrayList<Payment> convert(byte[] data) throws IOException;
}
