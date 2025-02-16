package de.frankfurtuas.cloud.webshop.paymentmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * PaymentResponse
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentResponse {

    private boolean success;

    private String message;
}