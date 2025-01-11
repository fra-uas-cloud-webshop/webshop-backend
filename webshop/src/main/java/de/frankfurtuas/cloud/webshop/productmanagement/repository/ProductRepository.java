package de.frankfurtuas.cloud.webshop.productmanagement.repository;

import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
