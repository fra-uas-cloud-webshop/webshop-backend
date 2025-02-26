package de.frankfurtuas.cloud.webshop.productmanagement.service;

import de.frankfurtuas.cloud.webshop.common.exception.ProductNotFoundException;
import de.frankfurtuas.cloud.webshop.inventorymanagement.model.Inventory;
import de.frankfurtuas.cloud.webshop.productmanagement.dto.ProductDTO;
import de.frankfurtuas.cloud.webshop.productmanagement.model.Product;
import de.frankfurtuas.cloud.webshop.productmanagement.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestPropertySource(locations = "classpath:application-test.properties")
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
        product.setPrice(BigDecimal.valueOf(1500.0));
        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(1);
        product.setInventory(inventory);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        //        ProductDTO createdProduct = productService.createProduct(product, 1);

        // Assert
        //        assertNotNull(createdProduct);
        //        assertThat(createdProduct).isNotNull();
        //        assertThat(createdProduct.getName()).isEqualTo("Laptop");
        //        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");
        product.setDescription("Smartphone");
        product.setPrice(BigDecimal.valueOf(999.0));
        Inventory inventory = new Inventory(1L, product, 1);
        product.setInventory(inventory);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        ProductDTO foundProduct = productService.getProductById(1L);

        // Assert
        assertThat(foundProduct.getName()).isEqualTo("Phone");
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductByName() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");
        product.setDescription("Smartphone");
        product.setPrice(BigDecimal.valueOf(999.0));
        Inventory inventory = new Inventory(1L, product, 1);
        product.setInventory(inventory);

        when(productRepository.findByName("Phone")).thenReturn(product);

        // Act
        ProductDTO foundProduct = productService.getProductByName("Phone");

        // Assert
        assertNotNull(foundProduct);
        assertThat(foundProduct.getName()).isEqualTo("Phone");
        verify(productRepository, times(1)).findByName("Phone");
    }

    @Test
    void testGetProductByCategory() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Phone");
        product.setDescription("Smartphone");
        product.setCategory("Electronics");
        product.setPrice(BigDecimal.valueOf(999.0));
        Inventory inventory = new Inventory(1L, product, 1);
        product.setInventory(inventory);

        when(productRepository.findByCategory("Electronics")).thenReturn(List.of(product));

        // Act
        List<ProductDTO> foundProduct = productService.getProductsByCategory("Electronics");

        // Assert
        assertNotNull(foundProduct);
        assertThat(foundProduct).hasSize(1);
        assertThat(foundProduct.get(0).getName()).isEqualTo("Phone");
        assertThat(foundProduct.get(0).getCategory()).isEqualTo("Electronics");
        verify(productRepository, times(1)).findByCategory("Electronics");
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        Product product1 = new Product();
        product1.setName("Tablet");
        product1.setDescription("Portable device");
        product1.setPrice(BigDecimal.valueOf(499.0));
        Inventory inventory1 = new Inventory(1L, product1, 1);
        product1.setInventory(inventory1);

        Product product2 = new Product();
        product2.setName("Monitor");
        product2.setDescription("4K Display");
        product2.setPrice(BigDecimal.valueOf(300.0));
        Inventory inventory2 = new Inventory(1L, product2, 1);
        product2.setInventory(inventory2);

        List<Product> products = Arrays.asList(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<ProductDTO> foundProducts = productService.getAllProducts();

        // Assert
        assertThat(foundProducts).hasSize(2);
        assertThat(foundProducts.get(0).getName()).isEqualTo("Tablet");
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setDescription("Gaming Laptop");
        product.setPrice(BigDecimal.valueOf(1500.0));

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Laptop");
        updatedProduct.setDescription("Gaming Laptop");
        updatedProduct.setPrice(BigDecimal.valueOf(500.0));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        // Act
        productService.updateProduct(1L, updatedProduct);

        // Assert
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo("Laptop");
        assertThat(product.getDescription()).isEqualTo("Gaming Laptop");
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    void testDeleteProduct_ThrowsProductNotFoundException() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteProduct(1L);
        });

        // Assert
        assertEquals("Product with id 1 not found", exception.getMessage());
    }
}