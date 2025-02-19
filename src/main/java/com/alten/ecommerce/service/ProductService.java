package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.ProductDto;
import com.alten.ecommerce.mapper.ProductMapper;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        return productMapper.toDto(productRepository.save(product)) ;
    }

    public Optional<ProductDto> partialUpdate(ProductDto product) {
        return productRepository.findById(product.getId()).map(
                existingProduct -> {
                    if(product.getName() != null){
                        existingProduct.setName(product.getName());
                    }
                    if(product.getDescription() != null){
                        existingProduct.setDescription(product.getDescription());
                    }
                    if (product.getCode() != null){
                        existingProduct.setCode(product.getCode());
                    }
                    if(String.valueOf(product.getQuantity()) != null){
                        existingProduct.setQuantity(product.getQuantity());
                    }
                    if(String.valueOf(product.getPrice()) != null){
                        existingProduct.setPrice(product.getPrice());
                    }
                    if(product.getCategory() != null){
                        existingProduct.setCategory(product.getCategory());
                    }
                    return productMapper.toDto(productRepository.save(existingProduct));
                }
        );
    }
    public List<ProductDto> getAllProducts() {
        return productMapper.toDto(productRepository.findAll()) ;
    }

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id).map(productMapper::toDto);
    }

    public ProductDto updateProduct(Long id, ProductDto updatedProduct) {
        return productMapper.toDto( productRepository.findById(id).map(product -> {
            product.setId(id);
            product.setCode(updatedProduct.getCode());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setImage(updatedProduct.getImage());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setQuantity(updatedProduct.getQuantity());
            product.setInventoryStatus(updatedProduct.getInventoryStatus());
            product.setRating(updatedProduct.getRating());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found")));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
