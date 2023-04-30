package com.example.labemtbackend.service;

import com.example.labemtbackend.models.Author;
import com.example.labemtbackend.models.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CountryService {
    List<Country> listAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(String name, String continent);
    Optional<Country> edit(Long id,String name,String continent);
    void deleteById(Long id);
}
