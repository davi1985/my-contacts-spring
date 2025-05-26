package com.sarahcode.contacts.api.repositories;

import com.sarahcode.contacts.api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
