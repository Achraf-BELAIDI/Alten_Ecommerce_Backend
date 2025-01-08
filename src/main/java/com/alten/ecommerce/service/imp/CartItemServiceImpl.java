package com.alten.ecommerce.service.imp;

import com.alten.ecommerce.dto.CartItemDto;
import com.alten.ecommerce.mapper.CartItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.CartItem;
import com.alten.ecommerce.repository.CartItemRepository;
import com.alten.ecommerce.repository.CartRepository;
import com.alten.ecommerce.service.CartItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CartRepository cartRepository;

    @Override
    public List<CartItemDto> getItemsByCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cartItemMapper.toDto(cartItemRepository.findByCart(cart)) ;
    }

    @Override
    public void updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItemRepository.delete(cartItem);
    }
}
