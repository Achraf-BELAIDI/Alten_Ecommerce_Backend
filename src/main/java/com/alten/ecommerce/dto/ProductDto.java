package com.alten.ecommerce.dto;

import com.alten.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

public class ProductDto {

    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private Product.InventoryStatus inventoryStatus;
    private double rating;
}
