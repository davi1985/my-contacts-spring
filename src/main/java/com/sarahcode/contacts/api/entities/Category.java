package com.sarahcode.contacts.api.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Category")
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
