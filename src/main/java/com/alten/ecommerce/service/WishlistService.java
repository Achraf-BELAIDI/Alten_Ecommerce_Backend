package com.alten.ecommerce.service;


import com.alten.ecommerce.dto.WishlistDto;


public interface WishlistService {
  

    public WishlistDto addOrRemoveProductToWishlist(String email, Long productId);

    public WishlistDto getWishlist(String email);
}
