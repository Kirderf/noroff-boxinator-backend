package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @Column(nullable = false, unique = true)
    private String id;
    @Column(length = 50, nullable = false, unique = true)
    private String  email;
    @Column(length = 50, nullable = false)
    private String  username;
    @Column(length = 50, nullable = false)
    private String  address;
}
