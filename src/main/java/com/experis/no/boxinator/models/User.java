package com.experis.no.boxinator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String  email;
    @Column(length = 50, nullable = false)
    private String  username;
    @Column(length = 50, nullable = false)
    private String  roles; //TODO add enum
    @Column(length = 50, nullable = false)
    private String  address;
    @Column(length = 50, nullable = false)
    private String  password; //TODO ska dæ hær lagres i db ?
}
