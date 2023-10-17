package com.experis.no.boxinator.repositories;

import com.experis.no.boxinator.models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, String> {
}
