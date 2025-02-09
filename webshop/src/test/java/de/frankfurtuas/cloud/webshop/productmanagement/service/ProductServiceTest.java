package de.frankfurtuas.cloud.webshop.productmanagement.service;

import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import de.frankfurtuas.cloud.webshop.productmanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice(1500.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product createdProduct = productService.createProduct(product);

        // Assert
        assertNotNull(createdProduct);
        assertThat(createdProduct).isNotNull();
        assertThat(createdProduct.getName()).isEqualTo("Laptop");
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");
        product.setDescription("Smartphone");
        product.setPrice(999.0);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> foundProduct = productService.getProductById(1L);

        // Assert
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo("Phone");
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Tablet");
        product1.setDescription("Portable device");
        product1.setPrice(499.0);

        Product product2 = new Product();
        product2.setName("Monitor");
        product2.setDescription("4K Display");
        product2.setPrice(300.0);

        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> foundProducts = productService.getAllProducts();

        // Assert
        assertThat(foundProducts).hasSize(2);
        assertThat(foundProducts.get(0).getName()).isEqualTo("Tablet");
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        // still
    }

    @Test
    void testDeleteProduct() {
        // still
    }
}