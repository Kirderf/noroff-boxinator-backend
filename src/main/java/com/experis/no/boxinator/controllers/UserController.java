package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.ProductNotFoundException;
import com.experis.no.boxinator.mappers.UserMapper;
import com.experis.no.boxinator.models.Product;
import com.experis.no.boxinator.models.User;
import com.experis.no.boxinator.models.dto.product.ProductDTO;
import com.experis.no.boxinator.models.dto.product.ProductPostDTO;
import com.experis.no.boxinator.models.dto.user.UserDTO;
import com.experis.no.boxinator.models.dto.user.UserPostDTO;
import com.experis.no.boxinator.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @GetMapping
    @Operation(summary = "Gets all Users")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserDTO.class)))
                    }
            )
    })
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(
                userMapper.userToUserDTO(
                        userService.findAll()
                )
        );
    }
    @GetMapping("{id}")
    @Operation(summary = "Gets a user by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity<?> findById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                    userMapper.userToUserDTO(
                            userService.findById(id)
                    )

            );
        } catch (ProductNotFoundException productNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    @Operation(summary = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity<?> add(@RequestBody UserPostDTO entity) throws URISyntaxException {
        User user = userService.add(userMapper.userPostDTOToUser(entity));
        URI uri = new URI("api/v1/user/" + user.getId());
        return ResponseEntity.created(uri).build();
    }

}
