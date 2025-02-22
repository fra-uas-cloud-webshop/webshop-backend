package de.frankfurtuas.cloud.webshop.inventorymanagement.repository;

import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The inventory repository.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Find an inventory by product ID.
     *
     * @param productId the product ID
     * @return the inventory
     */
    Optional<Inventory> findByProductId(Long productId);
}
