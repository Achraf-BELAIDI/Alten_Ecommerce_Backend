package com.alten.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CartItemDto {

    private Long productId;
    private String productName;
    private String productImage;
    private int quantity;

}
