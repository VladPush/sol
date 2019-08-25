package com.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Payment;
import com.service.PaymentService;

@RestController("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/save",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void save(@RequestParam("file") MultipartFile file, @RequestParam String type) throws IOException {
            paymentService.savePaymentsFromObject(file.getBytes(), type);
    }

    @GetMapping("/paymentsWithMaxAmount")
    public Collection<Payment> getPaymentsWithMaxAmount() {
        return paymentService.getPaymentsWithMaxAmount();
    }
}
