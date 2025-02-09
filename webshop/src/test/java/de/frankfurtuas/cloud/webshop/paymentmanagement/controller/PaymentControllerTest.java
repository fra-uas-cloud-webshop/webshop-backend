package de.frankfurtuas.cloud.webshop.paymentmanagement.controller;

import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentRequest;
import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentResponse;
import de.frankfurtuas.cloud.webshop.paymentmanagement.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessPayment_Success() {
        PaymentRequest request = new PaymentRequest("PayPal", 100.0);
        PaymentResponse mockResponse = new PaymentResponse(true, "Payment processed successfully with PayPal");

        when(paymentService.processPayment(request)).thenReturn(mockResponse);

        ResponseEntity<PaymentResponse> response = paymentController.processPayment(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isTrue();
        assertThat(response.getBody().getMessage()).isEqualTo("Payment processed successfully with PayPal");
    }

    @Test
    void testProcessPayment_Failure() {
        PaymentRequest request = new PaymentRequest("PayPal", -10.0);
        PaymentResponse mockResponse = new PaymentResponse(false, "Invalid payment amount");

        when(paymentService.processPayment(request)).thenReturn(mockResponse);

        ResponseEntity<PaymentResponse> response = paymentController.processPayment(request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getMessage()).isEqualTo("Invalid payment amount");
    }
}