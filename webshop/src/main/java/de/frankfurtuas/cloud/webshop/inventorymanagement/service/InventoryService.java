package de.frankfurtuas.cloud.webshop.inventorymanagement.service;

import de.frankfurtuas.cloud.webshop.common.exception.InventoryNotFoundException;
import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;
import de.frankfurtuas.cloud.webshop.inventorymanagement.repository.InventoryRepository;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The inventory service.
 */
@Service
public class InventoryService {

    /**
     * Logger instance.
     */
    private static final Logger LOG = LoggerFactory.getLogger(InventoryService.class);

    private final InventoryRepository inventoryRepository;

    /**
     * Constructor.
     *
     * @param inventoryRepository the inventory repository
     */
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Get all inventories.
     *
     * @return the list of inventories
     */
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    /**
     * Create a new inventory.
     *
     * @param product  the product
     * @param quantity the quantity
     */
    public void createInventory(Product product, Integer quantity) {
        LOG.info("Creating inventory for product {} with quantity {}", product.getId(), quantity);

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    /**
     * Update the quantity of a product.
     *
     * @param inventoryId the inventory ID
     * @param newQuantity  the new quantity
     * @return the updated inventory
     */
    public Inventory updateQuantity(Long inventoryId, Integer newQuantity) {
        Inventory inventory = inventoryRepository
                .findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));
        inventory.setQuantity(newQuantity);
        return inventoryRepository.save(inventory);
    }

    /**
     * Delete an inventory.
     *
     * @param inventoryId the inventory ID
     */
    public void deleteInventory(Long inventoryId) {
        inventoryRepository.deleteById(inventoryId);
    }
}
