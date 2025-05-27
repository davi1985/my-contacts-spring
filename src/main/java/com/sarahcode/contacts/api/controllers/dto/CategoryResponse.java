package com.sarahcode.contacts.api.controllers.dto;

import com.sarahcode.contacts.api.entities.Category;

public record CategoryResponse(Long id, String name) {

    public  CategoryResponse(Category category) {
        this(category.getId(), category.getName());
    }
}
