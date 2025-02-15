package de.frankfurtuas.cloud.webshop.inventorymanagement.service;

import de.frankfurtuas.cloud.webshop.inventorymanagement.repository.InventoryRepository;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Product updateStock(Long productId, int newStock) {
        Product product = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(newStock);
        return inventoryRepository.save(product);
    }
}
