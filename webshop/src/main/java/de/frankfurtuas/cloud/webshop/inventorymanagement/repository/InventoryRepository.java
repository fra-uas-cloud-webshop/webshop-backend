package de.frankfurtuas.cloud.webshop.inventorymanagement.repository;

import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The inventory repository.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
