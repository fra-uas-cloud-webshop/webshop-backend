package de.frankfurtuas.cloud.webshop.paymentmanagement.controller;

import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentRequest;
import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentResponse;
import de.frankfurtuas.cloud.webshop.paymentmanagement.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Payment controller.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Instantiates a new Payment controller.
     *
     * @param paymentService the payment service
     */
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Process payment.
     *
     * @param request the payment request
     * @return the payment response
     */
    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(@RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(request);
        return ResponseEntity.ok(response);
    }
}