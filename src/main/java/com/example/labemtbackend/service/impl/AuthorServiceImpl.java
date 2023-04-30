package com.example.labemtbackend.service.impl;

import com.example.labemtbackend.models.Author;
import com.example.labemtbackend.models.Country;
import com.example.labemtbackend.models.exceptions.AuthorNotFoundException;
import com.example.labemtbackend.models.exceptions.CountryNotFoundException;
import com.example.labemtbackend.repository.AuthorRepository;
import com.example.labemtbackend.repository.CountryRepository;
import com.example.labemtbackend.service.AuthorService;
import com.example.labemtbackend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository,CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(()->new CountryNotFoundException(countryId));
        Author author=new Author();
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
