package com.experis.no.boxinator.models.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private int id;
    private float price;
    private int stock;
    private String description;
    private String name;
    private String image;
    private boolean isActive;
}
