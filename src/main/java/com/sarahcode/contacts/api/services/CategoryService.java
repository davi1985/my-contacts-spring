package com.sarahcode.contacts.api.services;

import com.sarahcode.contacts.api.entities.Category;
import com.sarahcode.contacts.api.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }
}
