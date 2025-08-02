package com.sarahcode.contacts.api.controllers.contacts.dto;

import java.util.Locale.Category;

import org.springframework.lang.Nullable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record NewContactRequest(
        @NotEmpty String name,
        @Email String email,
        @Nullable Category category) {
}
