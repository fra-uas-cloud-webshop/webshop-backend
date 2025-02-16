package de.frankfurtuas.cloud.webshop.productmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.frankfurtuas.cloud.webshop.productmanagement.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(locations = "classpath:application-test.properties")
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProducts() throws Exception {
        //        // Arrange
        //        Product product1 = new Product();
        //        product1.setName("Laptop");
        //        product1.setDescription("Gaming Laptop");
        //        product1.setPrice(BigDecimal.valueOf(1500.0));
        //
        //        Product product2 = new Product();
        //        product2.setName("Monitor");
        //        product2.setDescription("4K Display");
        //        product2.setPrice(BigDecimal.valueOf(300.0));
        //
        //        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));
        //
        //        // Act & Assert
        //        mockMvc.perform(get("/products"))
        //                .andExpect(status().isOk())
        //                .andExpect(jsonPath("$", hasSize(2)))
        //                .andExpect((ResultMatcher) jsonPath("$[0].name", is("Laptop")))
        //                .andExpect((ResultMatcher) jsonPath("$[1].name", is("Monitor")));
        //
        //        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() throws Exception {
        //        // Arrange
        //        Product product = new Product();
        //        product.setName("Phone");
        //        product.setDescription("Smartphone");
        //        product.setPrice(BigDecimal.valueOf(999.0));
        //
        //        when(productService.getProductById(1L)).thenReturn(Optional.of(product));
        //
        //        // Act & Assert
        //        mockMvc.perform(get("/products/1"))
        //                .andExpect(status().isOk())
        //                .andExpect((ResultMatcher) jsonPath("$.name", is("Phone")))
        //                .andExpect((ResultMatcher) jsonPath("$.price", is(999.0)));
        //
        //        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testGetProductById_NotFound() throws Exception {
        //        // Arrange
        //        when(productService.getProductById(1L)).thenReturn(Optional.empty());
        //
        //        // Act & Assert
        //        mockMvc.perform(get("/products/1")).andExpect(status().isNotFound());
        //
        //        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testCreateProduct() throws Exception {
        //        // Arrange
        //        Product product = new Product();
        //        product.setName("Tablet");
        //        product.setDescription("Portable device");
        //        product.setPrice(BigDecimal.valueOf(500.0));
        //
        //        Product savedProduct = new Product();
        //        savedProduct.setName("Tablet");
        //        savedProduct.setDescription("Portable device");
        //        savedProduct.setPrice(BigDecimal.valueOf(400.0));
        //
        //        when(productService.createProduct(any(Product.class))).thenReturn(savedProduct);
        //
        //        // Act & Assert
        //        mockMvc.perform(post("/products")
        //                        .contentType(MediaType.APPLICATION_JSON)
        //                        .content(objectMapper.writeValueAsString(product)))
        //                .andExpect(status().isCreated())
        //                .andExpect((ResultMatcher) jsonPath("$.id", is(1)))
        //                .andExpect((ResultMatcher) jsonPath("$.name", is("Tablet")))
        //                .andExpect((ResultMatcher) jsonPath("$.price", is(400.0)));
        //
        //        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    void testUpdateProduct() throws Exception {
        //        // Arrange
        //        Product product = new Product();
        //        product.setName("Laptop");
        //        product.setDescription("Portable device");
        //        product.setPrice(BigDecimal.valueOf(2000.0));
        //
        //        Product updatedProduct = new Product();
        //        updatedProduct.setName("Updated Laptop");
        //        updatedProduct.setDescription("Updated description");
        //        updatedProduct.setPrice(BigDecimal.valueOf(2000.0));
        //
        //        // Act & Assert
        //        mockMvc.perform(put("/products/1")
        //                        .contentType(MediaType.APPLICATION_JSON)
        //                        .content(objectMapper.writeValueAsString(updatedProduct)))
        //                .andExpect(status().isOk())
        //                .andExpect((ResultMatcher) jsonPath("$.id", is(1)))
        //                .andExpect((ResultMatcher) jsonPath("$.name", is("Updated Laptop")))
        //                .andExpect((ResultMatcher) jsonPath("$.price", is(2000.0)));
        //
        //        verify(productService, times(1)).updateProduct(eq(1L), any(Product.class));
    }

    @Test
    void testUpdateProduct_NotFound() throws Exception {
        //        // Arrange
        //        Product updatedProduct = new Product();
        //        updatedProduct.setName("Updated Laptop");
        //        updatedProduct.setDescription("Updated description");
        //        updatedProduct.setPrice(BigDecimal.valueOf(2000.0));
        //
        //        when(productService.updateProduct(eq(1L), any(Product.class)))
        //                .thenThrow(new ProductNotFoundException("Product with ID 1 not found"));
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
        //        // Arrange
        //        doNothing().when(productService).deleteProduct(1L);
        //
        //        // Act & Assert
        //        mockMvc.perform(delete("/products/1")).andExpect(status().isNoContent());
        //
        //        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void testDeleteProduct_NotFound() throws Exception {
        //        // Arrange
        //        doThrow(new ProductNotFoundException("Product with ID 1 not found"))
        //                .when(productService)
        //                .deleteProduct(1L);
        //
        //        // Act & Assert
        //        mockMvc.perform(delete("/products/1")).andExpect(status().isNotFound());
        //
        //        verify(productService, times(1)).deleteProduct(1L);
    }
}
