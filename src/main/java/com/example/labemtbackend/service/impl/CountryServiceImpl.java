package com.example.labemtbackend.service.impl;

import com.example.labemtbackend.models.Country;
import com.example.labemtbackend.models.exceptions.CountryNotFoundException;
import com.example.labemtbackend.repository.CountryRepository;
import com.example.labemtbackend.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country=new Country();
        country.setName(name);
        country.setContinent(continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long id, String name, String continent) {
        Country country = this.findById(id).orElseThrow();
        country.setName(name);
        country.setContinent(continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}
