package de.frankfurtuas.cloud.webshop.inventorymanagement.service;

import de.frankfurtuas.cloud.webshop.common.exception.InventoryNotFoundException;
import de.frankfurtuas.cloud.webshop.inventorymanagement.dto.InventoryDTO;
import de.frankfurtuas.cloud.webshop.inventorymanagement.mapper.InventoryMapper;
import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;
import de.frankfurtuas.cloud.webshop.inventorymanagement.repository.InventoryRepository;
import de.frankfurtuas.cloud.webshop.mailmanagement.EmailNotificationService;
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

    private final EmailNotificationService emailNotificationService;

    /**
     * Constructor.
     *
     * @param inventoryRepository the inventory repository
     * @param emailNotificationService the email notification service
     */
    public InventoryService(
            InventoryRepository inventoryRepository, EmailNotificationService emailNotificationService) {
        this.inventoryRepository = inventoryRepository;
        this.emailNotificationService = emailNotificationService;
    }

    /**
     * Get all inventories.
     *
     * @return the list of inventories
     */
    public List<InventoryDTO> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return InventoryMapper.toInventoryDTOList(inventories);
    }

    /**
     * Create a new inventory.
     *
     * @param product  the product
     * @param quantity the quantity
     * @return the created inventory
     */
    public Inventory createInventory(Product product, Integer quantity) {
        LOG.info("Creating inventory for product {} with quantity {}", product.getId(), quantity);

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);

        return inventory;
    }

    /**
     * Update the quantity of a product.
     *
     * @param inventoryId the inventory ID
     * @param newQuantity  the new quantity
     * @return the updated inventory
     */
    public InventoryDTO updateQuantity(Long inventoryId, Integer newQuantity) {
        Inventory inventory = inventoryRepository
                .findById(inventoryId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found"));
        inventory.setQuantity(newQuantity);
        inventoryRepository.save(inventory);

        return InventoryMapper.toInventoryDTO(inventory);
    }

    /**
     * Update the quantity of a product by product ID.
     *
     * @param productId   the product ID
     * @param newQuantity the new quantity
     */
    public void updateQuantityByProductId(Long productId, Integer newQuantity) {
        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException("Inventory not found for product ID: " + productId));

        // Check if the new quantity is lower than the current quantity
        if (newQuantity < inventory.getQuantity()) {
            throw new IllegalArgumentException("New quantity cannot be lower than the existing quantity.");
        }

        inventory.setQuantity(newQuantity);
        inventoryRepository.save(inventory);

        // If quantity is low (e.g., less than 2), send a low stock alert email
        if (newQuantity < 2) {
            emailNotificationService.sendLowStockAlert(inventory.getProduct(), newQuantity);
        }
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
