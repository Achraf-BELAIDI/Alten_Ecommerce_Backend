
package com.alten.ecommerce.service.imp;

import com.alten.ecommerce.dto.CartDto;
import com.alten.ecommerce.dto.CartItemDto;
import com.alten.ecommerce.mapper.CartItemMapper;
import com.alten.ecommerce.mapper.CartMapper;
import com.alten.ecommerce.model.CartItem;
import com.alten.ecommerce.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.UserEntity;
import com.alten.ecommerce.repository.CartRepository;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.service.CartService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;


    public CartItemDto addProductToCart(String email, Long productId) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        System.out.println("User: " + user.getId() + " - " + user.getEmail());

        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartUserEmail(product.getId(), email);
        if(optionalCartItem.isPresent()){
            CartItem cartItem = optionalCartItem.get();

            cartItem.setQuantity(cartItem.getQuantity() + 1);
           return cartItemMapper.toDto(cartItemRepository.save(cartItem));
        }
        else{

            Cart cart = cartRepository.findByUser(user)
                    .orElseGet(() -> {
                        Cart newCart = new Cart(user);
                        cartRepository.save(newCart);
                        return newCart;
                    });

            cart.addItem(product, 1);
            Cart result = cartRepository.save(cart);
            return cartItemMapper.toDto(result.getItems().get(0));
        }
    }

    @Override
    public void removeProductFromCart(String email, Long productId) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    @Override
    public CartDto getCart(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartMapper.toDto(cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart(user);
                    return cartRepository.save(newCart);
                }));
    }
}

