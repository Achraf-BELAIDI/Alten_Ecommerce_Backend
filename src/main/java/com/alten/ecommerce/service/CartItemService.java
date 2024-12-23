package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.CartItemDto;

import java.util.List;

public interface CartItemService {

    List<CartItemDto> getItemsByCart(Long cartId);

    void updateCartItemQuantity(Long cartItemId, int quantity);

    void deleteCartItem(Long cartItemId);
}

