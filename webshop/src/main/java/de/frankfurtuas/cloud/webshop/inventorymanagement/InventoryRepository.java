package de.frankfurtuas.cloud.webshop.inventorymanagement.repository;

import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Product, Long> {
}
