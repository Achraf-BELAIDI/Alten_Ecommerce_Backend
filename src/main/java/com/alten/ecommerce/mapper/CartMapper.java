package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dto.CartDto;
import com.alten.ecommerce.model.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDto, Cart> {
}
