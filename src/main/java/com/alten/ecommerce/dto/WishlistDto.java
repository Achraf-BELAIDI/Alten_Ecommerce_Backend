package com.alten.ecommerce.dto;

import lombok.Data;
import java.util.List;

@Data
public class WishlistDto {

    private Long id;
    private String userEmail;
    private List<ProductDto> products;
}
