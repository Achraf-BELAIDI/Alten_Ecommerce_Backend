package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dto.CartDto;
import com.alten.ecommerce.dto.CartItemDto;
import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper extends EntityMapper<CartItemDto, CartItem> {
}
