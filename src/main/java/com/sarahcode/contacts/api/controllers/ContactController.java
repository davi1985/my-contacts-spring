package com.sarahcode.contacts.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sarahcode.contacts.api.controllers.contacts.dto.ContactResponse;
import com.sarahcode.contacts.api.mappers.ContactMapper;
import com.sarahcode.contacts.api.services.contacts.ContactService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;
    private final ContactMapper mapper;

    @GetMapping
    public ResponseEntity<List<ContactResponse>> getContacts() {
        var contacts = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(contacts);
    }

    // @PostMapping
    // @Transactional
    // public ResponseEntity<CategoryResponse> saveCategory(
    // @RequestBody @Valid final NewCategoryRequest request, final
    // UriComponentsBuilder uri) {
    // var response = service.save(request);
    // var location = uri.path("/api/v1/categories/{id}")
    // .buildAndExpand(response.id())
    // .toUri();

    // return ResponseEntity.created(location).body(response);
    // }

    // @GetMapping("{id}")
    // public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable final
    // Long id) {
    // var categoryResponse = mapper.toResponse(service.findById(id));

    // return ResponseEntity.ok(categoryResponse);
    // }
}
