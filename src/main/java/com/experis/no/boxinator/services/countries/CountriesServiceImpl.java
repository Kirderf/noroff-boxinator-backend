package com.experis.no.boxinator.services.countries;

import com.experis.no.boxinator.exceptions.CountriesNotFoundException;
import com.experis.no.boxinator.models.Countries;
import com.experis.no.boxinator.repositories.CountriesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CountriesServiceImpl implements CountriesService {
    private final CountriesRepository countriesRepository;

    public CountriesServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public Countries findById(String string) {
        return countriesRepository.findById(string).orElseThrow(() -> new CountriesNotFoundException(string));
    }

    @Override
    public Collection<Countries> findAll() {
        return countriesRepository.findAll();
    }

    @Override
    public Countries add(Countries entity) {
        return countriesRepository.save(entity);
    }

    @Override
    public Countries update(Countries entity) {
        return countriesRepository.save(entity);
    }

    @Override
    public void delete(String string) {
        countriesRepository.deleteById(string);
    }

    @Override
    public boolean exists(String string) {
        return countriesRepository.existsById(string);
    }

}
