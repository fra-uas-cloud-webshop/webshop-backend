package de.frankfurtuas.cloud.webshop.mailservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailNotificationServiceIntegrationTest {

    @Autowired
    private EmailNotificationService emailNotificationService;

    @Test
    void testSendRealEmail() {
        // Arrange
        String toEmail = "test@outlook.de"; // Replace with your test recipient
        String orderId = "12345";

        // Act
        emailNotificationService.sendOrderConfirmation(toEmail, orderId);

        // Assert
        // Check your inbox to verify the email was received.
    }
}
