package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dto.ContactDto;
import com.alten.ecommerce.dto.WishlistDto;
import com.alten.ecommerce.model.Contact;
import com.alten.ecommerce.model.Wishlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishlistMapper extends EntityMapper<WishlistDto, Wishlist> {
}
