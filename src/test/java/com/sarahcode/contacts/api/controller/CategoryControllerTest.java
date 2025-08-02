package com.sarahcode.contacts.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarahcode.contacts.api.controllers.categories.CategoryController;
import com.sarahcode.contacts.api.controllers.categories.dto.CategoryResponse;
import com.sarahcode.contacts.api.controllers.categories.dto.NewCategoryRequest;
import com.sarahcode.contacts.api.entities.Category;
import com.sarahcode.contacts.api.exceptions.CategoryNotFoundException;
import com.sarahcode.contacts.api.mappers.CategoryMapper;
import com.sarahcode.contacts.api.services.categories.CategoryService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService service;

    @MockitoBean
    private CategoryMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCategories_shouldReturnListOfCategories() throws Exception {
        var category1 = new Category(1L, "Instagram");
        var category2 = new Category(2L, "Facebook");

        Mockito.when(service.findAll()).thenReturn(List.of(category1, category2));
        Mockito.when(mapper.toResponse(category1))
                .thenReturn(new CategoryResponse(1L, "Instagram"));
        Mockito.when(mapper.toResponse(category2))
                .thenReturn(new CategoryResponse(2L, "Facebook"));

        var expectedJson = readJson("/response/categories.json");

        mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        Mockito.verify(service).findAll();
        Mockito.verify(mapper).toResponse(category1);
        Mockito.verify(mapper).toResponse(category2);
    }

    @Test
    public void getCategoryById_shouldReturnCategory_whenCategoryExists() throws Exception {
        var categoryId = 1L;
        var category = new Category(categoryId, "Instagram");
        var response = new CategoryResponse(categoryId, "Instagram");

        Mockito.when(service.findById(categoryId)).thenReturn(category);
        Mockito.when(mapper.toResponse(category)).thenReturn(response);

        var expectedJson = readJson("/response/category.json");

        mockMvc.perform(get("/api/v1/categories/" + categoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        Mockito.verify(service).findById(categoryId);
        Mockito.verify(mapper).toResponse(category);
    }

    @Test
    public void getCategoryById_shouldReturn404_whenCategoryNotExists() throws Exception {
        var invalidCategoryId = 3L;

        Mockito.when(service.findById(invalidCategoryId))
                .thenThrow(new CategoryNotFoundException("Category not found"));

        var expectedJson = readJson("/response/category-not-found.json");

        mockMvc.perform(get("/api/v1/categories/" + invalidCategoryId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json(expectedJson));

        Mockito.verify(service).findById(invalidCategoryId);
    }

    @Test
    public void saveCategory_shouldCreateNewCategory() throws Exception {
        var requestJson = readJson("/request/category.json");
        var expectedJson = readJson("/response/category.json");

        var savedResponse = new CategoryResponse(1L, "Instagram");

        Mockito.when(service.save(Mockito.any(NewCategoryRequest.class))).thenReturn(savedResponse);

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/v1/categories/1"))
                .andExpect(content().json(expectedJson));

        Mockito.verify(service).save(Mockito.any(NewCategoryRequest.class));
    }

    @Test
    public void saveCategory_shouldThrowError_whenPayloadInvalid() throws Exception {
        var request = new NewCategoryRequest("");
        var json = objectMapper.writeValueAsString(request);
        var expectedJson = readJson("/response/bad-request.json");

        mockMvc.perform(post("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(expectedJson));
    }

    private String readJson(String relativePath) throws IOException {
        var path = Path.of("src/test/resources", relativePath);

        return Files.readString(path);
    }
}
