package com.sarahcode.contacts.api.services;

import com.sarahcode.contacts.api.controllers.dto.CategoryResponse;
import com.sarahcode.contacts.api.controllers.dto.NewCategoryRequest;
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

    public List<Category> findAll() {
        return repository.findAll();
    }

    public CategoryResponse save(NewCategoryRequest request) {
        var newCategory = CategoryMapper.toEntity(request.name());
        var savedCategory = repository.save(newCategory);
        return new CategoryResponse(savedCategory.getId(), savedCategory.getName());
    }

    public CategoryResponse findById(Long id) {
        var category = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category not founded"));

        return new CategoryResponse(category.getId(), category.getName());
    }
}
