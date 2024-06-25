package com.example.DAMH.service;

import org.springframework.stereotype.Service;

@Service
public class MoMoService {

    public String generatePaymentUrl(String orderId, long amount) {
        // Mocked payment URL for testing purposes
        return "https://test-payment.momo.vn/gw_payment/transactionProcessor?orderId=" + orderId + "&amount=" + amount;
    }
}
