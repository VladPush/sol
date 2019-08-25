package com.util.dataConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.entity.Payment;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlPaymentDataConverter implements PaymentDataConverter {

    private final ObjectReader reader = new XmlMapper().readerFor(Payment[].class);

    @Override
    public ArrayList<Payment> convert(byte[] data) throws IOException {
        List<Payment> payments = Arrays.asList(reader.readValue(data));
        return new ArrayList<>(payments);
    }

}
