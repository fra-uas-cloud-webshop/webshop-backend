package de.frankfurtuas.cloud.webshop.paymentmanagement.service;

import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentRequest;
import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse processPayment(PaymentRequest request);

}
