package de.frankfurtuas.cloud.webshop.productmanagement.repository;

import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The product repository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find products by category.
     *
     * @param category the category
     * @return the list of products
     */
    List<Product> findByCategory(String category);

    /**
     * Find products by name containing the given name.
     *
     * @param name the name
     * @return the list of products
     */
    List<Product> findByName(String name);

    /**
     * Check if a product with the given name exists.
     *
     * @param name the name
     * @return true if a product with the given name exists, false otherwise
     */
    boolean existsByName(String name);
}
