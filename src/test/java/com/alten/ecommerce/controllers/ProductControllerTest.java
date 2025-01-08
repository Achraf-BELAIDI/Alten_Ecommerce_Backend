package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dto.ProductDto;
import com.alten.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private ProductController productController;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");
        productDto.setDescription("Description");
        productDto.setCode("CODE123");
        productDto.setQuantity(10);
        productDto.setPrice(100.0);
        productDto.setCategory("Category");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void createProduct_shouldReturnOkResponseWhenAdmin() {
        when(authentication.getName()).thenReturn("admin@admin.com");
        when(productService.createProduct(productDto)).thenReturn(productDto);

        ResponseEntity<?> response = productController.createProduct(productDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productDto, response.getBody());

        verify(productService).createProduct(productDto);
    }

    @Test
    void createProduct_shouldReturnForbiddenWhenNotAdmin() {
        when(authentication.getName()).thenReturn("user@user.com");

        ResponseEntity<?> response = productController.createProduct(productDto);

        assertEquals(403, response.getStatusCodeValue());
        assertEquals("Access denied: You do not have permission to add products.", response.getBody());

        verify(productService, never()).createProduct(any());
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        List<ProductDto> productList = List.of(productDto);
        when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productList, response.getBody());

        verify(productService).getAllProducts();
    }

    @Test
    void getProductById_shouldReturnProductWhenFound() {
        when(productService.getProductById(1L)).thenReturn(Optional.of(productDto));

        ResponseEntity<ProductDto> response = productController.getProductById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productDto, response.getBody());

        verify(productService).getProductById(1L);
    }

    @Test
    void getProductById_shouldReturnNotFoundWhenNotFound() {
        when(productService.getProductById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ProductDto> response = productController.getProductById(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(productService).getProductById(1L);
    }

    @Test
    void updateProduct_shouldReturnOkResponseWhenAdmin() {
        when(authentication.getName()).thenReturn("admin@admin.com");
        when(productService.updateProduct(1L, productDto)).thenReturn(productDto);

        ResponseEntity<?> response = productController.updateProduct(1L, productDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productDto, response.getBody());

        verify(productService).updateProduct(1L, productDto);
    }

    @Test
    void updateProduct_shouldReturnForbiddenWhenNotAdmin() {
        when(authentication.getName()).thenReturn("user@user.com");

        ResponseEntity<?> response = productController.updateProduct(1L, productDto);

        assertEquals(403, response.getStatusCodeValue());
        assertEquals("Access denied: You do not have permission to add products.", response.getBody());

        verify(productService, never()).updateProduct(anyLong(), any());
    }

    @Test
    void partialUpdateProduct_shouldReturnOkResponseWhenProductExists() {
        when(productService.partialUpdate(productDto)).thenReturn(Optional.of(productDto));

        ResponseEntity<?> response = productController.partialUpdateProduct(1L, productDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(productDto, response.getBody());

        verify(productService).partialUpdate(productDto);
    }

    @Test
    void partialUpdateProduct_shouldReturnNotFoundWhenProductDoesNotExist() {
        when(productService.partialUpdate(productDto)).thenReturn(Optional.empty());

        ResponseEntity<?> response = productController.partialUpdateProduct(1L, productDto);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Produit non trouvé.", response.getBody());

        verify(productService).partialUpdate(productDto);
    }

    @Test
    void deleteProduct_shouldReturnNoContentWhenAdmin() {
        when(authentication.getName()).thenReturn("admin@admin.com");
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<?> response = productController.deleteProduct(1L);

        assertEquals(204, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(productService).deleteProduct(1L);
    }

    @Test
    void deleteProduct_shouldReturnForbiddenWhenNotAdmin() {
        when(authentication.getName()).thenReturn("user@user.com");

        ResponseEntity<?> response = productController.deleteProduct(1L);

        assertEquals(403, response.getStatusCodeValue());
        assertEquals("Access denied: You do not have permission to add products.", response.getBody());

        verify(productService, never()).deleteProduct(anyLong());
    }
}
