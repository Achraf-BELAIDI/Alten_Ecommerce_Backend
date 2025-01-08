package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dto.ProductDto;
import com.alten.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){
        System.out.println(productDto);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!"admin@admin.com".equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not have permission to add products.");
        } 
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProductDto updatedProduct) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!"admin@admin.com".equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not have permission to add products.");
        }
        return ResponseEntity.ok(productService.updateProduct(id, updatedProduct));
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<?> partialUpdateProduct(@PathVariable Long id, @RequestBody ProductDto product) {
        Optional<ProductDto> result =  productService.partialUpdate(product);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produit non trouvé.");
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!"admin@admin.com".equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: You do not have permission to add products.");
        }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
