package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.ProductDto;
import com.alten.ecommerce.mapper.ProductMapper;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Description");
        product.setCode("CODE123");
        product.setQuantity(10);
        product.setPrice(100.0);
        product.setCategory("Category");

        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Test Product");
        productDto.setDescription("Description");
        productDto.setCode("CODE123");
        productDto.setQuantity(10);
        productDto.setPrice(100.0);
        productDto.setCategory("Category");
    }

    @Test
    void createProduct_shouldReturnProductDto() {
        when(productMapper.toEntity(productDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDto);

        ProductDto result = productService.createProduct(productDto);

        assertNotNull(result);
        assertEquals(productDto.getId(), result.getId());
        assertEquals(productDto.getName(), result.getName());

        verify(productMapper).toEntity(productDto);
        verify(productRepository).save(product);
        verify(productMapper).toDto(product);
    }

    @Test
    void partialUpdate_shouldUpdateFields() {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Name");
        updatedProduct.setDescription("Updated Description");

        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setId(1L);
        updatedProductDto.setName("Updated Name");
        updatedProductDto.setDescription("Updated Description");

        when(productRepository.findById(productDto.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        when(productMapper.toDto(updatedProduct)).thenReturn(updatedProductDto);

        Optional<ProductDto> result = productService.partialUpdate(productDto);

        assertTrue(result.isPresent());
        assertEquals(updatedProductDto.getName(), result.get().getName());
        assertEquals(updatedProductDto.getDescription(), result.get().getDescription());

        verify(productRepository).findById(productDto.getId());
        verify(productRepository).save(any(Product.class));
        verify(productMapper).toDto(updatedProduct);
    }

    @Test
    void getAllProducts_shouldReturnListOfProductDtos() {
        List<Product> products = List.of(product);
        List<ProductDto> productDtos = List.of(productDto);

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.toDto(products)).thenReturn(productDtos);

        List<ProductDto> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(productDto.getId(), result.get(0).getId());

        verify(productRepository).findAll();
        verify(productMapper).toDto(products);
    }

    @Test
    void getProductById_shouldReturnProductDto() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productMapper.toDto(product)).thenReturn(productDto);

        Optional<ProductDto> result = productService.getProductById(product.getId());

        assertTrue(result.isPresent());
        assertEquals(productDto.getId(), result.get().getId());

        verify(productRepository).findById(product.getId());
        verify(productMapper).toDto(product);
    }

    @Test
    void updateProduct_shouldUpdateAndReturnProductDto() {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Name");

        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setId(1L);
        updatedProductDto.setName("Updated Name");

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);
        when(productMapper.toDto(updatedProduct)).thenReturn(updatedProductDto);

        ProductDto result = productService.updateProduct(product.getId(), updatedProductDto);

        assertNotNull(result);
        assertEquals(updatedProductDto.getId(), result.getId());
        assertEquals(updatedProductDto.getName(), result.getName());

        verify(productRepository).findById(product.getId());
        verify(productRepository).save(any(Product.class));
        verify(productMapper).toDto(updatedProduct);
    }

    @Test
    void deleteProduct_shouldDeleteProduct() {
        doNothing().when(productRepository).deleteById(product.getId());

        productService.deleteProduct(product.getId());

        verify(productRepository).deleteById(product.getId());
    }
}
