package com.sarahcode.contacts.api.mappers;

import com.sarahcode.contacts.api.controllers.dto.CategoryResponse;
import com.sarahcode.contacts.api.entities.Category;

public class CategoryMapper {

    public Category toEntity(String name) {
        return new Category(name);
    }

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
