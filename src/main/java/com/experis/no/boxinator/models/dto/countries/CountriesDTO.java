package com.experis.no.boxinator.models.dto.countries;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountriesDTO {
    private String shortName;
    private String fullName;
    private int shippingCost;
}
