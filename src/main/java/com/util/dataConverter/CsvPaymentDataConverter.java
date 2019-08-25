package com.util.dataConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.entity.Payment;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvPaymentDataConverter implements PaymentDataConverter {

    private static final ObjectReader reader;

    static {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
        reader = csvMapper.readerFor(Payment[].class).with(csvSchema);
    }

    @Override
    public ArrayList<Payment> convert(byte[] data) throws IOException {
        List<Payment> payments = Arrays.asList(reader.readValue(data));
        return new ArrayList<>(payments);
    }

}
