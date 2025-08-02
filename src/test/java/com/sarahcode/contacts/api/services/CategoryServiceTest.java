package com.sarahcode.contacts.api.services;

import com.sarahcode.contacts.api.controllers.categories.dto.CategoryResponse;
import com.sarahcode.contacts.api.controllers.categories.dto.NewCategoryRequest;
import com.sarahcode.contacts.api.entities.Category;
import com.sarahcode.contacts.api.exceptions.CategoryNotFoundException;
import com.sarahcode.contacts.api.mappers.CategoryMapper;
import com.sarahcode.contacts.api.repositories.CategoryRepository;
import com.sarahcode.contacts.api.services.categories.CategoryService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryMapper mapper;

    @InjectMocks
    private CategoryService service;

    @Test
    public void findAll_shouldReturnCategoryList() {
        var categories = List.of(new Category(1L, "Telegram"), new Category(2L, "Work"));
        Mockito.when(repository.findAll()).thenReturn(categories);

        var result = service.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Mockito.verify(repository).findAll();
    }

    @Test
    public void findById_shouldReturnCategoryResponse_whenCategoryExists() {
        var categoryId = 1L;
        var category = new Category(categoryId, "Telegram");
        var categoryResponse = new CategoryResponse(categoryId, "Telegram");

        Mockito.when(repository.findById(categoryId)).thenReturn(Optional.of(category));

        var result = service.findById(categoryId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Telegram", categoryResponse.name());
        Mockito.verify(repository).findById(categoryId);
    }

    @Test
    public void findById_shouldThrowException_whenCategoryNotFound() {
        var categoryId = 1L;

        Mockito.when(repository.findById(categoryId)).thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class, () -> service.findById(categoryId));

        Mockito.verify(repository).findById(categoryId);
        Mockito.verifyNoInteractions(mapper);
    }

    @Test
    public void save_shouldSaveCategory() {
        var request = new NewCategoryRequest("Instagram");
        var categoryEntity = new Category(null, "Books");
        var savedCategory = new Category(1L, "Books");
        var expectedResponse = new CategoryResponse(1L, "Books");

        Mockito.when(mapper.toEntity("Instagram")).thenReturn(categoryEntity);
        Mockito.when(repository.save(categoryEntity)).thenReturn(savedCategory);
        Mockito.when(mapper.toResponse(savedCategory)).thenReturn(expectedResponse);

        var result = service.save(request);

        Assertions.assertNotNull(result);
        Mockito.verify(repository).save(categoryEntity);
        Mockito.verify(mapper).toResponse(savedCategory);
    }
}
