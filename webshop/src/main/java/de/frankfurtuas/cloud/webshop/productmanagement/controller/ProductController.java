package de.frankfurtuas.cloud.webshop.productmanagement.controller;

import de.frankfurtuas.cloud.webshop.common.controller.BaseController;
import de.frankfurtuas.cloud.webshop.productmanagement.dto.ProductDTO;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import de.frankfurtuas.cloud.webshop.productmanagement.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The product controller.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController {

    private final ProductService productService;

    /**
     * Constructor.
     *
     * @param productService the product service
     */
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create a new product.
     *
     * @param product the product to create
     * @return the created product
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product, @RequestParam Integer quantity) {
        ProductDTO createdProduct = productService.createProduct(product, quantity);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Get all products.
     *
     * @return the list of products
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Get a product by its id.
     *
     * @param id the product id
     * @return the product if found, otherwise not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.of(Optional.ofNullable(product));
    }

    /**
     * Get products by name.
     *
     * @param name the product name
     * @return the list of products
     */
    @GetMapping("/by-name")
    public ResponseEntity<ProductDTO> searchProductsByName(@RequestParam String name) {
        ProductDTO products = productService.getProductByName(name);
        return ResponseEntity.ok(products);
    }

    /**
     * Get products by category.
     *
     * @param category the product category
     * @return the list of products
     */
    @GetMapping("/by-category")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam String category) {
        List<ProductDTO> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    /**
     * Update a product by its id.
     *
     * @param id      the product id
     * @param product the product to update
     * @return the updated product if found, otherwise not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a product by its id.
     *
     * @param id the product id
     * @return no content if deleted, otherwise not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
