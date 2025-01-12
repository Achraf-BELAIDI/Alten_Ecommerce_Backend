package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dto.CartDto;
import com.alten.ecommerce.dto.CartItemDto;
import com.alten.ecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alten.ecommerce.service.CartService;


@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addProductToCart(@RequestBody ProductDto productDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CartItemDto cartItemDto = cartService.addProductToCart(email, productDto.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long productId) {
        System.out.println("ddddddddddddddd");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.removeProductFromCart(email, productId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/view")
    public ResponseEntity<?> getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CartDto cartDto = cartService.getCart(email);
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/quantity")
    public ResponseEntity<Integer> getCartQuantity() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CartDto cartDto = cartService.getCart(email);
        Integer cartQuantity = cartDto.getItems().stream().mapToInt(CartItemDto::getQuantity).sum();
        return ResponseEntity.status(HttpStatus.OK).body(cartQuantity);
    }
}

