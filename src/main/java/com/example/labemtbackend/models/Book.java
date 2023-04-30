package com.example.labemtbackend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    // id (Long), name (String),
    //category (enum), author (Author), availableCopies (Integer)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Author author;

    private int availableCopies;

    public Book() {
    }

    public Book(String name, Category category, Author author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
