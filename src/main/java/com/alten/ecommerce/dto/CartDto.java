package com.alten.ecommerce.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartDto {

    private Long id;
    private String userEmail;
    private List<CartItemDto> items;
    private int totalQuantity;

}
