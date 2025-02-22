package de.frankfurtuas.cloud.webshop.mailmanagement;

import de.frankfurtuas.cloud.webshop.ordermanagement.model.Order;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderItem;
import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends an email notification when an order status changes.
     * @param order The order that has changed status.
     */
    public void sendOrderStatusEmail(Order order) {
        String subject = "Order Update - Order #" + order.getId();
        String body = buildOrderStatusEmail(order);

        sendEmail(order.getCustomerEmail(), subject, body);
    }

    private String buildOrderStatusEmail(Order order) {
        String statusMessage =
                switch (order.getStatus()) {
                    case PENDING -> "Your order has been received and is being processed.";
                    case PROCESSED -> "Your order has been processed and is ready for shipping.";
                    case SHIPPED -> "Your order has been shipped! You can track it using the tracking number below.";
                    case DELIVERED -> "Your order has been delivered. We hope you enjoy your purchase!";
                    case CANCELLED -> "Your order has been cancelled. If this was a mistake, please contact us.";
                };

        StringBuilder orderDetails = new StringBuilder();
        if (order.getItems() != null && !order.getItems().isEmpty()) {
            for (OrderItem item : order.getItems()) {
                orderDetails
                        .append("- ")
                        .append(item.getProduct().getName())
                        .append(" | Quantity: ")
                        .append(item.getQuantity())
                        .append(" | Price: $")
                        .append(item.getTotalPriceForItem())
                        .append("\n");
            }
        }

        String trackingInfo =
                (order.getStatus() == OrderStatus.SHIPPED) ? "\nTracking Number: #" + order.getId() + "\n" : "";

        return "Dear " + order.getCustomerName() + ",\n\n"
                + "Your order #" + order.getId() + " is now: " + order.getStatus() + ".\n"
                + statusMessage + "\n"
                + trackingInfo
                + "\nOrder Summary:\n"
                + orderDetails
                + "\nTotal Amount: $" + order.getTotalAmount() + "\n\n"
                + "Thank you for shopping with us!\n\n"
                + "Best regards,\nUAS Webshop Team";
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