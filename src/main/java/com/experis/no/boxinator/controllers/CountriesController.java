package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.CountriesNotFoundException;
import com.experis.no.boxinator.mappers.CountriesMapper;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/countries")
public class CountriesController {
    private final CountriesService countriesService;
    private final CountriesMapper countriesMapper;

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
        return ResponseEntity.ok(
                countriesMapper.countriesToCountriesDTO(
                        countriesService.findAll()
                )
        );
    }

    @GetMapping("{id}")
    @Operation(summary = "Gets a country by countryCode (Example us for United states)")
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
        try {
            return ResponseEntity.ok(
                    countriesMapper.countriesToCountriesDTO(
                            countriesService.findById(id)
                    )

            );
        } catch (CountriesNotFoundException countriesNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }
}
