package com.experis.no.boxinator.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/resources")
public class ResourceController {

    @GetMapping("public")
    @Operation(summary = "Public endpoint test")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success"
            )
    })
    public ResponseEntity getPublic() {
        return ResponseEntity.ok("public");
    }

    @GetMapping("authenticated")
    @Operation(summary = "Authenticated endpoint test")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            )
    })
    public ResponseEntity getAuthenticated() {
        return ResponseEntity.ok("authenticated");
    }

    @GetMapping("authorized")
    @Operation(summary = "Authorized endpoint test")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            )
    })
    public ResponseEntity getAuthorized() {
        return ResponseEntity.ok("authorized");
    }
}

