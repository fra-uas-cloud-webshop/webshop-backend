package de.frankfurtuas.cloud.webshop.productmanagement.mapper;

import de.frankfurtuas.cloud.webshop.productmanagement.dto.ProductDTO;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The product mapper.
 */
public class ProductMapper {

    /**
     * Method to map a list of Product entities to a list of ProductDTOs.
     * @param products the list of products
     * @return the list of product DTOs
     */
    public static List<ProductDTO> toProductDTOList(List<Product> products) {
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImageUrl(),
                        product.getCategory(),
                        product.getInventory().getQuantity(),
                        product.getInventory().getId()))
                .collect(Collectors.toList());
    }

    /**
     * Method to map a Product entity to a ProductDTO.
     * @param product the product entity
     * @return the product DTO
     */
    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getCategory(),
                product.getInventory().getQuantity(),
                product.getInventory().getId());
    }
}