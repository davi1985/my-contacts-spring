package com.sarahcode.contacts.api.services.contacts;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sarahcode.contacts.api.entities.Contact;
import com.sarahcode.contacts.api.repositories.ContactRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository repository;

    public List<Contact> findAll() {
        return repository.findAll();
    }

    // public CategoryResponse save(NewCategoryRequest request) {
    // var savedCategory = repository.save(mapper.toEntity(request.name()));

    // return mapper.toResponse(savedCategory);
    // }

    // public Category findById(Long id) {
    // return repository.findById(id).orElseThrow(() -> new
    // CategoryNotFoundException("Category not founded"));
    // }
}
