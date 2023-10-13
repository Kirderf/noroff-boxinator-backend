package com.experis.no.boxinator.models.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String address;
    private String email;
    private String roles;
    private String username;
}
