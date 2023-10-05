package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Countries {
    @Id
    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private String short_name;

    @Column(nullable = false)
    private int shipping_cost;
}
