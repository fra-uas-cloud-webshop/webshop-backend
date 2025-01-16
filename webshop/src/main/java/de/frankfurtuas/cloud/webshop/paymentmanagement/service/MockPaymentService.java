package de.frankfurtuas.cloud.webshop.paymentmanagement.service;

import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentRequest;
import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MockPaymentService implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(MockPaymentService.class);

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {

        logger.info("Processing payment: Method={}, Amount={}", request.getPaymentMethod(), request.getAmount());

        // Simulating a simple validation
        if (request.getAmount() <= 0) {
            logger.error("Payment failed: Invalid amount {}", request.getAmount());
            return new PaymentResponse(false, "Invalid payment amount");
        }

        // Mock a success scenario for valid requests
        logger.info("Payment successful for method={} and amount={}", request.getPaymentMethod(), request.getAmount());
        return new PaymentResponse(true, "Payment processed successfully with " + request.getPaymentMethod());
    }
}