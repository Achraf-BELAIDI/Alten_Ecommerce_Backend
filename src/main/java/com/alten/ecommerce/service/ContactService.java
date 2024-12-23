package com.alten.ecommerce.service;

import com.alten.ecommerce.dto.ContactDto;
import com.alten.ecommerce.mapper.ContactMapper;
import com.alten.ecommerce.model.Contact;
import com.alten.ecommerce.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    public ContactDto saveContact(ContactDto contactDto) {
        Contact contact = contactMapper.toEntity(contactDto);
        Contact result = contactRepository.save(contact);
        return contactMapper.toDto(result) ;
    }
}
