package com.alten.ecommerce.controllers;


import com.alten.ecommerce.dto.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alten.ecommerce.model.CartItem;
import com.alten.ecommerce.service.CartItemService;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class CartItemController {


    private final CartItemService cartItemService;

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemDto>> getItemsByCart(@PathVariable Long cartId) {
        List<CartItemDto> items = cartItemService.getItemsByCart(cartId);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<String> updateCartItemQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {
        cartItemService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok("Cart item quantity updated successfully");
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.ok("Cart item deleted successfully");
    }
}
