package com.example.labemtbackend.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {
    //: id (Long), name (String), surname (String), country (Country)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;

    public Author() {
    }
}
