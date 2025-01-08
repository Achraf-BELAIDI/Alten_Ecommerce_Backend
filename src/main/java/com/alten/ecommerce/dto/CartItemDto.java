package com.alten.ecommerce.dto;

import lombok.Data;

@Data
public class CartItemDto {

    private Long productId;
    private String productName;
    private String productImage;
    private int quantity;

}
