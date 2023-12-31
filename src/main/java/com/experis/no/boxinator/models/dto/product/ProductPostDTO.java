package com.experis.no.boxinator.models.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPostDTO {
    private float price;
    private int stock;
    private String description;
    private String name;
    private String image;
    private boolean isActive;
    private int width;
    private int height;
    private int depth;
    private int weight;
}
