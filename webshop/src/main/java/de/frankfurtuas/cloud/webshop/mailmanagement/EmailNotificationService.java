package de.frankfurtuas.cloud.webshop.mailmanagement;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmation(String toEmail, String orderId) {
        String subject = "Order Confirmation - Order #" + orderId;
        String body = "Thank you for your purchase! Your order #" + orderId + " has been confirmed.";

        sendEmail(toEmail, subject, body);
    }

    public void sendShippingNotification(String toEmail, String trackingNumber) {
        String subject = "Shipping Notification - Tracking #" + trackingNumber;
        String body = "Your order has been shipped! Use the tracking number " + trackingNumber + " to track your shipment.";

        sendEmail(toEmail, subject, body);
    }

    private void sendEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            // Log the error
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}