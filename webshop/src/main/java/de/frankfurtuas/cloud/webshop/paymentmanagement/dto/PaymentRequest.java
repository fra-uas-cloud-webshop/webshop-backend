package de.frankfurtuas.cloud.webshop.paymentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private String paymentMethod;

    private double amount;
}