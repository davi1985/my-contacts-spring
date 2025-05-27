package com.sarahcode.contacts.api.mapper;

import com.sarahcode.contacts.api.entities.Category;
import com.sarahcode.contacts.api.mappers.CategoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryMapperTest {

    private final CategoryMapper mapper = new CategoryMapper();

    @Test
    public void toResponse_shouldMapCorrectly() {
        var category = new Category(1L, "Telegram");
        var result = mapper.toResponse(category);

        Assertions.assertEquals(category.getId(), result.id());
        Assertions.assertEquals(category.getName(), result.name());
    }

    @Test
    public void toEntity_shouldMapCorrectly() {
        var category = mapper.toEntity("Telegram");

        Assertions.assertEquals("Telegram", category.getName());
        Assertions.assertInstanceOf(Category.class, category);
    }
}
