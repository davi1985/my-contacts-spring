package com.sarahcode.contacts.api.controllers.contacts.dto;

import com.sarahcode.contacts.api.entities.Category;

public record ContactResponse(
        String name,
        String phone,
        String email,
        Category category) {
}
