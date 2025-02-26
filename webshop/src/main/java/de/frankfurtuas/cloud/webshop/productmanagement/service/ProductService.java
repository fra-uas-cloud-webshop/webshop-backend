package de.frankfurtuas.cloud.webshop.productmanagement.service;

import de.frankfurtuas.cloud.webshop.common.exception.ProductAlreadyExistsException;
import de.frankfurtuas.cloud.webshop.common.exception.ProductNotFoundException;
import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;
import de.frankfurtuas.cloud.webshop.inventorymanagement.service.InventoryService;
import de.frankfurtuas.cloud.webshop.productmanagement.dto.ProductDTO;
import de.frankfurtuas.cloud.webshop.productmanagement.mapper.ProductMapper;
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
    public ProductDTO createProduct(Product product, Integer quantity) {
        LOG.info("Creating a new product: {}", product);

        // Check if the product already exists
        if (productRepository.existsByName(product.getName())) { // Assuming 'name' is a unique field
            LOG.warn("Product already exists: {}", product.getName());
            throw new ProductAlreadyExistsException("Product with this name already exists");
        }

        // Save the new product if it doesn't exist
        Product savedProduct = productRepository.save(product);

        // Create the inventory for the new product
        Inventory inventory = inventoryService.createInventory(savedProduct, quantity);
        savedProduct.setInventory(inventory);

        return ProductMapper.toProductDTO(savedProduct);
    }

    /**
     * Get all products.
     *
     * @return the list of products
     */
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.toProductDTOList(products);
    }

    /**
     * Get a product by its id.
     *
     * @param id the product id
     * @return the product if found, otherwise empty
     */
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return ProductMapper.toProductDTO(product.get());
    }

    /**
     * Get a product by its name.
     *
     * @param name the product name
     * @return the product if found, otherwise empty
     */
    public ProductDTO getProductByName(String name) {
        Product products = productRepository.findByName(name);
        return ProductMapper.toProductDTO(products);
    }

    /**
     * Get products by category.
     *
     * @param category the product category
     * @return the list of products
     */
    public List<ProductDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return ProductMapper.toProductDTOList(products);
    }

    /**
     * Update a product.
     *
     * @param productId the product id
     * @param updatedProduct the updated product
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
