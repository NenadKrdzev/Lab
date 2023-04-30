package com.example.labemtbackend.service;

import com.example.labemtbackend.models.Author;
import com.example.labemtbackend.models.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    List<Author> listAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(String name, String surname, Long countryId);

    void deleteById(Long id);
}
