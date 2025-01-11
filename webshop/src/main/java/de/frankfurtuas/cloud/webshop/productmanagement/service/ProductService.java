package de.frankfurtuas.cloud.webshop.productmanagement.service;

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

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        if (productRepository.existsById(id)) {
            updatedProduct.setId(id);
            return productRepository.save(updatedProduct);
        }
        return null; // Or throw an exception
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
