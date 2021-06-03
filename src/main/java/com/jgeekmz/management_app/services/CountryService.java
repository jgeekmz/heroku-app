package com.jgeekmz.management_app.services;

import com.jgeekmz.management_app.models.Country;
import com.jgeekmz.management_app.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    //Get All Countries
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    //Get Country By Id
    public Optional<Country> findById(int id) {
        return countryRepository.findById(id);
    }

    //Delete Country
    public void delete(int id) {
        countryRepository.deleteById(id);
    }

    //Update Country
    public void save(Country country) {
        countryRepository.save(country);
    }
}

