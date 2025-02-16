package de.frankfurtuas.cloud.webshop.paymentmanagement.service;

import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentRequest;
import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentResponse;

/**
 * The Payment service.
 */
public interface PaymentService {

    /**
     * Process payment.
     *
     * @param request the payment request
     * @return the payment response
     */
    PaymentResponse processPayment(PaymentRequest request);
}
