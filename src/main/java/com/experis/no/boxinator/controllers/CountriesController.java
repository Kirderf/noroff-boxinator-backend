package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.CountriesNotFoundException;
import com.experis.no.boxinator.mappers.CountriesMapper;
import com.experis.no.boxinator.models.Countries;
import com.experis.no.boxinator.models.dto.countries.CountriesDTO;
import com.experis.no.boxinator.services.countries.CountriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/countries")
public class CountriesController {
    private final CountriesService countriesService;
    private final CountriesMapper countriesMapper;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public CountriesController(CountriesService countriesService, CountriesMapper countriesMapper) {
        this.countriesService = countriesService;
        this.countriesMapper = countriesMapper;
    }

    @GetMapping
    @Operation(summary = "Gets all Countries")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CountriesDTO.class)))
                    }
            )
    })
    public ResponseEntity<?> findAll() {
        logger.info("Fetching all countries.");
        Collection<CountriesDTO> countries = countriesMapper.countriesToCountriesDTO(
                countriesService.findAll()
        );
        logger.info("Successfully fetched all countries.");
        return ResponseEntity.ok(countries);
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets a country by countryCode (Example US for United states)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CountriesDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity<?> findById(@PathVariable String id) {
        logger.info("Fetching country with countryCode: " + id);
        try {
            CountriesDTO countriesDTO = countriesMapper.countriesToCountriesDTO(
                    countriesService.findById(id)
            );
            logger.info("Successfully fetched country with countryCode: " + id);
            return ResponseEntity.ok(countriesDTO);
        } catch (CountriesNotFoundException countriesNotFoundException) {
            logger.warning("Country with countryCode: " + id + " not found.");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Adds a new country")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody CountriesDTO entity) throws URISyntaxException {
        logger.info("Adding new country: " + entity.getShortName());
        Countries countries = countriesService.add(countriesMapper.countriesDTOToCountries(entity));
        URI uri = new URI("api/v1/countries/" + countries.getShortName());
        logger.info("Successfully added new country: " + entity.getShortName());
        return ResponseEntity.created(uri).build();
    }
}
