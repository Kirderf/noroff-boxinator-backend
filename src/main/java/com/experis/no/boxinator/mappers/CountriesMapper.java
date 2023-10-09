package com.experis.no.boxinator.mappers;

import com.experis.no.boxinator.models.Countries;
import com.experis.no.boxinator.models.dto.countries.CountriesDTO;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class CountriesMapper {

    public abstract CountriesDTO countriesToCountriesDTO(Countries countries);

    public abstract Countries countriesDTOToCountries(CountriesDTO countriesDTO);


    public abstract Collection<CountriesDTO> countriesToCountriesDTO(Collection<Countries> countriesCollection);

}
