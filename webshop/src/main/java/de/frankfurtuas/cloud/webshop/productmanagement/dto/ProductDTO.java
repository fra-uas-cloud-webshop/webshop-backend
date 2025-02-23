package de.frankfurtuas.cloud.webshop.productmanagement.dto;

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
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private String category;

    private Integer quantity;

    private Long inventoryId;
}
