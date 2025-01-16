package de.frankfurtuas.cloud.webshop.paymentmanagement.dto;

public class PaymentRequest {

    private String paymentMethod;

    private double amount;

    public PaymentRequest() {}

    public PaymentRequest(String paymentMethod, double amount) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}