package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping(value = "api/v1/info")
public class InfoController {

    private final UserService userService;

    public InfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getToken(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(jwt.getClaimAsString("sub"));
    }

    @PostMapping
    public ResponseEntity addUserWithToken(@AuthenticationPrincipal Jwt jwt) {

        return ResponseEntity.created(null).build();
    }

    @GetMapping("users")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("principal")
    public ResponseEntity getPrincipal(Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
