package de.frankfurtuas.cloud.webshop.ordermanagement.dto;

import de.frankfurtuas.cloud.webshop.ordermanagement.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {

    private String customerName;

    private String customerEmail;

    private String shippingAddress;

    private String phoneNumber;

    private BigDecimal totalAmount;

    private String paymentMethod;

    private OrderStatus status;

    private List<OrderItemDTO> items;
}
