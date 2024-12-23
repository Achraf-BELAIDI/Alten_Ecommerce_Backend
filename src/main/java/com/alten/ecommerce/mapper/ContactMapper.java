package com.alten.ecommerce.mapper;

import com.alten.ecommerce.dto.ContactDto;
import com.alten.ecommerce.model.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper extends EntityMapper<ContactDto, Contact> {
}
