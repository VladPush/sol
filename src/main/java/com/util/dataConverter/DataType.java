package com.util.dataConverter;

public enum DataType {
    JSON(new JsonPaymentDataConverter()),
    XML(new XmlPaymentDataConverter()),
    CSV(new CsvPaymentDataConverter());

    private final PaymentDataConverter paymentDataConverter;

    DataType(PaymentDataConverter paymentDataConverter) {
        this.paymentDataConverter = paymentDataConverter;
    }

    public PaymentDataConverter getPaymentDataConverter() {
        return paymentDataConverter;
    }
}
