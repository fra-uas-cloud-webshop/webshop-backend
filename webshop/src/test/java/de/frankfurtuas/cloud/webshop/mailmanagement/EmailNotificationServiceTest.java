package de.frankfurtuas.cloud.webshop.mailmanagement;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class EmailNotificationServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailNotificationService emailNotificationService;

    @Test
    void testSendOrderConfirmation() {
        // Arrange
        String toEmail = "zakaria.jouahri@outlook.de";
        String orderId = "12345";

        // Act
        emailNotificationService.sendOrderConfirmation(toEmail, orderId);

        // Assert
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testSendShippingNotificationEmail() {
        // Arrange
        String toEmail = "customer@example.com";
        String trackingNumber = "TRACK123";

        // Act
        emailNotificationService.sendShippingNotification(toEmail, trackingNumber);

        // Assert
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void testEmailSendingFailure() {
        // Arrange
        doThrow(new RuntimeException("Email server not reachable"))
                .when(mailSender)
                .send(any(SimpleMailMessage.class));

        // Act & Assert
        assertDoesNotThrow(() -> emailNotificationService.sendOrderConfirmation("customer@example.com", "12345"));
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}