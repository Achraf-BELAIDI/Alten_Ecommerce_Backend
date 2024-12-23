package com.alten.ecommerce.controllers;

import com.alten.ecommerce.dto.ProductDto;
import com.alten.ecommerce.dto.WishlistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.Wishlist;
import com.alten.ecommerce.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;


    @PostMapping("/addOrRemove")
    public ResponseEntity<WishlistDto> addProductToWishlist(@RequestBody ProductDto productDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        WishlistDto result = wishlistService.addOrRemoveProductToWishlist(email, productDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/view")
    public ResponseEntity<?> getWishlist() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        WishlistDto wishlistDto = wishlistService.getWishlist(email);
        return ResponseEntity.ok(wishlistDto);
    }
}

