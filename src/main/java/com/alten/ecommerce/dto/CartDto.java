package com.alten.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class CartDto {

    private Long id;
    private String userEmail;
    private List<CartItemDto> items;
    private int totalQuantity;

}
