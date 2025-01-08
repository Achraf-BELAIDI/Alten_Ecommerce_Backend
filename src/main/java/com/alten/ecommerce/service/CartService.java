package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.CartDto;
import com.alten.ecommerce.dto.CartItemDto;

public interface CartService {

    CartItemDto addProductToCart(String email, Long productId);

    void removeProductFromCart(String email, Long productId);

    CartDto getCart(String email);
}




