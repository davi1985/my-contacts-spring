package com.sarahcode.contacts.api.controllers;

import com.sarahcode.contacts.api.controllers.dto.CategoryResponse;
import com.sarahcode.contacts.api.controllers.dto.NewCategoryRequest;
import com.sarahcode.contacts.api.mappers.CategoryMapper;
import com.sarahcode.contacts.api.services.CategoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        var categories = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryResponse> saveCategory(
            @RequestBody @Valid final NewCategoryRequest  request, final UriComponentsBuilder uri) {
        var response = service.save(request);
        var location = uri.path("/api/v1/categories/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable final Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}
