package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dto.ProductDto;
import com.alten.ecommerce.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDto, Product> {
}
