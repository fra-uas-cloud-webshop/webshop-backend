package de.frankfurtuas.cloud.webshop.ordermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDTO {

    private Long orderId;

    private String productName;

    private BigDecimal productPrice;

    private Integer quantity;

    private BigDecimal totalPriceForItem;
}
