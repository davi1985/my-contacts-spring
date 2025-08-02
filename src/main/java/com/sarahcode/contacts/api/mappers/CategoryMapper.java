package com.sarahcode.contacts.api.mappers;

import org.springframework.stereotype.Component;

import com.sarahcode.contacts.api.controllers.categories.dto.CategoryResponse;
import com.sarahcode.contacts.api.entities.Category;

@Component
public class CategoryMapper {

    public Category toEntity(String name) {
        return new Category(name);
    }

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
