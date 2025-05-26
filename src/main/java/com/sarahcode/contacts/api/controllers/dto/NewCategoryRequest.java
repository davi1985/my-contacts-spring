package com.sarahcode.contacts.api.controllers.dto;

import jakarta.validation.constraints.NotEmpty;

public record NewCategoryRequest(@NotEmpty String name) {
}
