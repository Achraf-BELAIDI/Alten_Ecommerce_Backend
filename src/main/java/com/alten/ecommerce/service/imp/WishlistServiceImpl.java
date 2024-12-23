package com.alten.ecommerce.service.imp;

import com.alten.ecommerce.dto.WishlistDto;
import com.alten.ecommerce.mapper.WishlistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.UserEntity;
import com.alten.ecommerce.model.Wishlist;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.repository.WishlistRepository;
import com.alten.ecommerce.service.WishlistService;

@Service
@Transactional
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishlistMapper wishlistMapper;

    @Override
    public WishlistDto addOrRemoveProductToWishlist(String email, Long productId) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product with ID " + productId + " not found"));

        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseGet(() -> new Wishlist(user));

        if (wishlist.getProducts().contains(product)) {
            wishlist.removeItem(productId);
        } else {
            wishlist.addItem(product);
        }

        Wishlist savedWishlist = wishlistRepository.save(wishlist);

        return wishlistMapper.toDto(savedWishlist);
    }

    @Override
    public WishlistDto getWishlist(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));

        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseGet(() -> new Wishlist(user));

        return wishlistMapper.toDto(wishlist);
    }
}

