package de.frankfurtuas.cloud.webshop.productmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.frankfurtuas.cloud.webshop.productmanagement.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(locations = "classpath:application-test.properties")
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProducts() throws Exception {
        //        // Arrange
        //        Product product1 = new Product(1L, "Laptop", "Gaming Laptop", 1500.0);
        //        Product product2 = new Product(2L, "Monitor", "4K Display", 300.0);
        //
        //        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));
        //
        //        // Act & Assert
        //        mockMvc.perform(get("/products"))
        //                .andExpect(status().isOk())
        //                .andExpect(jsonPath("$", hasSize(2)))
        //                .andExpect(jsonPath("$[0].name", is("Laptop")))
        //                .andExpect(jsonPath("$[1].name", is("Monitor")));
        //
        //        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() throws Exception {
        //        // Arrange
        //        Product product = new Product(1L, "Phone", "Smartphone", 999.0);
        //        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        //
        //        // Act & Assert
        //        mockMvc.perform(get("/products/1"))
        //                .andExpect(status().isOk())
        //                .andExpect(jsonPath("$.name", is("Phone")))
        //                .andExpect(jsonPath("$.price", is(999.0)));
        //
        //        verify(productService, times(1)).getProductById(1L);
        //    }
        //
        //    @Test
        //    void testGetProductById_NotFound() throws Exception {
        //        // Arrange
        //        when(productService.getProductById(1L)).thenReturn(Optional.empty());
        //
        //        // Act & Assert
        //        mockMvc.perform(get("/products/1"))
        //                .andExpect(status().isNotFound());
        //
        //        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testCreateProduct() throws Exception {
        //        // Arrange
        //        Product product = new Product(1L, "Tablet", "Portable device", 500.0);
        //        Product savedProduct = new Product(1L, "Tablet", "Portable device", 400.0);
        //
        //        when(productService.createProduct(any(Product.class))).thenReturn(savedProduct);
        //
        //        // Act & Assert
        //        mockMvc.perform(post("/products")
        //                        .contentType(MediaType.APPLICATION_JSON)
        //                        .content(objectMapper.writeValueAsString(product)))
        //                .andExpect(status().isCreated())
        //                .andExpect(jsonPath("$.id", is(1)))
        //                .andExpect(jsonPath("$.name", is("Tablet")))
        //                .andExpect(jsonPath("$.price", is(400.0)));
        //
        //        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() throws Exception {
        //        // Arrange
        //        Product updatedProduct = new Product(null, "Updated Laptop", "Updated description", 2000.0);
        //        Product savedProduct = new Product(1L, "Updated Laptop", "Updated description", 2000.0);
        //
        //        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(savedProduct);
        //
        //        // Act & Assert
        //        mockMvc.perform(put("/products/1")
        //                        .contentType(MediaType.APPLICATION_JSON)
        //                        .content(objectMapper.writeValueAsString(updatedProduct)))
        //                .andExpect(status().isOk())
        //                .andExpect(jsonPath("$.id", is(1)))
        //                .andExpect(jsonPath("$.name", is("Updated Laptop")))
        //                .andExpect(jsonPath("$.price", is(2000.0)));
        //
        //        verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
    }

    @Test
    void testUpdateProduct_NotFound() throws Exception {
        //        // Arrange
        //        Product updatedProduct = new Product(null, "Updated Laptop", "Updated description", 2000.0);
        //
        //        when(productService.updateProduct(eq(1L), any(Product.class)))
        //                .thenThrow(new EntityNotFoundException("Product with ID 1 not found"));
        //
        //        // Act & Assert
        //        mockMvc.perform(put("/products/1")
        //                        .contentType(MediaType.APPLICATION_JSON)
        //                        .content(objectMapper.writeValueAsString(updatedProduct)))
        //                .andExpect(status().isNotFound());
        //
        //        verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        //
    }

    @Test
    void testDeleteProduct_NotFound() throws Exception {
        //
    }
}