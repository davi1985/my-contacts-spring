package com.sarahcode.contacts.api.services.categories;

import com.sarahcode.contacts.api.controllers.categories.dto.CategoryResponse;
import com.sarahcode.contacts.api.controllers.categories.dto.NewCategoryRequest;
import com.sarahcode.contacts.api.entities.Category;
import com.sarahcode.contacts.api.exceptions.CategoryNotFoundException;
import com.sarahcode.contacts.api.mappers.CategoryMapper;
import com.sarahcode.contacts.api.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public CategoryResponse save(NewCategoryRequest request) {
        var savedCategory = repository.save(mapper.toEntity(request.name()));

        return mapper.toResponse(savedCategory);
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not founded"));
    }
}
