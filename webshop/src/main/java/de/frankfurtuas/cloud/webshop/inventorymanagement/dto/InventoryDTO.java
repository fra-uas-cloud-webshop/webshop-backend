package de.frankfurtuas.cloud.webshop.inventorymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class InventoryDTO {

    private String productName;

    private String productCategory;

    private Integer quantity;
}
