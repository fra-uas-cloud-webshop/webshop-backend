package de.frankfurtuas.cloud.webshop.ordermanagement.model;

public enum OrderStatus {
    PENDING, // New order created
    PROCESSED, // Order processed and ready for shipping
    SHIPPED, // Order has been shipped
    DELIVERED, // Order delivered to the customer
    CANCELLED // Order cancelled
}
