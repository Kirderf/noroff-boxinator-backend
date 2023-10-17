package com.experis.no.boxinator.controllers;

import com.experis.no.boxinator.exceptions.UserNotFoundException;
import com.experis.no.boxinator.mappers.UserMapper;
import com.experis.no.boxinator.models.User;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    Logger logger = Logger.getLogger(this.getClass().getName());
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAuthority('ID_' + #id) or hasRole('ADMIN')")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(
                    userMapper.userToUserDTO(
                            userService.findById(id)
                    )

            );
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Adds a new user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserPostDTO.class))
                    }
            )
    })
    @PreAuthorize("hasAuthority('ID_' + #entity.id) or hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody UserPostDTO entity) throws URISyntaxException {
        if (userService.exists(entity.getId())) {
            return ResponseEntity.badRequest().build();
        }
        User user = userService.add(userMapper.userPostDTOToUser(entity));
        URI uri = new URI("api/v1/user/" + user.getId());
        logger.log(Level.INFO, "User with this id was created: " + user.getId());
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping
    @Operation(summary = "Patches fields on a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserPostDTO.class))
                    }
            ), @ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            }
    )
    })
    @PreAuthorize("hasAuthority('ID_' + #entity.id) or hasRole('ADMIN')")
    public ResponseEntity<?> patchFields(@RequestBody UserPostDTO entity) {
        if (userService.exists(entity.getId())) {
            User user = userService.update(userMapper.userPostDTOToUser(entity));
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }
}
