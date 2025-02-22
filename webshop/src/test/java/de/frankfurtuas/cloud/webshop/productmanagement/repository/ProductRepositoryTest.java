package de.frankfurtuas.cloud.webshop.productmanagement.repository;

import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testCreateAndFindByIdProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("High-end gaming laptop");
        product.setPrice(BigDecimal.valueOf(1500.0));
        product.setImageUrl("https://example.com/image.jpg");

        productRepository.save(product);

        // Act
        Optional<Product> findProductById = productRepository.findById(1L);

        // Assert
        assertThat(findProductById).isPresent();
        assertThat(findProductById.get().getId()).isEqualTo(1L);
        assertThat(findProductById.get().getName()).isEqualTo("Laptop");
        assertThat(findProductById.get().getDescription()).isEqualTo("High-end gaming laptop");
        assertThat(findProductById.get().getPrice()).isEqualTo(BigDecimal.valueOf(1500.0));
    }

    @Test
    void testCreateAndFindAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setDescription("High-end gaming laptop");
        product1.setPrice(BigDecimal.valueOf(1500.0));
        product1.setImageUrl("https://example.com/image.jpg");

        Product product2 = new Product();
        product2.setName("Xbox");
        product2.setDescription("With Fifa 25");
        product2.setPrice(BigDecimal.valueOf(500.0));
        product2.setImageUrl("https://example.com/image.jpg");

        productRepository.save(product1);
        productRepository.save(product2);

        // Act
        List<Product> products = productRepository.findAll();

        // Assert
        assertThat(products).hasSize(2);
        assertThat(products.get(0).getName()).isEqualTo("Laptop");
        assertThat(products.get(0).getDescription()).isEqualTo("High-end gaming laptop");
        assertThat(products.get(0).getPrice()).isEqualTo(BigDecimal.valueOf(1500.0));
        assertThat(products.get(1).getName()).isEqualTo("Xbox");
        assertThat(products.get(1).getDescription()).isEqualTo("With Fifa 25");
        assertThat(products.get(1).getPrice()).isEqualTo(BigDecimal.valueOf(500.0));
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("High-end gaming laptop");
        product.setPrice(BigDecimal.valueOf(1500.0));
        product.setImageUrl("https://example.com/image.jpg");

        // Act
        Product savedProduct = productRepository.save(product);
        savedProduct.setPrice(BigDecimal.valueOf(500.0));
        List<Product> products = productRepository.findAll();

        // Assert
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getPrice()).isEqualTo(BigDecimal.valueOf(500.0));
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Keyboard");
        product.setDescription("Mechanical keyboard");
        product.setPrice(BigDecimal.valueOf(120.0));
        product.setImageUrl("https://example.com/image.jpg");

        Product savedProduct = productRepository.save(product);

        // Act
        productRepository.delete(savedProduct);
        List<Product> remainingProducts = productRepository.findAll();

        // Assert
        assertThat(remainingProducts).isEmpty();
    }
}