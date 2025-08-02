package com.sarahcode.contacts.api.mappers;

import org.springframework.stereotype.Component;

import com.sarahcode.contacts.api.controllers.contacts.dto.ContactResponse;
import com.sarahcode.contacts.api.entities.Category;
import com.sarahcode.contacts.api.entities.Contact;

@Component
public class ContactMapper {

    public Contact toEntity(String name, String email, String phone, Category category) {
        return new Contact(name, phone, email, category);
    }

    public ContactResponse toResponse(Contact contact) {
        return new ContactResponse(
                contact.getName(),
                contact.getPhone(),
                contact.getEmail(),
                contact.getCategory());
    }
}
