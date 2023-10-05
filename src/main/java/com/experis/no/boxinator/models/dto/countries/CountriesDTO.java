package com.experis.no.boxinator.models.dto.countries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountriesDTO {
    private String full_name;
    private String short_name;
    private int shipping_cost; //TODO fix
}
