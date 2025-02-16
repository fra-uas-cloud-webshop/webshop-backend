package de.frankfurtuas.cloud.webshop.productmanagement.service;

import de.frankfurtuas.cloud.webshop.productmanagement.exception.ProductNotFoundException;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import de.frankfurtuas.cloud.webshop.productmanagement.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    /**
     * Logger instance.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    /**
     * Constructor.
     *
     * @param productRepository the product repository
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    public Product createProduct(Product product) {
        LOG.info("Creating a new product: {}", product);
        return productRepository.save(product);
    }

    /**
     * Get all products.
     *
     * @return the list of products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Get a product by its id.
     *
     * @param id the product id
     * @return the product if found, otherwise empty
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Get a product by its name.
     *
     * @param name the product name
     * @return the product if found, otherwise empty
     */
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByName(name);
    }

    /**
     * Get products by category.
     *
     * @param category the product category
     * @return the list of products
     */
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    /**
     * Update a product.
     *
     * @param id the product id
     * @param updatedProduct the updated product
     * @return the updated product if found, otherwise null
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository
                .findById(id)
                .map(existingProduct -> {
                    updatedProduct.setId(existingProduct.getId());
                    return productRepository.save(updatedProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    /**
     * Delete a product by its id.
     *
     * @param id the product id
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            LOG.warn("Attempt to delete a non-existent product with id {}", id);
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }
        LOG.info("Deleting product with id {}", id);
        productRepository.deleteById(id);
    }
}
