package de.frankfurtuas.cloud.webshop.productmanagement.service;

import de.frankfurtuas.cloud.webshop.common.exception.ProductAlreadyExistsException;
import de.frankfurtuas.cloud.webshop.common.exception.ProductNotFoundException;
import de.frankfurtuas.cloud.webshop.inventorymanagement.service.InventoryService;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import de.frankfurtuas.cloud.webshop.productmanagement.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The product service.
 */
@Service
public class ProductService {

    /**
     * Logger instance.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final InventoryService inventoryService;

    /**
     * Constructor.
     *
     * @param productRepository the product repository
     * @param inventoryService the inventory service
     */
    public ProductService(ProductRepository productRepository, InventoryService inventoryService) {
        this.productRepository = productRepository;
        this.inventoryService = inventoryService;
    }

    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    public Product createProduct(Product product, Integer quantity) {
        LOG.info("Creating a new product: {}", product);

        // Check if the product already exists
        if (productRepository.existsByName(product.getName())) { // Assuming 'name' is a unique field
            LOG.warn("Product already exists: {}", product.getName());
            throw new ProductAlreadyExistsException("Product with this name already exists");
        }

        // Save the new product if it doesn't exist
        Product savedProduct = productRepository.save(product);

        // Create the inventory for the new product
        inventoryService.createInventory(savedProduct, quantity);

        return savedProduct;
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
    public List<Product> getProductsByName(String name) {
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
     * @param productId the product id
     * @param updatedProduct the updated product
     * @return the updated product if found, otherwise null
     */
    public void updateProduct(Long productId, Product updatedProduct) {
        productRepository
                .findById(productId)
                .map(existingProduct -> {
                    updatedProduct.setId(existingProduct.getId());
                    updatedProduct.setInventory(existingProduct.getInventory());
                    LOG.info("Updating product with id {}", productId);
                    return productRepository.save(updatedProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));
    }

    /**
     * Delete a product by its id.
     *
     * @param productId the product id
     */
    public void deleteProduct(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));

        inventoryService.deleteInventory(product.getInventory().getId());
        productRepository.delete(product);
        LOG.info("Deleted product with id {}", productId);
    }
}
