package de.frankfurtuas.cloud.webshop.paymentmanagement.service;

import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentRequest;
import de.frankfurtuas.cloud.webshop.paymentmanagement.dto.PaymentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource(locations = "classpath:application-test.properties")
class MockPaymentServiceTest {

    private final PaymentService paymentService = new MockPaymentService();

    @Test
    void testValidPayment() {
        PaymentRequest request = new PaymentRequest("PayPal", 100.0);
        PaymentResponse response = paymentService.processPayment(request);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getMessage()).isEqualTo("Payment processed successfully with PayPal");
    }

    @Test
    void testInvalidPaymentAmount() {
        PaymentRequest request = new PaymentRequest("PayPal", -50.0);
        PaymentResponse response = paymentService.processPayment(request);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo("Invalid payment amount");
    }
}