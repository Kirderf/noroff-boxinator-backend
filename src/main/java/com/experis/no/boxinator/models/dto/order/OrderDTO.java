package com.experis.no.boxinator.models.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderDTO {
    private int id;
    private int user;
    private Set<Integer> products;
    private String status;
}
